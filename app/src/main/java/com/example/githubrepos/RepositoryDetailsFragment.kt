package com.example.githubrepos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.githubrepos.databinding.RepositoryDetailsFragmentBinding
import com.nostra13.universalimageloader.core.DisplayImageOptions
import com.nostra13.universalimageloader.core.ImageLoader


class RepositoryDetailsFragment : Fragment() {

    private val args by navArgs<RepositoryDetailsFragmentArgs>()

    private lateinit var binding: RepositoryDetailsFragmentBinding

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

        binding = RepositoryDetailsFragmentBinding.bind(view)

        Glide.with(this).load(args.currentRepo.owner.avatar_url).into(binding.userImage)

        binding.repoTitle.text = args.currentRepo.name
        binding.repoOwner.text = args.currentRepo.owner.login
        binding.repoDescription.text = args.currentRepo.description

    }
}