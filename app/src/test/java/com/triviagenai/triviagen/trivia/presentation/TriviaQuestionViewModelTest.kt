package com.triviagenai.triviagen.trivia.presentation

import com.triviagenai.triviagen.core.testing.MainDispatcherRule
import com.triviagenai.triviagen.core.testing.repository.TestTriviaRepositoryImpl
import com.triviagenai.triviagen.trivia.domain.model.SelectedAnswerState
import com.triviagenai.triviagen.trivia.domain.usecase.GetTriviaQuestionsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
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

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test fetch valid scenario`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }
        viewModel.fetchTriviaQuestions("History")
        assert(viewModel.uiState.value is TriviaUIState.Success)
        collectJob.cancel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test fetch invalid scenario`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }
        viewModel.fetchTriviaQuestions("")
        assert(viewModel.uiState.value is TriviaUIState.Error)
        collectJob.cancel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test process intent scenarios`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }
        viewModel.fetchTriviaQuestions("History")
        viewModel.processIntent(TriviaIntent.SubmitAnswer(0))
        assert(
            viewModel.uiState.value is TriviaUIState.Success
                    && (viewModel.uiState.value as
                    TriviaUIState.Success)
                .questions[(viewModel.uiState.value as TriviaUIState.Success)
                .currentQuestionIndex].selectedAnswer == SelectedAnswerState.Answered(0)
        )

        viewModel.processIntent(TriviaIntent.RandomTriviaRound)
        assert(viewModel.uiState.value is TriviaUIState.Success)
        collectJob.cancel()
    }

    @After
    fun tearDown() {
        viewModel.reset()
    }
}