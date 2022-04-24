package com.nouman.gittreadingrepo

import com.nouman.gittreadingrepo.db.entity.RepoEntity
import com.nouman.gittreadingrepo.db.entity.RepoOwnerEntity
import com.nouman.gittreadingrepo.models.Repo
import com.nouman.gittreadingrepo.models.RepoOwner
import com.nouman.gittreadingrepo.models.TrendingRepos

object FakeDataProvider {

    fun getTreadingRepoObject(): TrendingRepos {
        val list = mutableListOf<Repo>()
        repeat(10) { index ->
            list.add(
                Repo(
                    index,
                    "1000$index",
                    "test$index",
                    "test desc$index",
                    index.toString(),
                    index,
                    index,
                    "www.testurl.com/$index",
                    RepoOwner("test$index", index, "www.test.com/$index")
                )
            )
        }
        return TrendingRepos(10, list)
    }

    fun getRepoEntitiesList(): List<RepoEntity> {
        val list = mutableListOf<RepoEntity>()
        repeat(10) { index ->
            list.add(
                RepoEntity(
                    index,
                    "1000$index",
                    "test$index",
                    "test desc$index",
                    index.toString(),
                    index,
                    index,
                    "www.testurl.com/$index",
                    index
                )
            )
        }
        return list
    }

    fun getRepoOwnerEntity(index: Int =0) = RepoOwnerEntity(index, "test$index", "www.test.com/$index")

    fun getRepoOwnerObject(index: Int =0) = RepoOwner("test$index", index, "www.test.com/$index")
}
