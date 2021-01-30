package com.example.graphspresso.ui.repositoriesList

import android.view.View
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.text.KSnackbar
import com.agoda.kakao.text.KTextView
import com.example.graphspresso.GithubRepositoriesQuery
import com.example.graphspresso.R
import com.kaspersky.kaspresso.screens.KScreen
import org.hamcrest.Matcher

class RepositoriesListScreen() : KScreen<RepositoriesListScreen>() {
    override val layoutId: Int = R.layout.repositories_list_fragment
    override val viewClass: Class<*> = RepositoriesListFragment::class.java

    val name = KTextView { withId(R.id.username) }
    val reposCount = KTextView { withId(R.id.userReposCount) }


    val itemsRecycler = KRecyclerView(
        builder = { withId(R.id.recycler) },
        itemTypeBuilder = {
            itemType(::Item)
        }
    )

    class Item(parent: Matcher<View>) : KRecyclerItem<GithubRepositoriesQuery.Node>(parent) {
        val name = KTextView(parent) { withId(R.id.name) }
        val snackbar = KSnackbar()
    }


}