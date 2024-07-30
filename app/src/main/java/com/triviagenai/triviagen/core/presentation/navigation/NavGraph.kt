package com.triviagenai.triviagen.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.triviagenai.triviagen.options.presentation.options.OptionsScreen
import com.triviagenai.triviagen.options.presentation.options.OptionsViewModel
import com.triviagenai.triviagen.trivia.presentation.TriviaQuestionViewModel
import com.triviagenai.triviagen.trivia.presentation.answers.AnswersScreen
import com.triviagenai.triviagen.trivia.presentation.mainmenu.MainMenuScreen
import com.triviagenai.triviagen.trivia.presentation.results.ResultsScreen
import com.triviagenai.triviagen.trivia.presentation.roundsetup.RoundSetupScreen
import com.triviagenai.triviagen.trivia.presentation.triviagame.TriviaGameScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: Route,
    optionsViewModel: OptionsViewModel
) {
    val triviaQuestionViewModel: TriviaQuestionViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<Route.MainMenuRoute> {
            MainMenuScreen(
                navController = navController
            )
        }

        composable<Route.RoundSetupRoute> {
            RoundSetupScreen(
                navController = navController,
                triviaQuestionViewModel = triviaQuestionViewModel
            )
        }

        composable<Route.TriviaGameRoute> {
            TriviaGameScreen(
                navController = navController,
                triviaQuestionViewModel = triviaQuestionViewModel
            )
        }

        composable<Route.ResultsRoute> {
            ResultsScreen(
                triviaQuestionViewModel = triviaQuestionViewModel,
                navController = navController
            )
        }

        composable<Route.AnswersRoute> {
            AnswersScreen(
                triviaQuestionViewModel = triviaQuestionViewModel,
                navController = navController
            )
        }

        composable<Route.OptionsScreen> {
            OptionsScreen(
                navController = navController,
                optionsViewModel = optionsViewModel
            )
        }
    }
}