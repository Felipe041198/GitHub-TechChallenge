package com.example.githubrepos.view

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.githubrepos.R
import com.example.githubrepos.viewmodel.RepositorySearchViewModel
import com.example.githubrepos.databinding.RepositorySearchFragmentBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositorySearchFragment : Fragment() {

    companion object {
        private const val LAST_SEARCH_QUERY: String = "last_search_query"
        private const val DEFAULT_QUERY = "GitHub"}

    private val viewModel: RepositorySearchViewModel by viewModel()

    private lateinit var binding: RepositorySearchFragmentBinding

    private lateinit var adapter: RepositoryAdapter
    private var searchJob: Job? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.repository_search_fragment, container, false)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(LAST_SEARCH_QUERY, binding.inputSearch.text!!.trim().toString())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = RepositorySearchFragmentBinding.bind(view)

        initAdapter()

        val query = savedInstanceState?.getString(LAST_SEARCH_QUERY) ?: DEFAULT_QUERY
        searchData(query)
        initListener(query)
    }



    private fun searchData(query: String) {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.searchRepositories(query).collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun initAdapter() {
        adapter = RepositoryAdapter(findNavController())

        binding.reposView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = RepositoryLoadStateAdapter { adapter.retry() },
            footer = RepositoryLoadStateAdapter { adapter.retry() }
        )

        adapter.addLoadStateListener { loadState ->

            val isEmpty = loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0
            showEmptyList(isEmpty)
            binding.reposView.isVisible = loadState.source.refresh is LoadState.NotLoading
            binding.layoutLoading.isVisible = loadState.source.refresh is LoadState.Loading
        }
    }

    private fun showEmptyList(isEmpty: Boolean) {
        if (isEmpty){
            binding.layoutEmpty.visibility = View.VISIBLE
            binding.reposView.visibility = View.GONE
        } else {
            binding.layoutEmpty.visibility = View.GONE
            binding.reposView.visibility = View.VISIBLE
        }
    }


    private fun initListener(query: String) {
        binding.inputSearch.setText(query)

        binding.inputSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                searchRepoByQuery()
                true
            } else {
                false
            }
        }
        binding.inputSearch.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                searchRepoByQuery()
                true
            } else {
                false
            }
        }

        lifecycleScope.launch {
            adapter.loadStateFlow
                .distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collect { binding.reposView.scrollToPosition(0) }
        }
    }

    private fun searchRepoByQuery() {
        binding.inputSearch.text!!.trim().let {
            if (it.isNotEmpty()) {
                searchData(it.toString())
            }
        }
    }
}