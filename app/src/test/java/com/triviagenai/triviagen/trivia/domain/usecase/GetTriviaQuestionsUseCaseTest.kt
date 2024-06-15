package com.triviagenai.triviagen.trivia.domain.usecase

import com.triviagenai.triviagen.core.testing.MainDispatcherRule
import com.triviagenai.triviagen.core.testing.repository.TestTriviaRepositoryImpl
import com.triviagenai.triviagen.trivia.domain.model.TriviaQuestion
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class GetTriviaQuestionsUseCaseTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val repository = TestTriviaRepositoryImpl()
    private val getTriviaQuestionsUseCase = GetTriviaQuestionsUseCase(repository)

    @Test
    fun `test Success scenario`() = runTest {
        var actual: List<TriviaQuestion>? = null
        getTriviaQuestionsUseCase("Geography").collectLatest { result ->
            result?.onSuccess { actual = it }
        }
        assertTrue(!actual.isNullOrEmpty())
    }

    @Test
    fun `test Error scenario`() = runTest {
        var actual: Throwable? = null
        getTriviaQuestionsUseCase("error").collectLatest { result ->
            result?.onFailure { actual = it }
        }
        assertNotNull(actual)
    }

}
