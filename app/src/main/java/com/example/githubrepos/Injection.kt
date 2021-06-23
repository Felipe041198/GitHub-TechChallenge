package com.example.githubrepos

import androidx.lifecycle.ViewModelProvider
import com.example.githubrepos.api.GithubService
import com.example.githubrepos.data.GithubRepository
import com.example.githubrepos.modules.main.ViewModelFactory

object Injection {

    private fun provideGithubRepository(): GithubRepository {
        return GithubRepository(GithubService.create())
    }

    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return ViewModelFactory(provideGithubRepository())
    }
}