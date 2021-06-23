package com.example.githubrepos.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.githubrepos.api.GithubService
import com.example.githubrepos.model.Repository
import kotlinx.coroutines.flow.Flow

private const val GITHUB_STARTING_PAGE_INDEX = 1

/**
 * Repository class that works with local and remote data sources.
 */
class GithubRepository(private val service: GithubService) {
    /**
     * Search repositories whose names match the query, exposed as a stream of data that will emit
     * every time we get more data from the network.
     */
    fun getSearchResultStream(query: String): Flow<PagingData<Repository>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { GithubPagingSource(service, query) }
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 10
    }
}