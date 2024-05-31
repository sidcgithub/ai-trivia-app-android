package com.triviagenai.triviagen

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.triviagenai.triviagen.trivia.presentation.answers.AnswersScreen
import com.triviagenai.triviagen.trivia.presentation.mainmenu.MainMenuScreen
import com.triviagenai.triviagen.trivia.presentation.results.ResultsScreen
import com.triviagenai.triviagen.trivia.presentation.roundsetup.RoundSetupScreen
import com.triviagenai.triviagen.trivia.presentation.triviagame.TriviaGameScreen
import com.triviagenai.triviagen.ui.theme.TriviaGenTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TriviaGenTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = MainManuRoute
                ) {
                    composable<MainManuRoute> {
                        MainMenuScreen(navController = navController)
                    }

                    composable<RoundSetupRoute> {
                        RoundSetupScreen(navController = navController)
                    }

                    composable<TriviaGameRoute> {
                        TriviaGameScreen(navController = navController)
                    }

                    composable<ResultsRoute> {
                        ResultsScreen(navController = navController)
                    }

                    composable<AnswersRoute> {
                        AnswersScreen(navController = navController)
                    }
                }
            }
        }
    }
}

@Serializable
object MainManuRoute

@Serializable
object RoundSetupRoute

@Serializable
object TriviaGameRoute

@Serializable
object ResultsRoute

@Serializable
object AnswersRoute