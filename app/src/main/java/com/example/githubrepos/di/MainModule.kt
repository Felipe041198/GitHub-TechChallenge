package com.example.githubrepos.di

import com.example.githubrepos.viewmodel.RepositorySearchViewModel
import com.example.githubrepos.data.GithubService
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
}