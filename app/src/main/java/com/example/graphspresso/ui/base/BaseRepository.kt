package com.example.graphspresso.ui.base

import com.apollographql.apollo.exception.ApolloHttpException
import com.example.graphspresso.utils.ContextProviders
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import sa.gov.mos.utils.ResourcesHandler
import java.io.IOException

abstract class BaseRepository(private val contextProviders: ContextProviders) : KoinComponent {

    private val resourcesHandler: ResourcesHandler by inject()
    val UNKNOWN_ERROR = /*resourcesHandler.getString(R.string.error)*/ "UNKNOWN_ERROR"
    val NETWORK_ERROR = /*resourcesHandler.getString(R.string.internet_error_message)*/
        "NETWORK_ERROR"
    val NETWORK_ERROR_TIMEOUT = /*resourcesHandler.getString(R.string.time_out_message)*/
        "NETWORK_TIME_OUT_ERROR"


    suspend fun <T> launchBlock(block: suspend CoroutineScope.() -> T): T {
        return withContext(contextProviders.IO) {
            return@withContext block.invoke(this)
        }
    }

    fun convertErrorBodyToString(errorBody: ResponseBody?): String {
        return try {
            errorBody?.string() ?: UNKNOWN_ERROR
        } catch (e: Exception) {
            //Timber.e(e)
            UNKNOWN_ERROR
        }
    }

    fun <T> networkHandler(fetch: suspend () -> T) = flow {

        try {
            emit(fetch.invoke())
        } catch (throwable: Throwable) {

            when (throwable) {
                is TimeoutCancellationException -> {
                    throw Exception(NETWORK_ERROR_TIMEOUT)
                }
                is IOException -> {
                    throw Exception(NETWORK_ERROR)
                }
                is ApolloHttpException -> {
                    throw Exception(getErrorFrom(throwable))
                }
                else -> {
                    throw Exception(UNKNOWN_ERROR)
                }
            }
        }
    }

    private fun getErrorFrom(throwable: ApolloHttpException): String {
        return try {
            throwable.rawResponse().toString()
        } catch (exception: Exception) {
            UNKNOWN_ERROR
        }
    }


}
