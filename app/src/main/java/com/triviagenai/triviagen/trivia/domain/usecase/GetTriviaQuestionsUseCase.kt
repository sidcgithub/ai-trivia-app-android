package com.triviagenai.triviagen.trivia.domain.usecase

import com.triviagenai.triviagen.core.data.model.Resource
import com.triviagenai.triviagen.trivia.data.model.Round
import com.triviagenai.triviagen.trivia.domain.model.TriviaQuestion
import com.triviagenai.triviagen.trivia.domain.model.mapToTriviaQuestions
import com.triviagenai.triviagen.trivia.domain.repository.TriviaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTriviaQuestionsUseCase @Inject constructor(
    private val repository: TriviaRepository
) {
    operator fun invoke(topic: String): Flow<Result<List<TriviaQuestion>>?> {
        return repository.fetchTriviaQuestions(topic).map {
            if(it is Resource.Loading) {
                null
            } else {
                when (val result = it.data) {
                    is Round.TriviaRound -> Result.success(result.mapToTriviaQuestions())
                    else -> Result.failure(TriviaException())
                }
            }
        }
    }
}

class TriviaException(message: String = "Trivia could not be fetched") : Exception(message)