package com.nouman.gittreadingrepo.models.response

import com.example.seezassignment.network.response.BaseResponse
import com.google.gson.annotations.SerializedName
import com.nouman.gittreadingrepo.models.RepoOwner

data class RepoOwnerResponse(
    @SerializedName("login")
    val name: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("avatar_url")
    val url: String,
) : BaseResponse<RepoOwner>() {
    override fun toModel() = RepoOwner(name, id, url)

}