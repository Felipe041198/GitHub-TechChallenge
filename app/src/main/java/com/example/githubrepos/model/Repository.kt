package com.example.githubrepos.model

import com.google.gson.annotations.SerializedName

data class Repository(
    @field:SerializedName("id") val id: Long,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("owner") val author: Author,
    @field:SerializedName("description") val description: String?,
)
