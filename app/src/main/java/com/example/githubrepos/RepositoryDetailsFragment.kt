package com.example.githubrepos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.nostra13.universalimageloader.core.DisplayImageOptions
import com.nostra13.universalimageloader.core.ImageLoader
import kotlinx.android.synthetic.main.list_repos_activity.*
import kotlinx.android.synthetic.main.repository_details_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RepositoryDetailsFragment : Fragment() {

    private val args by navArgs<RepositoryDetailsFragmentArgs>()

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(RepositoryDetailsViewModel::class.java)

        val options = DisplayImageOptions.Builder()
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .build()

        ImageLoader.getInstance().displayImage(args.currentRepo.owner.avatar_url, user_image, options);

        repo_title.text = args.currentRepo.name
        repo_owner.text = args.currentRepo.owner.login
        repo_description.text = args.currentRepo.description

    }
}