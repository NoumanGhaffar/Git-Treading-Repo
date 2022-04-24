package com.nouman.gittreadingrepo.repos

import com.nouman.gittreadingrepo.models.TrendingRepos
import com.nouman.gittreadingrepo.network.GitApi
import javax.inject.Inject

class GitApiRepositoryImpl @Inject constructor(
    private val gitApi: GitApi
) : GitApiRepository {
    override suspend fun getTrendingRepos(): TrendingRepos {
        return gitApi.getTrendingRepos()
    }
}
