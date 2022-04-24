package com.nouman.gittreadingrepo.network

import com.nouman.gittreadingrepo.models.TrendingRepos
/**
 * Git API
 */
interface GitApi {
    /**
     * This api will get the trending Repos for git
     * @return TrendingReposResponse ( This will contain List<Repo> )
     */
    suspend fun getTrendingRepos(): TrendingRepos
}
