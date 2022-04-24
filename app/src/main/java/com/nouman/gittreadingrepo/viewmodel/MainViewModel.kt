package com.nouman.gittreadingrepo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nouman.gittreadingrepo.models.GetRepoStates
import com.nouman.gittreadingrepo.models.Repo
import com.nouman.gittreadingrepo.usecase.GetReposFormDbUseCase
import com.nouman.gittreadingrepo.usecase.GetTreadingRepoUseCase
import com.nouman.gittreadingrepo.usecase.InsertReposUseCase
import com.nouman.gittreadingrepo.utils.exceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainViewModel @Inject constructor(
    private val getTreadingRepoUseCase: GetTreadingRepoUseCase,
    private val insertReposUseCase: InsertReposUseCase,
    private val getReposFormDBUseCase: GetReposFormDbUseCase
) : ViewModel() {

    private val trendingRepos = MutableLiveData<GetRepoStates>()
    val repos: LiveData<GetRepoStates>
        get() = trendingRepos

    fun getTreadingRepos() {
        getTrendingReposFromServer()
    }

    private fun getTrendingReposFromServer() {
        val exceptionHandler = viewModelScope.exceptionHandler {
            trendingRepos.value = GetRepoStates.ShowError(false)
        }
        viewModelScope.launch(exceptionHandler) {
            trendingRepos.value = GetRepoStates.ShowLoading
            val response = getTreadingRepoUseCase.run(Unit)
            handleResponse(response.repoList)
        }
    }

    private fun handleResponse(list: List<Repo>) {
        if (list.isNotEmpty()) {
            trendingRepos.value = GetRepoStates.LoadReposToUi(list)
            insertInDb(list)
        } else {
            trendingRepos.value = GetRepoStates.ShowError(true)
        }
    }

    private fun insertInDb(repoList: List<Repo>) {
        val exceptionHandler = viewModelScope.exceptionHandler {
           it.printStackTrace()
        }
        viewModelScope.launch(exceptionHandler) {
            insertReposUseCase.run(repoList)
        }
    }

    fun loadReposFromDb() {
        val exceptionHandler = viewModelScope.exceptionHandler {
            trendingRepos.value = GetRepoStates.ShowError(false)
        }
        viewModelScope.launch(exceptionHandler) {
            val list = getReposFormDBUseCase.run(Unit)
            handleResponse(list)
        }
    }
}
