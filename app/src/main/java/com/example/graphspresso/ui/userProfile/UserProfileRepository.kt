package com.example.graphspresso.ui.userProfile

import com.example.graphspresso.data.remote.GithubApiService
import com.example.graphspresso.ui.base.BaseRepository
import com.example.graphspresso.utils.ContextProviders

class UserProfileRepository(
    contextProviders: ContextProviders,
    private val githubApiService: GithubApiService
) : BaseRepository(contextProviders) {

    fun getUserProfile() = networkHandler {
        githubApiService.getUserProfile()
    }
}