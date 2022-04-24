package com.nouman.gittreadingrepo.db.repository

import com.nouman.gittreadingrepo.models.RepoOwner

/**
 * Repo Owner DB
 */
interface RepoOwnerDbRepository {
    /**
     * get all [RepoOwner]
     */
    suspend fun getRepoOwner(repoOwnerId: Int): RepoOwner

    /**
     * insert [RepoOwner]
     */
    suspend fun insert(repo: RepoOwner)
}
