package com.nouman.gittreadingrepo.utils

interface CoroutineUseCase<Params, Result> {
    suspend fun run(params: Params): Result
}
