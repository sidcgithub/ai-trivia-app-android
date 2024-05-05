package com.triviagenai.triviagen.feature.trivia.domain.model

data class TriviaQuestion(
    val question: String,
    val options: List<String>,
    val answer: String
)
