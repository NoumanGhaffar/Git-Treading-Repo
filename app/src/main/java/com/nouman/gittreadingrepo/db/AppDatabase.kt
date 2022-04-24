package com.nouman.gittreadingrepo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nouman.gittreadingrepo.db.dao.RepoDao
import com.nouman.gittreadingrepo.db.dao.RepoOwnerDao
import com.nouman.gittreadingrepo.db.entity.RepoEntity
import com.nouman.gittreadingrepo.db.entity.RepoOwnerEntity

/**
 * App Database
 */
@Database(
    entities = [RepoEntity::class, RepoOwnerEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract val repoDao: RepoDao
    abstract val repoOwnerDao: RepoOwnerDao
}
