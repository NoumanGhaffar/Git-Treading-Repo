package com.nouman.gittreadingrepo.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "repo_owner")
class RepoOwnerEntity(
    @PrimaryKey ()
    val id: Int,
    val name: String,
    val url: String,
)
