package com.example.githubrepos

import android.app.Application
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import com.nostra13.universalimageloader.core.assist.QueueProcessingType


class GitHubTechChallengeApplication: Application() {

    override fun onCreate(){

        super.onCreate()

        val config: ImageLoaderConfiguration = ImageLoaderConfiguration.Builder(this)
            .threadPriority(Thread.NORM_PRIORITY - 2)
            .threadPoolSize(3)
            .diskCacheExtraOptions(480, 320, null)
            .tasksProcessingOrder(QueueProcessingType.LIFO)
            .build()

        ImageLoader.getInstance().init(config)
    }
}