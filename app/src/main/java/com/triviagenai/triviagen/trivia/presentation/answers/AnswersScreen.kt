package com.triviagenai.triviagen.trivia.presentation.answers

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.triviagenai.triviagen.core.presentation.TriviaGenScaffold
import com.triviagenai.triviagen.trivia.domain.model.TriviaQuestion
import com.triviagenai.triviagen.trivia.presentation.answers.components.TriviaAnswerCard

@Composable
fun AnswersScreen() {
    val trivias = listOf(
        TriviaQuestion("What?", listOf("1", "2"), 0),
        TriviaQuestion("What?", listOf("1", "2"), 0),
    )

    TriviaGenScaffold(backNavigationIcon = true) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(trivias.size) { index ->
                TriviaAnswerCard(trivias[index])
            }
        }
    }
}