package com.example.githubrepos

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.PagingData
import com.example.githubrepos.api.GithubService
import com.example.githubrepos.model.Repository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.Flow
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var apiService: GithubService
    private lateinit var repositorySearchViewModel: RepositorySearchViewModel

    @Before
    fun setupTest() {
        apiService = mock()
        repositorySearchViewModel = mock()
    }

    @Test
    fun testSearchResult() {
        val searchKey: String = "test"
        val mockResult: Flow<PagingData<Repository>> = mock()
        whenever(repositorySearchViewModel.searchRepositories(searchKey)).thenReturn(mockResult)
        val result = repositorySearchViewModel.searchRepositories(searchKey)
        Assert.assertEquals(result, mockResult)
    }

}
