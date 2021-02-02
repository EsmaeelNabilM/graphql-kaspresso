package com.example.graphspresso.ui.scenarios

import com.example.graphspresso.testUtils.getText
import com.example.graphspresso.ui.repositoriesList.RepositoriesListScreen
import com.kaspersky.kaspresso.testcases.api.scenario.Scenario
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext
import io.kotlintest.matchers.numerics.shouldBeGreaterThan

/**
 * Created by esmaeel on Jan, 2021
 */

class UserReposListScenario(private val standAloneTest: Boolean = true) : Scenario() {

    val listScreen = RepositoriesListScreen()

    override val steps: TestContext<Unit>.() -> Unit = {
        step(
            """check for if this is a stand alone test scenario
               to open the main activity and run the previous scenarios or not""".trimIndent()
        ) {
            if (standAloneTest) {
                scenario(UserProfileTestScenario(withEndNavigation = true))
            }
        }


        listScreen {

            step("make sure username and repos count has been passed right") {
                name {
                    isVisible()
                    hasAnyText()
                }
                reposCount {
                    isVisible()
                    hasAnyText()
                }
            }


            step("check for data has been arrived from API") {
                itemsRecycler {
                    step("size should be bigger than 0") {
                        getSize().shouldBeGreaterThan(0)
                    }

                    step("make sure first item is visible and has data") {
                        childAt<RepositoriesListScreen.Item>(0) {
                            name {
                                isVisible()
                                hasAnyText()
                            }
                        }
                    }
                }
            }

            step("make sure items is clickable") {
                itemsRecycler.childAt<RepositoriesListScreen.Item>(0) {
                    flakySafely(1_000) {
                        name.isClickable()
                    }
                }
            }


            step("make sure Snackbar shows after clicking on any item and shows the repo name") {
                itemsRecycler.childAt<RepositoriesListScreen.Item>(0) {

                    name.click()

                    flakySafely(2_000) {
                        snackbar.isVisible()
                        val nameString = name.getText()
                        snackbar.text.hasText(nameString)
                    }
                }
            }

            step("make sure pagination is working") {
                val oldSize = itemsRecycler.getSize()
                itemsRecycler.scrollToEnd()
                flakySafely(5_000) {
                    val newSize = itemsRecycler.getSize()
                    newSize shouldBeGreaterThan oldSize
                }

            }

        }


    }

}
