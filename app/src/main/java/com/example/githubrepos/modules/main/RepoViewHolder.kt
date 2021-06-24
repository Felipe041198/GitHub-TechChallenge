package com.example.githubrepos.modules.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrepos.R
import com.example.githubrepos.model.Repository
import com.example.githubrepos.modules.details.DetailsActivity

class RepoViewHolder(view: View, navController: NavController) : RecyclerView.ViewHolder(view) {
    private val name: TextView = view.findViewById(R.id.repo_title)
    private val owner: TextView = view.findViewById(R.id.repo_owner)

    private var repo: Repository? = null

    init {
        view.setOnClickListener {
            navController.navigate(R.id.action_repositorySearchFragment_to_repositoryDetailsFragment)
        }
    }

    fun bind(repo: Repository?) {
        if (repo == null) {
            val resources = itemView.resources
            name.text = resources.getString(R.string.loading)
            owner.visibility = View.GONE
        } else {
            showRepoData(repo)
        }
    }

    private fun showRepoData(repo: Repository) {
        this.repo = repo
        name.text = repo.name
        owner.text = repo.owner?.login
    }

    companion object {
        fun create(parent: ViewGroup,navController: NavController): RepoViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.repos_view_item, parent, false)
            return RepoViewHolder(view,navController)
        }
    }




}