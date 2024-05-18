package com.triviagenai.triviagen.trivia.domain.model

import com.triviagenai.triviagen.trivia.data.model.Round

data class TriviaQuestion(
    val question: String,
    val options: List<String>,
    val answer: String,
    val points : Int = 5,
)

fun Round.TriviaRound.mapToTriviaQuestions() = questions.map { question ->
    TriviaQuestion(
        question.question,
        question.options,
        question.options[question.answer]
    )
}
