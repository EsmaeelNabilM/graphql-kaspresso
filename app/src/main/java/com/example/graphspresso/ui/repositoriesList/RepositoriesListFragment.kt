package com.example.graphspresso.ui.repositoriesList

import android.widget.Toast
import com.example.graphspresso.GithubRepositoriesQuery
import com.example.graphspresso.R
import com.example.graphspresso.databinding.ActivityMainBinding
import com.example.graphspresso.databinding.RepositoriesListFragmentBinding
import com.example.graphspresso.type.OrderDirection
import com.example.graphspresso.type.RepositoryOrderField
import com.example.graphspresso.ui.base.BaseFragment
import com.example.graphspresso.ui.base.ViewState
import com.example.graphspresso.utils.EndlessRecyclerOnScrollListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class RepositoriesListFragment :
    BaseFragment<RepositoriesListFragmentBinding, ReposViewModel>(R.layout.repositories_list_fragment) {

    private val TAG = "MainActivity"

    override val viewModel: ReposViewModel by viewModel()
    lateinit var reposAdapter: ReposAdapter

    private val paginationListener = object : EndlessRecyclerOnScrollListener() {
        override fun onLoadMore(currentPage: Int, lastVisibleItemPosition: Int) {
            requestRepos(lastCount = reposAdapter.itemCount)
        }
    }

    override fun setup() {
        reposAdapter = ReposAdapter {
            Toast.makeText(
                requireContext(),
                it.fragments.repositoryFragment.name,
                Toast.LENGTH_SHORT
            ).show()
        }


        binder.recycler.apply {
            adapter = reposAdapter
            addOnScrollListener(paginationListener)
        }

        requestRepos(lastCount = reposAdapter.itemCount)
    }

    private fun requestRepos(lastCount: Int = 0) {
        viewModel.getRepositoriesList(
            repositoriesCount = lastCount + 10,
            orderBy = RepositoryOrderField.STARGAZERS,
            orderDirection = OrderDirection.DESC
        )
    }

    override fun render(state: ViewState) {
        when (state) {
            is ReposViewState.OnReposListResponse -> bindList(state.response?.viewer?.repositories?.nodes)
        }
    }

    private fun bindList(response: List<GithubRepositoriesQuery.Node?>?) {
        reposAdapter.submitList(response)
    }
}


