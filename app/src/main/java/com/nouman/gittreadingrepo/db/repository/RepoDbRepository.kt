package com.nouman.gittreadingrepo.db.repository

import com.nouman.gittreadingrepo.models.Repo

/**
 * Repo DB
 */
interface RepoDbRepository {
    /**
     * get all [Repo]
     */
    suspend fun getAll(): List<Repo>

    /**
     * insert [Repo]
     */
    suspend fun insert(repo: Repo)
}
