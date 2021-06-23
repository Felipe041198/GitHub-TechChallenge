package com.example.githubrepos.modules.main

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrepos.R
import com.example.githubrepos.model.Repository

class RepoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val name: TextView = view.findViewById(R.id.repo_title)
    private val author: TextView = view.findViewById(R.id.repo_author)

    private var repo: Repository? = null

    init {
        view.setOnClickListener {
            //TODO
        }
    }

    fun bind(repo: Repository?) {
        if (repo == null) {
            val resources = itemView.resources
            name.text = resources.getString(R.string.loading)
            author.visibility = View.GONE
        } else {
            showRepoData(repo)
        }
    }

    private fun showRepoData(repo: Repository) {
        this.repo = repo
        name.text = repo.name
        author.text = repo.author?.login ?: "Missing Author"
    }

    companion object {
        fun create(parent: ViewGroup): RepoViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.repos_view_item, parent, false)
            return RepoViewHolder(view)
        }
    }
}