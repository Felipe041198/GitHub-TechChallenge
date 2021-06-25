package com.example.githubrepos

import androidx.paging.PagingData
import com.example.githubrepos.api.GithubService
import com.example.githubrepos.model.Repository
import kotlinx.coroutines.flow.Flow

interface RepositoryInterface {
    fun getSearchResultStream(query: String): Flow<PagingData<Repository>>
}