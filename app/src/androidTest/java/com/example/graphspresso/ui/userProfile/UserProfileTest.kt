package com.example.graphspresso.ui.userProfile

import com.example.graphspresso.testUtils.goto
import com.example.graphspresso.ui.MainActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Test


class UserProfileFragmentTest : TestCase() {

    val userScreen = UserProfileScreen()

    @Test
    fun user_profile_test_case() = run {
        step("open the main activity") {
            goto(MainActivity::class.java)
        }

        userScreen {
            step("make sure user profile is visible") {
                flakySafely(16_000) {
                    avatar.isVisible()

                    name {
                        isVisible()
                        hasAnyText()
                    }

                    bio {
                        isVisible()
                        hasAnyText()
                    }

                    repos {
                        isVisible()
                        hasAnyText()
                    }

                    reposCount {
                        isInvisible()
                        hasText("48")
                    }
                }
            }
        }
    }
}