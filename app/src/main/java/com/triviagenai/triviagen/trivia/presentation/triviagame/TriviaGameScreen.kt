package com.triviagenai.triviagen.trivia.presentation.triviagame

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavHostController
import com.triviagenai.triviagen.core.presentation.TriviaGenScaffold
import com.triviagenai.triviagen.core.presentation.navigation.NavigationStatus
import com.triviagenai.triviagen.trivia.presentation.TriviaQuestionViewModel
import com.triviagenai.triviagen.trivia.presentation.TriviaUIState
import com.triviagenai.triviagen.trivia.presentation.triviagame.components.DisplayGameContent

@Composable
fun TriviaGameScreen(
    triviaQuestionViewModel: TriviaQuestionViewModel,
    navController: NavHostController
) {
    val modifier = Modifier.testTag("TriviaGameScreen")
    val triviaRound by triviaQuestionViewModel.uiState.collectAsState()
    var selectedIndex by remember { mutableIntStateOf(-1) }
    TriviaGenScaffold(
        navigationStatus = NavigationStatus.None
    ) {
        when (triviaRound) {
            is TriviaUIState.Success -> DisplayGameContent(
                triviaRound,
                selectedIndex,
                { index -> selectedIndex = index },
                triviaQuestionViewModel,
                navController,
                modifier
            )

            is TriviaUIState.Error -> Box(modifier = modifier.fillMaxSize()) {
                Text(
                    text = (triviaRound as TriviaUIState.Error).message,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            is TriviaUIState.Loading -> Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "Loading...",
                    modifier = modifier.align(Alignment.Center)
                )
            }
        }
    }
}
