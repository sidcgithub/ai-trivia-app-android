package com.triviagenai.triviagen

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.triviagenai.options.FakeUserPreferenceRepository
import com.triviagenai.triviagen.core.presentation.navigation.NavGraph
import com.triviagenai.triviagen.core.presentation.navigation.Route
import com.triviagenai.triviagen.options.domain.usecase.GetDarkmodePreferenceUseCase
import com.triviagenai.triviagen.options.domain.usecase.SetDarkmodePreferenceUseCase
import com.triviagenai.triviagen.options.presentation.options.OptionsViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var navController: TestNavHostController
    private lateinit var optionsViewModel: OptionsViewModel
    private val repository = FakeUserPreferenceRepository()
    private val getDarkmodePreferenceUseCase = GetDarkmodePreferenceUseCase(repository)
    private val setDarkmodePreferenceUseCase = SetDarkmodePreferenceUseCase(repository)

    @Before
    fun setup() {
        optionsViewModel = OptionsViewModel(getDarkmodePreferenceUseCase, setDarkmodePreferenceUseCase)

        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(
                ComposeNavigator()
            )
            
            NavGraph(
                navController = navController,
                startDestination = Route.MainMenuRoute,
                optionsViewModel = optionsViewModel
            )
        }
    }

    @Test
    fun verifyStartDestination() {
        composeTestRule
            .onNodeWithContentDescription("MainMenuScreen")
            .assertIsDisplayed()
    }

    @Test
    fun navigation_clickQuickGame_navigatesToRoundSetup() {
        composeTestRule
            .onNodeWithText("Quick game")
            .performClick()

        composeTestRule
            .onNodeWithContentDescription("RoundSetupScreen")
            .assertIsDisplayed()
    }

    @Test
    fun navigation_clickStartRound_navigatesToTriviaGame() {
        composeTestRule
            .onNodeWithText("Quick game")
            .performClick()

        composeTestRule
            .onNodeWithText("Start Round")
            .performClick()

        composeTestRule
            .onNodeWithContentDescription("TriviaGameScreen")
            .assertIsDisplayed()
    }

    @Test
    fun navigation_clickRandomRound_navigatesToTriviaGame() {
        composeTestRule
            .onNodeWithText("Quick game")
            .performClick()

        composeTestRule
            .onNodeWithText("Random Round")
            .performClick()

        composeTestRule
            .onNodeWithContentDescription("TriviaGameScreen")
            .assertIsDisplayed()
    }
}