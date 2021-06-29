package com.example.githubrepos.view

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class RepositoryLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<RepositoryLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: RepositoryLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): RepositoryLoadStateViewHolder {
        return RepositoryLoadStateViewHolder.create(parent, retry)
    }
}
