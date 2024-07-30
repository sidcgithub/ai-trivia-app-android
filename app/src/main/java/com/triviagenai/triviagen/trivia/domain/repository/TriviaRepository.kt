package com.triviagenai.triviagen.trivia.domain.repository

import com.triviagenai.triviagen.core.data.model.Resource
import com.triviagenai.triviagen.trivia.data.model.Round
import kotlinx.coroutines.flow.Flow

interface TriviaRepository {
    fun fetchTriviaQuestions(topic: String): Flow<Resource<out Round>>
}
