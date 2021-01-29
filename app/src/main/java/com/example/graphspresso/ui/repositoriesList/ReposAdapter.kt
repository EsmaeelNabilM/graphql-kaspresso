package com.example.graphspresso.ui.repositoriesList

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.graphspresso.GithubRepositoriesQuery
import com.example.graphspresso.databinding.RepoItemBinding
import com.example.graphspresso.fragment.RepositoryFragment

class ReposAdapter(
    private val onItemClicked: (GithubRepositoriesQuery.Node) -> Unit,
) : ListAdapter<GithubRepositoriesQuery.Node, ReposAdapter.ItemHolder>(
    ItemDiffUtil
) {

    private object ItemDiffUtil :
        DiffUtil.ItemCallback<GithubRepositoriesQuery.Node>() {
        override fun areItemsTheSame(
            oldItem: GithubRepositoriesQuery.Node,
            newItem: GithubRepositoriesQuery.Node
        ) = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: GithubRepositoriesQuery.Node,
            newItem: GithubRepositoriesQuery.Node
        ) = oldItem.fragments.repositoryFragment.id == newItem.fragments.repositoryFragment.id
    }

    inner class ItemHolder(private val binder: RepoItemBinding) :
        RecyclerView.ViewHolder(binder.root) {
        @SuppressLint("SetTextI18n")
        fun bindViews(itemModel: GithubRepositoriesQuery.Node) {
            with(binder) {
                name.text = itemModel.fragments.repositoryFragment.name
                description.text = itemModel.fragments.repositoryFragment.description
                created.text = "Created at : ${itemModel.fragments.repositoryFragment.createdAt}"
                updated.text = "Updated at : ${itemModel.fragments.repositoryFragment.updatedAt}"
                stars.text =
                    "Stars : ${itemModel.fragments.repositoryFragment.stargazers.totalCount}"
                langs.text =
                    "Languages : ${getLanguages(itemModel.fragments.repositoryFragment.languages?.nodes)}"
                root.setOnClickListener { onItemClicked(itemModel) }
            }
        }

        private fun getLanguages(nodes: List<RepositoryFragment.Node?>?): String {
            var item = ""
            nodes?.forEach {
                item = "$item, ${it?.name}"
            }

            return item.replaceFirst(", ", "")
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            RepoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bindViews(getItem(position))
    }

}