package com.example.githubrepos.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Owner(
    @field:SerializedName("id") val id: Long,
    @field:SerializedName("login") val login: String,
    @field:SerializedName("avatar_url") val avatar_url: String
) : Parcelable
