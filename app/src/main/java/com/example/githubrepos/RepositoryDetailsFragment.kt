package com.example.githubrepos

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class RepositoryDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = RepositoryDetailsFragment()
    }

    private lateinit var viewModel: RepositoryDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.repository_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RepositoryDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}