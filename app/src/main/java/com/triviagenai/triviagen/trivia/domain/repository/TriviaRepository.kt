package com.triviagenai.triviagen.trivia.domain.repository

import com.triviagenai.triviagen.trivia.domain.model.TriviaQuestion
import kotlinx.coroutines.flow.Flow

interface TriviaRepository {
    fun fetchTriviaQuestions(topic: String): Flow<List<TriviaQuestion>>
}
