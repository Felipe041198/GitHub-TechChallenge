package com.example.githubrepos.di

import com.example.githubrepos.RepositoryDetailsViewModel
import com.example.githubrepos.RepositorySearchViewModel
import com.example.githubrepos.api.GithubService
import com.example.githubrepos.data.GithubRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {

    single {
        GithubService.create()
    }
    single {
        GithubRepository(service = get())
    }
    viewModel {
        RepositorySearchViewModel(
            repository = get()
        )
    }
    viewModel{
        RepositoryDetailsViewModel()
    }
}