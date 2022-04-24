package com.nouman.gittreadingrepo.db.repositoryImp

import com.nouman.gittreadingrepo.db.dao.RepoOwnerDao
import com.nouman.gittreadingrepo.db.entity.RepoOwnerEntity
import com.nouman.gittreadingrepo.db.repository.RepoOwnerDbRepository
import com.nouman.gittreadingrepo.models.RepoOwner
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject


/**
 * RepoClient DB client
 */
class RepoOwnerRepositoryImpl @Inject constructor(
    private val repoOwnerDao: RepoOwnerDao
) : RepoOwnerDbRepository {
    override suspend fun getRepoOwner(repoOwnerId: Int): RepoOwner {
        return withContext(IO) {
            repoOwnerDao.getRepoOwner(repoOwnerId).run {
                RepoOwner(
                    name = this.name,
                    url = this.url,
                    id = this.id
                )
            }
        }
    }

    override suspend fun insert(repo: RepoOwner) {
        return withContext(IO) {
            repoOwnerDao.insert(RepoOwnerEntity(name = repo.name, url = repo.url, id = repo.id))
        }
    }
}
