package com.triviagenai.triviagen.trivia.presentation.answers

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.triviagenai.triviagen.core.presentation.TriviaGenScaffold
import com.triviagenai.triviagen.trivia.presentation.TriviaQuestionViewModel
import com.triviagenai.triviagen.trivia.presentation.TriviaUIState
import com.triviagenai.triviagen.trivia.presentation.answers.components.TriviaAnswerCard

@Composable
fun AnswersScreen(triviaQuestionViewModel: TriviaQuestionViewModel) {
    val trivia = triviaQuestionViewModel.uiState.collectAsState().value.let {
        if (it is TriviaUIState.Success) {
            it.questions
        } else {
            emptyList()
        }
    }

    TriviaGenScaffold(backNavigationIcon = true) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(trivia.size) { index ->
                TriviaAnswerCard(trivia[index])
            }
        }
    }
}