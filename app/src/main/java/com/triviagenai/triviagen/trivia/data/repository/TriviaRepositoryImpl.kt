package com.triviagenai.triviagen.trivia.data.repository

import com.triviagenai.triviagen.core.data.api.TriviaGenApiService
import com.triviagenai.triviagen.trivia.data.model.Resource
import com.triviagenai.triviagen.trivia.data.model.Round
import com.triviagenai.triviagen.trivia.domain.repository.TriviaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TriviaRepositoryImpl @Inject constructor(private val apiService: TriviaGenApiService) :
    TriviaRepository {

    override fun fetchTriviaQuestions(topic: String): Flow<Resource<out Round>> = flow {
        try {
            emit(Resource.Loading())
            val result = when (val response = apiService.getTriviaQuestions(topic)) {
                is Round.TriviaRound -> Resource.Success(data = response)
                is Round.Error -> Resource.Error(errorMessage = response.message)
                else -> Resource.Error("Something went wrong...")
            }
            emit(result)
        } catch (e: Exception) {
            emit(Resource.Error("Exception"))
        }
    }
}