package com.example.githubrepos.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.githubrepos.model.RepositorySearchActions
import com.example.githubrepos.data.GithubRepository
import com.example.githubrepos.model.Repository
import kotlinx.coroutines.flow.Flow

class RepositorySearchViewModel (private val repository: GithubRepository) : ViewModel() {

    private var currentQueryValue: String? = null
    var currentSearchResult: Flow<PagingData<Repository>>? = null
    internal val action = MutableLiveData<RepositorySearchActions>()

    init {
        RepositorySearchActions.EmptyList.run()
    }

    fun searchRepositories(queryString: String) {


        val lastResult = currentSearchResult
        if (queryString == currentQueryValue && lastResult != null) {
            RepositorySearchActions.LoadedList(lastResult).run()
        } else {
            currentQueryValue = queryString
            val newResult: Flow<PagingData<Repository>> =
                repository.getSearchResultStream(queryString).cachedIn(viewModelScope)
            currentSearchResult = newResult

            RepositorySearchActions.LoadedList(newResult).run()
        }
    }

    private fun RepositorySearchActions.run() = action.postValue(this)

    fun refreshFragment() {
        if (currentSearchResult != null){
            RepositorySearchActions.LoadedList(currentSearchResult!!).run()
        }
    }

}