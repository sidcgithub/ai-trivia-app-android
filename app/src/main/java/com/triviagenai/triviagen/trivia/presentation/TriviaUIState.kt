package com.triviagenai.triviagen.trivia.presentation

import com.triviagenai.triviagen.trivia.domain.model.TriviaQuestion

data class TriviaUIState(
    val questions: List<TriviaQuestion> = emptyList(),
    val currentQuestionIndex: Int = 0,
    val score: Int = 0,
    val isLoading: Boolean = false,
    val error: String? = null
)