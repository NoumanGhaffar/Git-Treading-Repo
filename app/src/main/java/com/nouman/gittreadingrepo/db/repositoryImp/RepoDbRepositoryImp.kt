package com.nouman.gittreadingrepo.db.repositoryImp

import com.nouman.gittreadingrepo.db.dao.RepoDao
import com.nouman.gittreadingrepo.db.dao.RepoOwnerDao
import com.nouman.gittreadingrepo.db.entity.RepoEntity
import com.nouman.gittreadingrepo.db.entity.RepoOwnerEntity
import com.nouman.gittreadingrepo.db.repository.RepoDbRepository
import com.nouman.gittreadingrepo.models.Repo
import com.nouman.gittreadingrepo.models.RepoOwner
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject


/**
 * RepoClient DB client
 */
class RepoDbRepositoryImp @Inject constructor(
    private val repoDao: RepoDao,
    private val repoOwnerDao: RepoOwnerDao
) : RepoDbRepository {

    override suspend fun getAll(): List<Repo> {
        return withContext(IO) {
            repoDao.getAll().map {
                val owner = it.repoOwnerId?.let { id ->
                    repoOwnerDao.getRepoOwner(id)
                }
                Repo(
                    repoId = it.repoId,
                    nodeId = it.nodeId,
                    fullName = it.fullName,
                    description = it.description,
                    language = it.language,
                    forks = it.forks,
                    starsCount = it.starsCount,
                    url = it.url,
                    repoOwner = owner?.let { entity -> RepoOwner(entity.name, entity.id, entity.url) }
                )
            }
        }
    }

    override suspend fun insert(repo: Repo) {
        return withContext(IO) {
            repo.repoOwner?.let {
                repoOwnerDao.insert(RepoOwnerEntity(it.id, it.name, it.url))
            }
            repoDao.insert(
                RepoEntity(
                    repoId = repo.repoId,
                    nodeId = repo.nodeId,
                    fullName = repo.fullName,
                    description = repo.description,
                    language = repo.language,
                    forks = repo.forks,
                    starsCount = repo.starsCount,
                    url = repo.url,
                    repoOwnerId = repo.repoOwner?.id
                )
            )
        }
    }
}
