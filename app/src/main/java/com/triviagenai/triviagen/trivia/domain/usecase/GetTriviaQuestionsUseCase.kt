package com.triviagenai.triviagen.trivia.domain.usecase

import com.triviagenai.triviagen.trivia.domain.model.TriviaQuestion
import com.triviagenai.triviagen.trivia.domain.repository.TriviaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTriviaQuestionsUseCase @Inject constructor(
    private val repository: TriviaRepository
) {
    operator fun invoke(topic: String): Flow<List<TriviaQuestion>> {
        return repository.fetchTriviaQuestions(topic)
    }
}