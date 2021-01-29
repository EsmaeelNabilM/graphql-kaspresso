package sa.gov.mos.di

import com.apollographql.apollo.ApolloClient
import com.example.graphspresso.BuildConfig
import com.example.graphspresso.data.remote.GithubApiService
import com.example.graphspresso.data.remote.GithubApiServiceImpl
import com.example.graphspresso.utils.ContextProviders
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import sa.gov.mos.utils.ResourcesHandler

val generalModule = module {
    single { ResourcesHandler(get()) }
    single { ContextProviders() }
}
val apisModule = module {

    fun provideGithubApiService(apolloClient: ApolloClient): GithubApiService {
        return GithubApiServiceImpl(apolloClient)
    }

    single { provideGithubApiService(get()) }
}

val networkModule = module {

    fun provideApolloClient(client: OkHttpClient): ApolloClient {
        return ApolloClient.builder()
            .serverUrl("https://api.github.com/graphql")
            .okHttpClient(client)
            .build()
    }

    single { provideApolloClient(get()) }

    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }
    }

    fun provideKeysInjectionInterceptor(): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val original = chain.request()
            chain.proceed(
                chain.request().newBuilder()
                    .addHeader("Authorization", "bearer c9a67aae0a634a4c78f1478719cf44740c9db040")
                    .method(original.method, original.body)
                    .build()
            )
        }
    }

    fun provideHttpClient(
        logger: HttpLoggingInterceptor,
        keysInjectionInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .addInterceptor(keysInjectionInterceptor)
            .build()
    }
    single { provideHttpClient(get(), get()) }
    single { provideLoggingInterceptor() }
    single { provideKeysInjectionInterceptor() }
}
