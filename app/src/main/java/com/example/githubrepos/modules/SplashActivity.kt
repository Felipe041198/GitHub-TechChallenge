package com.example.githubrepos.modules

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.githubrepos.modules.main.ListReposActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        startActivity(Intent(this, ListReposActivity::class.java))
        finish()
    }
}