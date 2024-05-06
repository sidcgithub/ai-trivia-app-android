package com.triviagenai.triviagen.trivia.data.repository

import com.triviagenai.triviagen.core.data.api.TriviaGenApiService
import com.triviagenai.triviagen.trivia.domain.model.TriviaQuestion
import com.triviagenai.triviagen.trivia.domain.repository.TriviaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TriviaRepository @Inject constructor(private val apiService: TriviaGenApiService) :
    TriviaRepository {

    override fun fetchTriviaQuestions(category: String): Flow<List<TriviaQuestion>> = flow {
        apiService.getTriviaQuestions(category)
    }
}