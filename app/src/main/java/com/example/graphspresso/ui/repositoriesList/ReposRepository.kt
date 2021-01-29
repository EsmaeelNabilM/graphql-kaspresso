package com.example.graphspresso.ui.repositoriesList

import com.example.graphspresso.data.remote.GithubApiService
import com.example.graphspresso.type.OrderDirection
import com.example.graphspresso.type.RepositoryOrderField
import com.example.graphspresso.ui.base.BaseRepository
import com.example.graphspresso.utils.ContextProviders

class ReposRepository(
    contextProviders: ContextProviders,
    private val githubApiService: GithubApiService
) : BaseRepository(contextProviders) {

    fun getRepositoriesList(
        repositoriesCount: Int,
        orderBy: RepositoryOrderField,
        orderDirection: OrderDirection
    ) = networkHandler {
        githubApiService.getRepositoriesList(repositoriesCount, orderBy, orderDirection)
    }
}