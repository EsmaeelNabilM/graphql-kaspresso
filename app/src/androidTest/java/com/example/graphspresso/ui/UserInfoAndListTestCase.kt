package com.example.graphspresso.ui

import com.example.graphspresso.ui.scenarios.UserProfileTestScenario
import com.example.graphspresso.ui.scenarios.UserReposListScenario
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Test

/**
 * Created by esmaeel on Jan, 2021
 */

class UserInfoAndListTestCase : TestCase() {

    @Test
    fun test_app_scenarios() = run {
        step("run user info scenario with navigation") {
            scenario(UserProfileTestScenario(withEndNavigation = true))
        }

        step("run user repositories list test scenario") {
            scenario(UserReposListScenario(standAloneTest = false))
        }
    }
}