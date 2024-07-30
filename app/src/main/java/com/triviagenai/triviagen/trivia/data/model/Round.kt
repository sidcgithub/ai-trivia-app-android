package com.triviagenai.triviagen.trivia.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true, generator = "sealed:type")
sealed class Round {
    data class TriviaRound(
        val category: String,
        val questions: List<Question>
    ) : Round()

    data class Error(
        val message: String
    ) : Round()
}

@JsonClass(generateAdapter = true)
data class Question(
    val question: String,
    val options: List<String>,
    val answer: Int
)