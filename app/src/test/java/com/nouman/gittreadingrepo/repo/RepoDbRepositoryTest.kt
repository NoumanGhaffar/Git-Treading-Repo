package com.nouman.gittreadingrepo.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.nouman.gittreadingrepo.FakeDataProvider
import com.nouman.gittreadingrepo.db.dao.RepoDao
import com.nouman.gittreadingrepo.db.dao.RepoOwnerDao
import com.nouman.gittreadingrepo.db.repositoryImp.RepoDbRepositoryImp
import com.nouman.gittreadingrepo.helper.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RepoDbRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = CoroutineTestRule()
    private var repoDao: RepoDao = mock()
    private var ownerDao: RepoOwnerDao = mock()
    private lateinit var repoImpl: RepoDbRepositoryImp

    @Before
    fun init() {
        repoImpl = RepoDbRepositoryImp(repoDao, ownerDao)
    }

    @Test
    fun `verify getRepoOwner run without error and complete`() = runBlocking {
        whenever(repoDao.getAll()).thenReturn(
            FakeDataProvider.getRepoEntitiesList().subList(0,1)
        )
        whenever(ownerDao.getRepoOwner(any())).thenReturn(
            FakeDataProvider.getRepoOwnerEntity(0)
        )
        val repoResponse = repoImpl.getAll()
        assertEquals(FakeDataProvider.getTreadingRepoObject().repoList.subList(0,1), repoResponse)
    }
}
