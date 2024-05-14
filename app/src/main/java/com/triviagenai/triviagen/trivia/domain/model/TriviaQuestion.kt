package com.triviagenai.triviagen.trivia.domain.model

data class TriviaQuestion(
    val question: String,
    val options: List<String>,
    val answer: Int
)
