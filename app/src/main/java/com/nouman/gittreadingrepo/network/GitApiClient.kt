package com.nouman.gittreadingrepo.network

import androidx.annotation.Keep
import com.nouman.gittreadingrepo.models.TrendingRepos
import com.nouman.gittreadingrepo.models.response.TrendingReposResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.http.GET
/**
 * Git Api Client
 */
class GitApiClient(retrofit: Retrofit) : GitApi {

    interface Service {
        @Keep
        @GET("repositories?q=language=+sort:forks")
        suspend fun getTrendingRepos(
        ): TrendingReposResponse
    }

    private val service = retrofit.create(Service::class.java)

    /**
     * This api will get the Repos from git

     * @return TrendingRepos ( Page will contain List<Repos>
     */
    override suspend fun getTrendingRepos(): TrendingRepos {
        return withContext(Dispatchers.IO) {
            service.getTrendingRepos(
            ).toModel()
        }
    }
}
