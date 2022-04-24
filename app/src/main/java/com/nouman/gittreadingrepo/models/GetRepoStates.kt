package com.nouman.gittreadingrepo.models

sealed class GetRepoStates {
    data class LoadReposToUi(val repoList: List<Repo>) : GetRepoStates()
    object ShowLoading : GetRepoStates()
    //Can create Enums for different type of errors
    data class ShowError(val isEmptyList: Boolean) : GetRepoStates()

}
