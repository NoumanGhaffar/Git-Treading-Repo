package com.nouman.gittreadingrepo.repos

import com.nouman.gittreadingrepo.models.TrendingRepos

interface GitApiRepository {
    suspend fun getTrendingRepos(): TrendingRepos
}
