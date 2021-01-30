package com.example.graphspresso.ui.scenarios

import com.example.graphspresso.testUtils.goto
import com.example.graphspresso.ui.MainActivity
import com.example.graphspresso.ui.userProfile.UserProfileScreen
import com.kaspersky.kaspresso.testcases.api.scenario.Scenario
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext

/**
 * Created by esmaeel on Jan, 2021
 */

/**
 *  withEndNavigation : determines if this scenario with navigate at the end or not.
 */
class UserProfileTestScenario(private val withEndNavigation: Boolean = false) : Scenario() {

    val userScreen = UserProfileScreen()

    override val steps: TestContext<Unit>.() -> Unit = {
        step("open the main activity") {
            goto(MainActivity::class.java)
        }

        userScreen {
            step("make sure user profile is visible and has data") {
                flakySafely(5_000) {
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
                        isVisible()
                        isClickable()
                        containsText("4")
                    }

                }
            }

            step("navigate if this scenario is used in another scenarios") {
                if (withEndNavigation)
                    flakySafely(2_000) {
                        reposCount.click()
                    }
            }
        }
    }
}