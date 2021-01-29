package com.example.graphspresso.data.remote

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.await
import com.example.graphspresso.GithubRepositoriesQuery
import com.example.graphspresso.UserProfileQuery
import com.example.graphspresso.type.OrderDirection
import com.example.graphspresso.type.RepositoryOrderField

class GithubApiServiceImpl(private val client: ApolloClient) : GithubApiService {

    override suspend fun getRepositoriesList(
        repositoriesCount: Int,
        orderBy: RepositoryOrderField,
        orderDirection: OrderDirection
    ): GithubRepositoriesQuery.Data? {

        return client.query(
            GithubRepositoriesQuery(repositoriesCount, orderBy, orderDirection)
        ).await().data

    }

    override suspend fun getUserProfile(): UserProfileQuery.Viewer? {
        return client.query(
            UserProfileQuery()
        ).await().data?.viewer
    }


}

