package com.nouman.gittreadingrepo.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RepoOwner(
    val name: String,
    val id: Int,
    val url: String,
): Parcelable
