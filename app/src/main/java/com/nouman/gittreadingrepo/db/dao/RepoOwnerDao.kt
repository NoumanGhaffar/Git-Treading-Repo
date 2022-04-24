package com.nouman.gittreadingrepo.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nouman.gittreadingrepo.db.entity.RepoOwnerEntity

@Dao
abstract class RepoOwnerDao {

    @Query("SELECT * FROM repo_owner WHERE id = :repoOwnerId")
    abstract suspend fun getRepoOwner(repoOwnerId: Int): RepoOwnerEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insert(repo: RepoOwnerEntity)
}
