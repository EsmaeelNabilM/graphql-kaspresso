package com.example.graphspresso.di

import com.example.graphspresso.ui.repositoriesList.ReposRepository
import com.example.graphspresso.ui.userProfile.UserProfileRepository
import org.koin.dsl.module


val repositoryModule = module {
    single { ReposRepository(get(), get()) }
    single { UserProfileRepository(get(), get()) }
}
