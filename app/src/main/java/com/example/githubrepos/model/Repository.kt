package com.example.githubrepos.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Repository(
    @field:SerializedName("id") val id: Long,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("owner") val owner: Owner,
    @field:SerializedName("description") val description: String?,
    @field:SerializedName("html_url") val url: String,
) : Parcelable
