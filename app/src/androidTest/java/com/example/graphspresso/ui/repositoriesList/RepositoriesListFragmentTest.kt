package com.example.graphspresso.ui.repositoriesList

import com.example.graphspresso.ui.scenarios.UserReposListScenario
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Test


class RepositoriesListFragmentTest : TestCase() {

    @Test
    fun user_repositories_list_test_case() = run {
        step("run user repositories test scenario as a standalone test scenario") {
            scenario(UserReposListScenario(standAloneTest = true))
        }
    }
}