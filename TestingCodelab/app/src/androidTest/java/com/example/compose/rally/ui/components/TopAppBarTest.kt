package com.example.compose.rally.ui.components

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.compose.rally.RallyScreen
import com.example.compose.rally.ui.theme.RallyTheme
import org.junit.Rule
import org.junit.Test

class TopAppBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testTopBarSelection() {
        val allScreens = RallyScreen.values().toList()
        composeTestRule.setContent {
            RallyTheme {
                RallyTopAppBar(
                        allScreens = allScreens,
                        onTabSelected = {},
                        currentScreen = RallyScreen.Accounts
                )
            }
        }
        composeTestRule
                .onNodeWithContentDescription(RallyScreen.Accounts.name)
                .assertIsSelected()

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("currentLabelExists")

        listOf(RallyScreen.Bills, RallyScreen.Overview).forEach { screen ->
            composeTestRule
                    .onNodeWithContentDescription(screen.name)
                    .assertIsNotSelected()
        }
    }

    @Test
    fun testTopBarSelectedTextShown() {
        val allScreens = RallyScreen.values().toList()
        composeTestRule.setContent {
            RallyTheme {
                RallyTopAppBar(
                        allScreens = allScreens,
                        onTabSelected = {},
                        currentScreen = RallyScreen.Accounts
                )
            }
        }
        composeTestRule
                .onNode(
                        matcher = hasText(RallyScreen.Accounts.name.uppercase()) and
                                hasParent(
                                        hasContentDescription(RallyScreen.Accounts.name)
                                ),
                        useUnmergedTree = true
                )
                .assertExists()
    }
}