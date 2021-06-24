package com.example.githubrepos.modules.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.githubrepos.Injection
import com.example.githubrepos.R

class DetailsActivity : AppCompatActivity() {
    private lateinit var viewModel: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.details_activity)
        viewModel = ViewModelProvider(this, Injection.provideViewModelFactory()).get(DetailsViewModel::class.java)

        initValues()
    }

    private fun initValues() {
        TODO("Not yet implemented")
    }
}