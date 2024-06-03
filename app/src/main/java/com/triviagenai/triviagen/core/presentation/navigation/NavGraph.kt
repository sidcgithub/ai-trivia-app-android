package com.triviagenai.triviagen.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.triviagenai.triviagen.trivia.presentation.answers.AnswersScreen
import com.triviagenai.triviagen.trivia.presentation.mainmenu.MainMenuScreen
import com.triviagenai.triviagen.trivia.presentation.results.ResultsScreen
import com.triviagenai.triviagen.trivia.presentation.roundsetup.RoundSetupScreen
import com.triviagenai.triviagen.trivia.presentation.triviagame.TriviaGameScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: Route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<Route.MainMenuRoute> {
            MainMenuScreen(navController = navController)
        }

        composable<Route.RoundSetupRoute> {
            RoundSetupScreen(navController = navController)
        }

        composable<Route.TriviaGameRoute> {
            TriviaGameScreen(navController = navController)
        }

        composable<Route.ResultsRoute> {
            ResultsScreen(navController = navController)
        }

        composable<Route.AnswersRoute> {
            AnswersScreen(navController = navController)
        }
    }
}