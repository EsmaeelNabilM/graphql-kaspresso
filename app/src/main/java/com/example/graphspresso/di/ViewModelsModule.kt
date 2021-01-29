package sa.gov.mos.di

import com.example.graphspresso.ui.repositoriesList.ReposViewModel
import com.example.graphspresso.ui.userProfile.UserProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ReposViewModel(get(), get()) }
    viewModel { UserProfileViewModel(get(), get()) }
}
