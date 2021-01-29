package com.example.graphspresso.ui.userProfile


import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.navigation.findNavController
import coil.api.load
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
            avatar.load(response?.avatarUrl.toString())
            name.text = response?.name
            bio.text = response?.bio
            repos.text = "Repository"



            with(reposCount){
                text = response?.repositories?.totalCount.toString()
                setOnClickListener {
                    findNavController().navigate(R.id.RepositoriesListFragment)
                }
            }
        }
    }
}


