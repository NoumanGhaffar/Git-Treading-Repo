package com.nouman.gittreadingrepo.di

import com.nouman.gittreadingrepo.network.Config
import com.nouman.gittreadingrepo.network.GitApi
import com.nouman.gittreadingrepo.network.GitApiClient
import com.nouman.gittreadingrepo.repos.GitApiRepository
import com.nouman.gittreadingrepo.repos.GitApiRepositoryImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * NetworkModule
 * Defined all the network-related classes that need to be provided in the scope of Application.
 */
@Module
open class NetworkModule {

    @Provides
    @Singleton
    open fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .connectTimeout(Config.CONNECTION_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(Config.WRITE_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(Config.READ_TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Config.GIT_BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideGitApi(retrofit: Retrofit): GitApi {
        return GitApiClient(retrofit)
    }

    @Provides
    @Singleton
    fun provideGitRepo(gitApi: GitApi): GitApiRepository {
        return GitApiRepositoryImpl(gitApi)
    }
}
