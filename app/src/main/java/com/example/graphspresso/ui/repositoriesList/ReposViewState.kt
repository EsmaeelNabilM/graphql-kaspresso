package com.example.graphspresso.ui.repositoriesList

import com.example.graphspresso.GithubRepositoriesQuery
import com.example.graphspresso.ui.base.ViewState

sealed class ReposViewState : ViewState() {
    data class OnReposListResponse(val response: GithubRepositoriesQuery.Data?) :
        Loaded<GithubRepositoriesQuery.Data?>(response)
}