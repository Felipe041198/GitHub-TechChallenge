package com.example.githubrepos

import androidx.paging.PagingData
import com.example.githubrepos.model.Repository
import kotlinx.coroutines.flow.Flow

sealed class RepositorySearchActions {
    object Loading : RepositorySearchActions()
    object EmptyList : RepositorySearchActions()
    data class LoadedList(var repositoryList: Flow<PagingData<Repository>>) : RepositorySearchActions()
    object EmptySearch : RepositorySearchActions()
}
