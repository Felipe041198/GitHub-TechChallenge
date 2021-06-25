package com.example.githubrepos

import android.R.attr.key
import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource.LoadParams
import com.example.githubrepos.api.GithubService
import com.example.githubrepos.api.RepoSearchResponse
import com.example.githubrepos.data.GithubPagingSource
import com.example.githubrepos.model.Owner
import com.example.githubrepos.model.Repository
import com.nhaarman.mockitokotlin2.verify
import org.junit.Rule
import org.junit.Test


class GitHubPagingSourceTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var pagingSource: GithubPagingSource

    @Test
    fun `when pagingsource load getData then sets loadResult`() {
        val testString = "Test"
        val service = MockService()

        val onSuccess: Consumer<LoadResult<Int, Item>> = mock()
        val onError: Consumer<Throwable> = mock()
        val params: LoadParams<Int> = mock()

        pagingSource = GithubPagingSource(service,testString)

        pagingSource.load(params)

        verify(MockService)
    }
}

class MockService() : GithubService {
    override suspend fun searchRepos(
        query: String,
        page: Int,
        itemsPerPage: Int
    ): RepoSearchResponse {
        val owner =
            Owner(111, "Login", "Test URL Avatar")

        val list = listOf(
            Repository(1111, "Test Name", owner, "Test Description", "Test URL")
        )

        return RepoSearchResponse(1, list, 2)
    }

}

