package com.triviagenai.triviagen

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class NavigationTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun verifyStartDestination() {
        composeTestRule.onNodeWithTag("MainMenuScreen")
            .assertIsDisplayed()
    }

    @Test
    fun navigation_clickQuickGame_navigatesToRoundSetup() {
        composeTestRule
            .onNodeWithText("Quick game")
            .performClick()

        composeTestRule
            .onNodeWithTag("RoundSetupScreen")
            .assertIsDisplayed()
    }

    @Test
    fun navigation_clickStartRound_navigatesToTriviaGame() {
        composeTestRule
            .onNodeWithText("Quick game")
            .performClick()

        composeTestRule
            .onNodeWithTag("StartRoundButton")
            .performClick()

        composeTestRule
            .onNodeWithTag("TriviaGameScreen")
            .assertIsDisplayed()
    }

    @Test
    fun navigation_clickRandomRound_navigatesToTriviaGame() {
        composeTestRule
            .onNodeWithText("Quick game")
            .performClick()

        composeTestRule
            .onNodeWithTag("RandomRoundButton")
            .performClick()

        composeTestRule
            .onNodeWithTag("TriviaGameScreen")
            .assertIsDisplayed()
    }

    @Test
    fun navigation_clickTopBarBackButtonInTriviaGameScreen_navigatesToRoundSetupScreen() {
        composeTestRule
            .onNodeWithText("Quick game")
            .performClick()

        composeTestRule
            .onNodeWithTag("RandomRoundButton")
            .performClick()

        composeTestRule
            .onNodeWithTag("TopAppBarBackNavigationButton")
            .performClick()

        composeTestRule
            .onNodeWithTag("QuitTriviaAlertDialogConfirmButton")
            .performClick()

        composeTestRule
            .onNodeWithTag("RoundSetupScreen")
            .assertIsDisplayed()

        composeTestRule
            .activityRule.scenario.onActivity { activity ->
                activity.onBackPressedDispatcher.onBackPressed()
            }

        composeTestRule
            .onNodeWithTag("MainMenuScreen")
            .assertIsDisplayed()
    }
}