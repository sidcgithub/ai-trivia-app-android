package com.triviagenai.triviagen.feature.trivia.domain.usecase

import com.triviagenai.triviagen.feature.trivia.data.repository.TriviaRepositoryImpl
import com.triviagenai.triviagen.feature.trivia.domain.model.TriviaQuestion
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTriviaQuestionsUseCase @Inject constructor(private val repository: TriviaRepositoryImpl) {
    operator fun invoke(topic: String): Flow<List<TriviaQuestion>> {
        return repository.fetchTriviaQuestions(topic)
    }
}