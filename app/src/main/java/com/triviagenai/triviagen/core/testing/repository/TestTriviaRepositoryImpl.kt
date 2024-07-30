package com.triviagenai.triviagen.core.testing.repository

import com.triviagenai.triviagen.core.data.model.Resource
import com.triviagenai.triviagen.trivia.data.model.Question
import com.triviagenai.triviagen.trivia.data.model.Round
import com.triviagenai.triviagen.trivia.domain.repository.TriviaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TestTriviaRepositoryImpl @Inject constructor() : TriviaRepository {

    private val triviaRound1: Round.TriviaRound = Round.TriviaRound(
        category = "Geography",
        questions = listOf(
            Question(
                question = "What is the capital of France?",
                options = listOf("London", "Paris", "Madrid", "Berlin"),
                answer = 1
            ),
            Question(
                question = "What is the capital of Germany?",
                options = listOf("London", "Paris", "Madrid", "Berlin"),
                answer = 3
            ),
            Question(
                question = "What is the capital of Spain?",
                options = listOf("London", "Paris", "Madrid", "Berlin"),
                answer = 2
            ),
            Question(
                question = "What is the capital of England?",
                options = listOf("London", "Paris", "Madrid", "Berlin"),
                answer = 0
            ),
            Question(
                question = "What is the capital of Italy?",
                options = listOf("London", "Paris", "Madrid", "Berlin"),
                answer = 1
            ),
        )
    )

    private val errorRound: Round.Error = Round.Error("Error fetching questions")
    override fun fetchTriviaQuestions(topic: String): Flow<Resource<out Round>> = flow {
        try {
            emit(Resource.Loading())
            val result = when (val response = if (topic != "error") triviaRound1 else errorRound) {
                is Round.TriviaRound -> Resource.Success(data = response)
                is Round.Error -> Resource.Error(errorMessage = response.message)
            }
            emit(result)
        } catch (e: Exception) {
            emit(Resource.Error("Exception"))
        }
    }
}