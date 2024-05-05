package com.triviagenai.triviagen.feature.trivia.domain.repository

import com.triviagenai.triviagen.feature.trivia.domain.model.TriviaQuestion
import kotlinx.coroutines.flow.Flow

interface TriviaRepository {
    fun fetchTriviaQuestions(topic: String): Flow<List<TriviaQuestion>>
}
