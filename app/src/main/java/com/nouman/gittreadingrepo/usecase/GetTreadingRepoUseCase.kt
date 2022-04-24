package com.nouman.gittreadingrepo.usecase

import com.nouman.gittreadingrepo.models.TrendingRepos
import com.nouman.gittreadingrepo.repos.GitApiRepository
import com.nouman.gittreadingrepo.utils.Constants.IO
import com.nouman.gittreadingrepo.utils.CoroutineUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class GetTreadingRepoUseCase @Inject constructor(
    private val gitRepository: GitApiRepository
) : CoroutineUseCase<Unit, TrendingRepos> {
    @Named(IO) private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    override suspend fun run(params: Unit): TrendingRepos {
        return withContext(ioDispatcher) { gitRepository.getTrendingRepos() }
    }
}
