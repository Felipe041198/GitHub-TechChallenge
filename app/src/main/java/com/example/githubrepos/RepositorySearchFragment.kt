package com.example.githubrepos

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.githubrepos.modules.main.ReposAdapter
import kotlinx.android.synthetic.main.list_repos_activity.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import kotlin.collections.emptyList

class RepositorySearchFragment : Fragment() {

    companion object {
        fun newInstance() = RepositorySearchFragment()
        private const val LAST_SEARCH_QUERY: String = "last_search_query"
        private const val DEFAULT_QUERY = "Android"
    }

    private lateinit var viewModel: RepositorySearchViewModel

    lateinit var adapter: ReposAdapter
    private var searchJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.repository_search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this,Injection.provideViewModelFactory()).get(RepositorySearchViewModel::class.java)

        adapter = ReposAdapter(findNavController())

        initAdapter()
        val query = savedInstanceState?.getString(RepositorySearchFragment.LAST_SEARCH_QUERY) ?: RepositorySearchFragment.DEFAULT_QUERY
        search(query)
        initSearch(query)
    }

    private fun search(query: String) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.searchRepo(query).collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun initAdapter() {
        repos_view.adapter = adapter
    }

    private fun initSearch(query: String) {
        input_search.setText(query)

        input_search.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                updateRepoListFromInput()
                true
            } else {
                false
            }
        }
        input_search.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                updateRepoListFromInput()
                true
            } else {
                false
            }
        }

        lifecycleScope.launch {
            adapter.loadStateFlow
                .distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collect { repos_view.scrollToPosition(0) }
        }
    }

    private fun updateRepoListFromInput() {
        input_search.text.trim().let {
            if (it.isNotEmpty()) {
                search(it.toString())
            }
        }
    }

    private fun showEmptyList(show: Boolean) {
        if (show) {
            emptyList.visibility = View.VISIBLE
            repos_view.visibility = View.GONE
        } else {
            emptyList.visibility = View.GONE
            repos_view.visibility = View.VISIBLE
        }
    }
}