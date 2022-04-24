package com.nouman.gittreadingrepo.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.nouman.gittreadingrepo.FakeDataProvider
import com.nouman.gittreadingrepo.db.repository.RepoDbRepository
import com.nouman.gittreadingrepo.helper.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UseCaseGetTreadingRepoFromDbTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = CoroutineTestRule()
    private var repo: RepoDbRepository = mock()
    private lateinit var useCase: GetReposFormDbUseCase

    @Before
    fun init() {
        useCase = GetReposFormDbUseCase(repo)
    }

    @Test
    fun `verify getAll repos run without error and complete`() = runBlocking {
        val fakeData = FakeDataProvider.getTreadingRepoObject().repoList
        whenever(repo.getAll()).thenReturn(
            fakeData
        )
        val response = useCase.run(Unit)
        assertEquals(fakeData, response)
    }

    @Test
    fun `verify getAll repos runs and got error it should return null response`() = runBlocking {
        whenever(repo.getAll()).thenReturn(
            null
        )
        val response = useCase.run(Unit)
        assertEquals(null, response)
    }
}
