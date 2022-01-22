package com.example.compose.rally.ui.overview

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class OverviewScreenTest{

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun alertsDisplayed(){
        composeTestRule.setContent {
            OverviewBody()
        }

        composeTestRule
                .onNodeWithText("Alerts")
                .assertIsDisplayed()
    }
}