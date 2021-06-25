package com.example.githubrepos.model

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

sealed class RepositorySearchActions {
    object Loading : RepositorySearchActions()
    object EmptyList : RepositorySearchActions()
    data class LoadedList(var repositoryList: Flow<PagingData<Repository>>) : RepositorySearchActions()
    object EmptySearch : RepositorySearchActions()
}
