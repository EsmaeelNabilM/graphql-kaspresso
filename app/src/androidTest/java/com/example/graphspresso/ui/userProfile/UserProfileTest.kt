package com.example.graphspresso.ui.userProfile

import com.example.graphspresso.ui.scenarios.UserProfileTestScenario
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Test


class UserProfileFragmentTest : TestCase() {

    @Test
    fun user_profile_test_case() = run {
        step("run user Profile test scenario without navigating to another screens") {
            scenario(UserProfileTestScenario(withEndNavigation = false))
        }
    }
}