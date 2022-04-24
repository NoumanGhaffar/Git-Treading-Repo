package com.nouman.gittreadingrepo.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.nouman.gittreadingrepo.FakeDataProvider
import com.nouman.gittreadingrepo.helper.CoroutineTestRule
import com.nouman.gittreadingrepo.network.GitApi
import com.nouman.gittreadingrepo.repos.GitApiRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GitApiRepoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = CoroutineTestRule()
    private var gitApi: GitApi = mock()
    private lateinit var repoImpl: GitApiRepositoryImpl

    @Before
    fun init() {
        repoImpl = GitApiRepositoryImpl(gitApi)
    }

    @Test
    fun `verify getTrendingRepos run without error and complete`() = runBlocking {
        val fakeData = FakeDataProvider.getTreadingRepoObject()
        whenever(gitApi.getTrendingRepos()).thenReturn(
            fakeData
        )
        val repoResponse = repoImpl.getTrendingRepos()
        assertEquals(fakeData, repoResponse)
    }

    @Test
    fun `verify getTrendingRepos runs and got error it should return null response`() = runBlocking {
        whenever(gitApi.getTrendingRepos()).thenReturn(
            null
        )
        val repoResponse = repoImpl.getTrendingRepos()
        assertEquals(null, repoResponse)
    }
}
