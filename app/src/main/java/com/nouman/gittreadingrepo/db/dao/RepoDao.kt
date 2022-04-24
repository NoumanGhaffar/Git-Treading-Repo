package com.nouman.gittreadingrepo.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nouman.gittreadingrepo.db.entity.RepoEntity

@Dao
abstract class RepoDao {

    @Query("SELECT * FROM repo ORDER BY forks ASC")
    abstract suspend fun getAll(): List<RepoEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insert(repo: RepoEntity)
}
