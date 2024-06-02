package com.triviagenai.triviagen.trivia.presentation.answers

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.navigation.NavHostController
import com.triviagenai.triviagen.core.presentation.TriviaGenScaffold
import com.triviagenai.triviagen.trivia.domain.model.TriviaQuestion
import com.triviagenai.triviagen.trivia.presentation.answers.components.TriviaAnswerCard

@Composable
fun AnswersScreen(navController: NavHostController) {
    val trivias = listOf(
        TriviaQuestion("What is the largest animal?", listOf("Option", "Correct answer", "Option", "Wrong answer"), 1),
        TriviaQuestion("When was the prehistory?", listOf("1", "2", "Never", "Tomorrow"), 0),
    )

    TriviaGenScaffold(
        backNavigationIcon = true,
        navController = navController
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .semantics { contentDescription = "AnswersScreen" }
        ) {
            items(trivias.size) { index ->
                TriviaAnswerCard(trivias[index])
            }
        }
    }
}