package com.example.githubrepos.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrepos.R
import com.example.githubrepos.model.Repository

class RepositoryViewHolder(view: View, navController: NavController) : RecyclerView.ViewHolder(view) {
    private val name: TextView = view.findViewById(R.id.repo_title)
    private val owner: TextView = view.findViewById(R.id.repo_owner)

    private var repo: Repository? = null

    init {
        view.setOnClickListener {
            navController.navigate(
                RepositorySearchFragmentDirections.actionRepositorySearchFragmentToRepositoryDetailsFragment(
                    repo!!
                )
            )
        }
    }

    fun bind(repo: Repository?) {
        if (repo == null) {
            val resources = itemView.resources
            name.text = resources.getString(R.string.loading)
            owner.visibility = View.GONE
        } else {
            showRepositoryData(repo)
        }
    }

    private fun showRepositoryData(repo: Repository) {
        this.repo = repo
        name.text = repo.name
        owner.text = repo.owner.login
    }

    companion object {
        fun create(parent: ViewGroup, navController: NavController): RepositoryViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.repos_view_item, parent, false)
            return RepositoryViewHolder(view, navController)
        }
    }


}