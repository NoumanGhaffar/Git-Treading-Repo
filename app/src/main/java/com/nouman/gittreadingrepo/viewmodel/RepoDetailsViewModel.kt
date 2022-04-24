package com.nouman.gittreadingrepo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nouman.gittreadingrepo.models.Repo
import com.nouman.gittreadingrepo.models.ShowRepoStates
import javax.inject.Inject

class RepoDetailsViewModel @Inject constructor() : ViewModel() {
    private val trendingRepo = MutableLiveData<ShowRepoStates>()
    val repo: LiveData<ShowRepoStates>
        get() = trendingRepo

    fun loadRepoDetails(repo: Repo) {
        trendingRepo.value = ShowRepoStates.LoadReposToUi(repo)
    }
}
