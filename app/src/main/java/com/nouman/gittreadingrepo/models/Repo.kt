package com.nouman.gittreadingrepo.models


import android.os.Parcelable
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Repo(
    var repoId: Int,
    var nodeId: String,
    var fullName: String?,
    var description: String?,
    var language: String?,
    var forks: Int,
    var starsCount: Int,
    val url: String?,
    var repoOwner: RepoOwner?
) : Parcelable {
    companion object {
        @JvmStatic
        @BindingAdapter("profileImage")
        fun loadImage(view: AppCompatImageView, profileImage: String) {
            Glide.with(view.context)
                .load(profileImage)
                .into(view)
        }
    }
}
