package com.example.githubrepos.model

import com.google.gson.annotations.SerializedName

data class Author(@field:SerializedName("login") val login: String,
                  @field:SerializedName("avatar_url") val avatar_url: String
                  )
