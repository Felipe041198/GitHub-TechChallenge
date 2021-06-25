package com.example.githubrepos

import android.app.Application
import com.example.githubrepos.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class GitHubTechChallengeApplication: Application() {

    override fun onCreate(){

        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@GitHubTechChallengeApplication)

            modules(mainModule)
        }
    }
}