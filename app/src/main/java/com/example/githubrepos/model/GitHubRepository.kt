package com.example.githubrepos.model

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface GitHubRepository {

    fun getSearchResultStream(query: String): Flow<PagingData<Repository>>
}