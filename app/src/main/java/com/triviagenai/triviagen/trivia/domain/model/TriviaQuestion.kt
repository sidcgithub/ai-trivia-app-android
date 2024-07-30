package com.triviagenai.triviagen.trivia.domain.model

import com.triviagenai.triviagen.trivia.data.model.Round

data class TriviaQuestion(
    val question: String,
    val options: List<String>,
    val answer: Int,
    val selectedAnswer: SelectedAnswerState = SelectedAnswerState.Unanswered,
)

fun Round.TriviaRound.mapToTriviaQuestions() = questions.map { question ->
    TriviaQuestion(
        question.question,
        question.options,
        question.answer
    )
}

sealed class SelectedAnswerState {
    object Unanswered : SelectedAnswerState()
    data class Answered(val answer: Int) : SelectedAnswerState()
}
