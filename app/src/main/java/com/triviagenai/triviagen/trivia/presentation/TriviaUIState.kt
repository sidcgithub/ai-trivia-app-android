package com.triviagenai.triviagen.trivia.presentation

import com.triviagenai.triviagen.trivia.domain.model.TriviaQuestion

sealed class TriviaUIState {
    object Loading : TriviaUIState()
    data class Success(val questions: List<TriviaQuestion>, val currentQuestionIndex: Int, val score: Int) : TriviaUIState()
    data class Error(val message: String) : TriviaUIState()
}
