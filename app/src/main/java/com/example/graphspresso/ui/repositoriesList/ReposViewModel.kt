package com.example.graphspresso.ui.repositoriesList

import com.example.graphspresso.type.OrderDirection
import com.example.graphspresso.type.RepositoryOrderField
import com.example.graphspresso.ui.base.BaseViewModel
import com.example.graphspresso.utils.ContextProviders
import kotlinx.coroutines.flow.collect

class ReposViewModel(
    private val repository: ReposRepository,
    contextProviders: ContextProviders
) : BaseViewModel(contextProviders) {

    fun getRepositoriesList(
        repositoriesCount: Int,
        orderBy: RepositoryOrderField,
        orderDirection: OrderDirection
    ) = launchBlock {
        repository
            .getRepositoriesList(repositoriesCount, orderBy, orderDirection)
            .collect {
                setState(ReposViewState.OnReposListResponse(it))
            }
    }
}