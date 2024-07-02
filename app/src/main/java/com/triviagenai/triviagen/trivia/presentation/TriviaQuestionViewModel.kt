package com.triviagenai.triviagen.trivia.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triviagenai.triviagen.trivia.domain.model.SelectedAnswerState
import com.triviagenai.triviagen.trivia.domain.usecase.GetTriviaQuestionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TriviaQuestionViewModel @Inject constructor(
    private val getTriviaQuestionsUseCase: GetTriviaQuestionsUseCase
) : ViewModel() {

    private val categories = listOf(
        "History",
        "Geography",
        "Science",
        "Mathematics",
        "Philosophy",
        "Art",
        "Sports",
        "Literature",
        "Technology",
        "Economics",
        "Politics",
        "Culture",
        "Miscellaneous"
    )

    companion object {
        const val POINTS = 5
    }

    private val _uiState = MutableStateFlow<TriviaUIState>(TriviaUIState.Loading)
    val uiState: StateFlow<TriviaUIState> = _uiState
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = TriviaUIState.Loading
        )

    var isOptionButtonEnabled by mutableStateOf(true)
        private set

    fun fetchTriviaQuestions(topic: String) {
        viewModelScope.launch {
            if (topic.isBlank()) {
                _uiState.emit(TriviaUIState.Error("Topic is empty"))
                return@launch
            }
            _uiState.value = TriviaUIState.Loading
            getTriviaQuestionsUseCase(topic).collect { result ->
                result?.onSuccess { questions ->
                    _uiState.value = TriviaUIState.Success(questions.toMutableList(), 0, 0)
                }?.onFailure { exception ->
                    _uiState.value = TriviaUIState.Error(exception.message ?: "Unknown error")
                } ?: run { _uiState.value = TriviaUIState.Loading }
            }
        }
    }

    private fun fetchRandomTriviaRoundQuestions() {
        fetchTriviaQuestions(categories.random())
    }

    fun processIntent(intent: TriviaIntent) {
        when (intent) {
            is TriviaIntent.SubmitAnswer -> viewModelScope.launch { submitAnswer(intent.selectedOptionIndex) }
            TriviaIntent.RandomTriviaRound -> fetchRandomTriviaRoundQuestions()
        }
    }

    private fun nextQuestion() {
        val currentState = uiState.value
        if (currentState is TriviaUIState.Success) {
            val nextIndex = currentState.currentQuestionIndex + 1
            if (nextIndex < currentState.questions.size) {
                _uiState.value = currentState.copy(currentQuestionIndex = nextIndex)
            }
        }
    }

    private suspend fun submitAnswer(selectedOptionIndex: Int) {
        isOptionButtonEnabled = false
        val currentState = _uiState.value
        if (currentState is TriviaUIState.Success) {
            val currentQuestion = currentState.questions[currentState.currentQuestionIndex]
            currentState.questions[currentState.currentQuestionIndex] =
                currentQuestion.copy(
                    selectedAnswer = SelectedAnswerState.Answered(
                        selectedOptionIndex
                    )
                )
            if (selectedOptionIndex == currentQuestion.answer) {
                _uiState.value =
                    currentState.copy(score = currentState.score + POINTS)
            }
            delay(2000)
            nextQuestion()
            isOptionButtonEnabled = true
        }
    }

    fun reset() {
        _uiState.value = TriviaUIState.Loading
    }
}

sealed class TriviaIntent {
    data class SubmitAnswer(val selectedOptionIndex: Int) : TriviaIntent()

    object RandomTriviaRound : TriviaIntent()
}
