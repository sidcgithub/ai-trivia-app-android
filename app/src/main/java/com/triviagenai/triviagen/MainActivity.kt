package com.triviagenai.triviagen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.triviagenai.triviagen.core.presentation.navigation.NavGraph
import com.triviagenai.triviagen.core.presentation.navigation.Route
import com.triviagenai.triviagen.ui.theme.TriviaGenTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TriviaGenTheme {
                val navController = rememberNavController()
                BackHandler {
                    // Do nothing
                }
                NavGraph(
                    navController = navController,
                    startDestination = Route.MainMenuRoute,
                )
            }
        }
    }
}