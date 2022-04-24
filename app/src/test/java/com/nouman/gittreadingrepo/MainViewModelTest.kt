package com.nouman.gittreadingrepo


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jraska.livedata.test
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.nouman.gittreadingrepo.helper.CoroutineTestRule
import com.nouman.gittreadingrepo.models.GetRepoStates
import com.nouman.gittreadingrepo.models.TrendingRepos
import com.nouman.gittreadingrepo.usecase.GetReposFormDbUseCase
import com.nouman.gittreadingrepo.usecase.GetTreadingRepoUseCase
import com.nouman.gittreadingrepo.usecase.InsertReposUseCase
import com.nouman.gittreadingrepo.viewmodel.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MainViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = CoroutineTestRule()

    private val getTreadingRepoUseCase: GetTreadingRepoUseCase = mock()
    private val insertReposUseCase: InsertReposUseCase = mock()
    private val getReposFormDBUseCase: GetReposFormDbUseCase = mock()

    private lateinit var viewModel: MainViewModel

    @ExperimentalCoroutinesApi
    @Before
    fun init() {
        viewModel = MainViewModel(
            getTreadingRepoUseCase,
            insertReposUseCase,
            getReposFormDBUseCase
        )
    }

    @Test
    @ExperimentalCoroutinesApi
    fun `verify when getTreadingRepos called, and data exist on server, should call LoadReposToUi`(): Unit =
        runBlocking {
            val getRepoStates = viewModel.repos.test()
            val dummyData = FakeDataProvider.getTreadingRepoObject()
            whenever(getTreadingRepoUseCase.run(any()))
                .thenReturn(dummyData)
            viewModel.getTreadingRepos()
            getRepoStates.assertValue(GetRepoStates.LoadReposToUi(dummyData.repoList))
        }

    @Test
    @ExperimentalCoroutinesApi
    fun `verify when getTreadingRepos called, and response is empty, should call ShowError with empty list`(): Unit =
        runBlocking {
            val getRepoStates = viewModel.repos.test()
            whenever(getTreadingRepoUseCase.run(any()))
                .thenReturn(TrendingRepos(0, emptyList()))
            viewModel.getTreadingRepos()
            getRepoStates.assertValue(GetRepoStates.ShowError(true))
        }

    @Test
    @ExperimentalCoroutinesApi
    fun `verify when getTreadingRepos called, and exception occurs, should call ShowError without empty list`(): Unit =
        runBlocking {
            val getRepoStates = viewModel.repos.test()
            whenever(getTreadingRepoUseCase.run(any()))
                .thenThrow()
            viewModel.getTreadingRepos()
            getRepoStates.assertValue(GetRepoStates.ShowError(false))
        }

    @Test
    @ExperimentalCoroutinesApi
    fun `verify when loadReposFromDb called, and data exist in db, should call LoadReposToUi`(): Unit = runBlocking {
        val getRepoStates = viewModel.repos.test()
        val dummyData = FakeDataProvider.getTreadingRepoObject()
        whenever(getReposFormDBUseCase.run(any()))
            .thenReturn(dummyData.repoList)
        viewModel.loadReposFromDb()
        getRepoStates.assertValue(GetRepoStates.LoadReposToUi(dummyData.repoList))
    }

    @Test
    @ExperimentalCoroutinesApi
    fun `verify when loadReposFromDb called, and data not exist in Db, should call ShowError with empty list`(): Unit =
        runBlocking {
            val getRepoStates = viewModel.repos.test()
            whenever(getReposFormDBUseCase.run(any()))
                .thenReturn(emptyList())
            viewModel.loadReposFromDb()
            getRepoStates.assertValue(GetRepoStates.ShowError(true))
        }

    @Test
    @ExperimentalCoroutinesApi
    fun `verify when loadReposFromDb called, and exception occurs, should call ShowError without empty list`(): Unit =
        runBlocking {
            val getRepoStates = viewModel.repos.test()
            whenever(getReposFormDBUseCase.run(any()))
                .thenThrow()
            viewModel.loadReposFromDb()
            getRepoStates.assertValue(GetRepoStates.ShowError(false))
        }
}
