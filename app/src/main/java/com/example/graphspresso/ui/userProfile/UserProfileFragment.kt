package com.example.graphspresso.ui.userProfile


import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider
import androidx.navigation.findNavController
import coil.load
import com.example.graphspresso.R
import com.example.graphspresso.UserProfileQuery
import com.example.graphspresso.databinding.UserProfileFragmentBinding
import com.example.graphspresso.ui.base.BaseFragment
import com.example.graphspresso.ui.base.ViewState
import org.koin.androidx.viewmodel.ext.android.viewModel


class UserProfileFragment :
    BaseFragment<UserProfileFragmentBinding, UserProfileViewModel>(R.layout.user_profile_fragment) {

    override val viewModel: UserProfileViewModel by viewModel()


    override fun setup() {
        viewModel.getUserProfile()
    }

    override fun render(state: ViewState) {
        when (state) {
            is UserProfileViewState.OnUserResponse -> bindUser(state.response)
        }
    }

    private fun bindUser(response: UserProfileQuery.Viewer?) {
        with(binder) {
            avatar.setCircleElevation()
            avatar.load(response?.avatarUrl.toString())
            name.text = response?.name
            bio.text = response?.bio
            repos.text = "Repository"



            with(reposCount) {
                visibility = View.VISIBLE
                text = response?.repositories?.totalCount.toString()
                setOnClickListener {
                    findNavController().navigate(
                        UserProfileFragmentDirections.actionUserProfileFragmentToRepositoriesListFragment(
                            username = response?.name ?: "",
                            reposCount = response?.repositories?.totalCount ?: 0
                        )
                    )
                }
            }
        }
    }
}

fun View.setCircleElevation() {
    outlineProvider = object : ViewOutlineProvider() {
        override fun getOutline(view: View, outline: Outline) {
            outline.setOval(10, 10, view.width, view.height)
        }
    }
    clipToOutline = true
}



