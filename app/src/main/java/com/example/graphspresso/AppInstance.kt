package com.example.graphspresso

import android.app.Application
import com.example.graphspresso.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import sa.gov.mos.di.*

class AppInstance : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@AppInstance)
            modules(
                listOf(
                    repositoryModule,
                    viewModelModule,
                    networkModule,
                    apisModule,
                    generalModule
                )
            )
        }
    }
}