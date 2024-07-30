package com.triviagenai.triviagen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.triviagenai.triviagen.core.presentation.navigation.NavGraph
import com.triviagenai.triviagen.core.presentation.navigation.Route
import com.triviagenai.triviagen.options.presentation.options.OptionsViewModel
import com.triviagenai.triviagen.ui.theme.TriviaGenTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val optionsViewModel: OptionsViewModel = hiltViewModel()
            val isDarkTheme = optionsViewModel.darkmodeState.collectAsState().value
            val navController = rememberNavController()

            TriviaGenTheme(
                darkTheme = isDarkTheme
            ) {
                NavGraph(
                    navController = navController,
                    startDestination = Route.MainMenuRoute,
                    optionsViewModel = optionsViewModel
                )
            }
        }
    }
}