package com.example.githubrepos

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.paging.*
import com.example.githubrepos.api.GithubService
import com.example.githubrepos.api.RepoSearchResponse
import com.example.githubrepos.data.GithubRepository
import com.example.githubrepos.model.Owner
import com.example.githubrepos.model.Repository
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.flow.Flow
import okhttp3.internal.immutableListOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RepositorySearchViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: RepositorySearchViewModel

    @Mock
    private lateinit var searchObservable : Observer<Repository>

    @Mock
    private lateinit var actionObservable : Observer<RepositorySearchActions>

    @Test
    fun `when searchRepositories loadList`() {
        val testString = "Test"

        var currentSearchResult: Flow<PagingData<Repository>>? = null

        val mockRepository = MockRepository()

        viewModel = RepositorySearchViewModel(mockRepository)

        viewModel.action.observeForever(actionObservable)

        viewModel.searchRepositories(testString)

        val newResult: Flow<PagingData<Repository>> =
            mockRepository.getSearchResultStream(testString)

        verify(actionObservable).onChanged(RepositorySearchActions.LoadedList(newResult))
    }
}

class MockRepository: GithubRepository(MockService())

class MockService() : GithubService {
    override suspend fun searchRepos(
        query: String,
        page: Int,
        itemsPerPage: Int
    ): RepoSearchResponse {
        val owner =
            Owner(111, "Login", "Test URL Avatar")

        val list = immutableListOf(
            Repository(1111, "Test Name", owner, "Test Description", "Test URL")
        )

        return RepoSearchResponse(1, list, 2)
    }

}
