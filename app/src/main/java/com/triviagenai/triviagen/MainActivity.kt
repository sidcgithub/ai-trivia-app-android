package com.triviagenai.triviagen

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.triviagenai.triviagen.trivia.presentation.TriviaQuestionViewModel
import com.triviagenai.triviagen.trivia.presentation.answers.AnswersScreen
import com.triviagenai.triviagen.trivia.presentation.results.ResultsScreen
import com.triviagenai.triviagen.trivia.presentation.roundsetup.RoundSetupScreen
import com.triviagenai.triviagen.trivia.presentation.triviagame.TriviaGameScreen
import com.triviagenai.triviagen.ui.theme.TriviaGenTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TriviaGenTheme {
                val triviaQuestionViewModel: TriviaQuestionViewModel = hiltViewModel()
                var screenNumber by remember {
                    mutableIntStateOf(0)
                }
                Box(
                    Modifier
                        .fillMaxSize()
                        .clickable { if (++screenNumber > 3) screenNumber = 0 }) {
                    when (screenNumber) {
                        0 -> RoundSetupScreen(triviaQuestionViewModel)
                        1 -> TriviaGameScreen(triviaQuestionViewModel)
                        2 -> ResultsScreen(triviaQuestionViewModel)
                        3 -> AnswersScreen(triviaQuestionViewModel)
                    }
                }
            }
        }
    }
}