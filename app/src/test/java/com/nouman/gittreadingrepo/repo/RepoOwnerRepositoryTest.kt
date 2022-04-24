package com.nouman.gittreadingrepo.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.nouman.gittreadingrepo.FakeDataProvider
import com.nouman.gittreadingrepo.db.dao.RepoOwnerDao
import com.nouman.gittreadingrepo.db.repositoryImp.RepoOwnerRepositoryImpl
import com.nouman.gittreadingrepo.helper.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RepoOwnerRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = CoroutineTestRule()
    private var dao: RepoOwnerDao = mock()
    private lateinit var repoImpl: RepoOwnerRepositoryImpl

    @Before
    fun init() {
        repoImpl = RepoOwnerRepositoryImpl(dao)
    }

    @Test
    fun `verify getRepoOwner run without error and complete`() = runBlocking {

        whenever(dao.getRepoOwner(1)).thenReturn(
            FakeDataProvider.getRepoOwnerEntity()
        )
        val repoResponse = repoImpl.getRepoOwner(1)
        assertEquals(FakeDataProvider.getRepoOwnerObject(), repoResponse)
    }
}
