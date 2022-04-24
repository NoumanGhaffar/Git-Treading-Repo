package com.nouman.gittreadingrepo.usecase

import com.nouman.gittreadingrepo.db.repository.RepoDbRepository
import com.nouman.gittreadingrepo.models.Repo
import com.nouman.gittreadingrepo.utils.Constants
import com.nouman.gittreadingrepo.utils.CoroutineUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class GetReposFormDbUseCase @Inject constructor(
    private val repoDbRepository: RepoDbRepository
) : CoroutineUseCase<Unit, List<Repo>> {
    @Named(Constants.IO)
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    override suspend fun run(params: Unit): List<Repo> {
        return withContext(ioDispatcher) {
            repoDbRepository.getAll()
        }
    }
}
