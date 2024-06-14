package com.triviagenai.triviagen.trivia.presentation

import com.triviagenai.triviagen.core.testing.MainDispatcherRule
import com.triviagenai.triviagen.core.testing.repository.TestTriviaRepositoryImpl
import com.triviagenai.triviagen.trivia.domain.usecase.GetTriviaQuestionsUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TriviaQuestionViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val triviaRepository = TestTriviaRepositoryImpl()
    private val triviaQuestionsUseCase = GetTriviaQuestionsUseCase(triviaRepository)

    private lateinit var viewModel: TriviaQuestionViewModel

    @Before
    fun setUp() {
        viewModel = TriviaQuestionViewModel(triviaQuestionsUseCase)
    }

    @Test
    fun testFetchTriviaQuestions() = runTest {
        // Test with valid topic
        viewModel.fetchTriviaQuestions("History")
        assert(viewModel.uiState.value is TriviaUIState.Success)

        // Test with invalid topic
        viewModel.fetchTriviaQuestions("")
        assert(viewModel.uiState.value is TriviaUIState.Error)
    }

    @Test
    fun testProcessIntent() = runTest {
        viewModel.processIntent(
            TriviaIntent.SubmitAnswer(0)
                    assert (viewModel.uiState.value is TriviaUIState.Success)

                    viewModel . processIntent (TriviaIntent.RandomTriviaRound)
                    assert (viewModel.uiState.value is TriviaUIState.Success)
    }
}