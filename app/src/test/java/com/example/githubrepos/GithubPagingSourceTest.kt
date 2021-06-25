package com.example.githubrepos

import android.content.ClipData
import androidx.paging.PagingSource
import com.example.githubrepos.api.GithubService
import com.example.githubrepos.data.GithubPagingSource
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import java.util.function.Consumer

class GithubPagingSourceTest {

        private val itemList = emptyList<Any>()

        private lateinit var source: GithubPagingSource

        private val service: GithubService = mock()

        @Before
        fun `set up`() {
        }

        @Test
        fun `getItems - should delegate to service`() {
            val onSuccess: Consumer<PagingSource.LoadResult<Int, ClipData.Item>> = mock()
            val onError: Consumer<Throwable> = mock()
            val params: PagingSource.LoadParams<Int> = mock()

            verifyZeroInteractions(onError)
        }
    }