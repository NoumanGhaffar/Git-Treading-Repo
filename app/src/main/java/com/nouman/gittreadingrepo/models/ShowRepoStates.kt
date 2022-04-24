package com.nouman.gittreadingrepo.models

sealed class ShowRepoStates {
    data class LoadReposToUi(val repo: Repo) : ShowRepoStates()
}
