package com.nouman.gittreadingrepo.db.entity
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repo")
class RepoEntity(
    @PrimaryKey
    val repoId: Int,
    val nodeId: String,
    val fullName: String?,
    val description: String?,
    val language: String?,
    val forks: Int,
    val starsCount: Int,
    val url: String?,
    val repoOwnerId: Int?
)
