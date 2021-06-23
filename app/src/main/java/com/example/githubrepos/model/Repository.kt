package com.example.githubrepos.model

import com.google.gson.annotations.SerializedName

data class Repository(
    @field:SerializedName("id") val id: Long,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("full_name") val full_name: String,
    @field:SerializedName("description") val description: String?,
    @field:SerializedName("owner") val author: Author?
)
