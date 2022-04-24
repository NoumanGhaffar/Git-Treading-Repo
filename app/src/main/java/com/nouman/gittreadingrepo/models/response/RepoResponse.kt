package com.nouman.gittreadingrepo.models.response

import com.example.seezassignment.network.response.BaseResponse
import com.google.gson.annotations.SerializedName
import com.nouman.gittreadingrepo.models.Repo
import com.nouman.gittreadingrepo.models.TrendingRepos

data class RepoResponse(
    @SerializedName("id")
    var id: Int,
    @SerializedName("node_id")
    var nodeId: String,
    @SerializedName("full_name")
    var fullName: String?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("language")
    var language: String?,
    @SerializedName("forks")
    var forks: Int,
    @SerializedName("stargazers_count")
    var starsCount: Int,
    @SerializedName("html_url")
    var url: String?,
    @SerializedName("owner")
    var owner: RepoOwnerResponse?
): BaseResponse<Repo>() {
    override fun toModel() =
        Repo(repoId = id, nodeId, fullName, description, language, forks, starsCount, url, owner?.toModel())
}