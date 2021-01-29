package com.example.graphspresso.ui.userProfile

import com.example.graphspresso.UserProfileQuery
import com.example.graphspresso.ui.base.ViewState

sealed class UserProfileViewState : ViewState() {
    data class OnUserResponse(val response: UserProfileQuery.Viewer?) :
        Loaded<UserProfileQuery.Viewer?>(response)

}