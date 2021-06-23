package com.example.githubrepos.api

import com.example.githubrepos.model.Repository
import com.google.gson.annotations.SerializedName
import com.google.gson.*

data class RepoSearchResponse(
    @SerializedName("total_count") val total: Int = 0,
    @SerializedName("items") val items: List<Repository> = emptyList(),
    val nextPage: Int? = null
)
