package com.example.githubrepos.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.githubrepos.model.GitHubRepository
import com.example.githubrepos.model.Repository
import kotlinx.coroutines.flow.Flow

open class GithubRepositoryImpl(private val service: GithubService) : GitHubRepository {

    override fun getSearchResultStream(query: String): Flow<PagingData<Repository>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { GithubPagingSource(service, query) }
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 50
    }
}