package com.example.graphspresso.ui.userProfile

import com.example.graphspresso.ui.base.BaseViewModel
import com.example.graphspresso.utils.ContextProviders
import kotlinx.coroutines.flow.collect

class UserProfileViewModel(
    private val repository: UserProfileRepository,
    contextProviders: ContextProviders
) : BaseViewModel(contextProviders) {

    fun getUserProfile() = launchBlock {
        repository.getUserProfile()
            .collect {
                setState(UserProfileViewState.OnUserResponse(it))
            }
    }
}