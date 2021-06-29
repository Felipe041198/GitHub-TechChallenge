package com.example.githubrepos.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.githubrepos.data.GithubRepository
import com.example.githubrepos.model.Repository
import kotlinx.coroutines.flow.Flow

class RepositorySearchViewModel(private val repository: GithubRepository) : ViewModel() {

    private var currentQueryValue: String? = null
    var currentSearchResult: Flow<PagingData<Repository>>? = null

    fun searchRepositories(queryString: String): Flow<PagingData<Repository>> {

        val lastResult = currentSearchResult
        if (queryString == currentQueryValue && lastResult != null) {
            return lastResult
        }
        currentQueryValue = queryString
        val newResult: Flow<PagingData<Repository>> =
            repository.getSearchResultStream(queryString).cachedIn(viewModelScope)
        currentSearchResult = newResult

        return newResult
    }
}