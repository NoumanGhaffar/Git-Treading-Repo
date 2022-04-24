package com.nouman.gittreadingrepo.di

import android.app.Application
import androidx.room.Room
import com.nouman.gittreadingrepo.db.AppDatabase
import com.nouman.gittreadingrepo.db.repository.RepoDbRepository
import com.nouman.gittreadingrepo.db.repository.RepoOwnerDbRepository
import com.nouman.gittreadingrepo.db.repositoryImp.RepoOwnerRepositoryImpl
import com.nouman.gittreadingrepo.db.repositoryImp.RepoDbRepositoryImp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Database Module
 * Define all the database-related classes that need to be provided in the scope of Application.
 */
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "git_repo_db").build()
    }

    @Provides
    @Singleton
    fun provideRepoDbRepositoryImp(appDatabase: AppDatabase): RepoDbRepository {
        return RepoDbRepositoryImp(appDatabase.repoDao, appDatabase.repoOwnerDao)
    }

    @Provides
    @Singleton
    fun provideRepoOwnerRepositoryImpl(appDatabase: AppDatabase): RepoOwnerDbRepository {
        return RepoOwnerRepositoryImpl(appDatabase.repoOwnerDao)
    }
}
