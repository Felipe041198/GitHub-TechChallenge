package com.example.githubrepos

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
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
        fun newInstance() = RepositorySearchFragment()
        private const val LAST_SEARCH_QUERY: String = "last_search_query"
        private const val DEFAULT_QUERY = "Android"
    }

    private val viewModel: RepositorySearchViewModel by viewModel()

    private lateinit var binding: RepositorySearchFragmentBinding

    lateinit var adapter: RepositoryAdapter
    private var searchJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.repository_search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = RepositorySearchFragmentBinding.bind(view)

        setupViews(savedInstanceState)
        observeActions()

    }

    private fun refreshFragment() {
        viewModel.refreshFragment()
    }

    private fun observeActions() {
        viewModel.action.observe(viewLifecycleOwner, {
            when (it) {
                is RepositorySearchActions.LoadedList -> showDataState(it)
                is RepositorySearchActions.EmptyList -> showEmptyState()
                is RepositorySearchActions.Loading -> showLoadingState()
                is RepositorySearchActions.EmptySearch -> showEmptySearchState()
            }
        })
    }

    private fun showEmptySearchState() {
        binding.layoutMessage.visibility = View.VISIBLE
        binding.reposView.visibility = View.GONE
        binding.tvStateMessage.text = getString(R.string.empty_search)
    }

    private fun showLoadingState() {
        binding.layoutMessage.visibility = View.VISIBLE
        binding.reposView.visibility = View.GONE
        binding.tvStateMessage.text = getString(R.string.loading)
    }

    private fun showEmptyState() {
        binding.layoutMessage.visibility = View.VISIBLE
        binding.reposView.visibility = View.GONE
        binding.tvStateMessage.text = getString(R.string.empty_list)
    }

    private fun showDataState(it: RepositorySearchActions.LoadedList) {
        binding.layoutMessage.visibility = View.GONE
        binding.reposView.visibility = View.VISIBLE

        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            it.repositoryList.collectLatest { adapter.submitData(it) }
        }
    }


    private fun setupViews(state: Bundle?) {

        adapter = ReposAdapter(findNavController())
        binding.reposView.adapter = adapter

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
                viewModel.searchRepositories(it.toString())
            } else {
                viewModel.action.postValue(RepositorySearchActions.EmptySearch)
            }
        }
    }
}