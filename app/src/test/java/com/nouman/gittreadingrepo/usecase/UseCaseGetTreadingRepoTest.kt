package com.nouman.gittreadingrepo.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.nouman.gittreadingrepo.FakeDataProvider
import com.nouman.gittreadingrepo.helper.CoroutineTestRule
import com.nouman.gittreadingrepo.repos.GitApiRepository
import com.nouman.gittreadingrepo.usecase.GetTreadingRepoUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UseCaseGetTreadingRepoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = CoroutineTestRule()
    private var repo: GitApiRepository = mock()
    private lateinit var useCase: GetTreadingRepoUseCase

    @Before
    fun init() {
        useCase = GetTreadingRepoUseCase(repo)
    }

    @Test
    fun `verify getTrendingRepos run without error and complete`() = runBlocking {
        val fakeData = FakeDataProvider.getTreadingRepoObject()
        whenever(repo.getTrendingRepos()).thenReturn(
            fakeData
        )
        val response = useCase.run(Unit)
        assertEquals(fakeData, response)
    }

    @Test
    fun `verify getTrendingRepos runs and got error it should return null response`() = runBlocking {
        whenever(repo.getTrendingRepos()).thenReturn(
            null
        )
        val fakeData = useCase.run(Unit)
        assertEquals(null, fakeData)
    }
}
