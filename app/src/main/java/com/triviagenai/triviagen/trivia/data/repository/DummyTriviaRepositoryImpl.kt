package com.triviagenai.triviagen.trivia.data.repository

import com.triviagenai.triviagen.core.data.api.TriviaGenApiService
import com.triviagenai.triviagen.trivia.domain.repository.TriviaRepository
import com.triviagenai.triviagen.trivia.domain.model.TriviaQuestion
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DummyTriviaRepositoryImpl @Inject constructor() : TriviaRepository {
    private var cachedQuestions: List<TriviaQuestion>? = null

    override fun fetchTriviaQuestions(topic: String): Flow<List<TriviaQuestion>> = flow {
        delay(1000)
        cachedQuestions = listOf(
            TriviaQuestion(
                question = "What is the chemical symbol for the element oxygen?",
                options = listOf("O", "Ox", "Om", "Op"),
                answer = "O"
            ),
            TriviaQuestion(
                question = "Which planet is known as the Red Planet?",
                options = listOf("Earth", "Mars", "Jupiter", "Venus"),
                answer = "Mars"
            )
        )
        emit(cachedQuestions!!)
    }
}