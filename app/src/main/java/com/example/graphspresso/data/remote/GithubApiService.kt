package com.example.graphspresso.data.remote

import com.example.graphspresso.GithubRepositoriesQuery
import com.example.graphspresso.UserProfileQuery
import com.example.graphspresso.type.OrderDirection
import com.example.graphspresso.type.RepositoryOrderField

interface GithubApiService {
    suspend fun getRepositoriesList(
        repositoriesCount: Int,
        orderBy: RepositoryOrderField,
        orderDirection: OrderDirection
    ): GithubRepositoriesQuery.Data?


    suspend fun getUserProfile(): UserProfileQuery.Viewer?
}

