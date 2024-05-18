package com.triviagenai.triviagen.trivia.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triviagenai.triviagen.trivia.domain.usecase.GetTriviaQuestionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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

    init {
        fetchTriviaQuestions("Science")
    }

    private fun fetchTriviaQuestions(topic: String) {
        viewModelScope.launch {
            _uiState.value = TriviaUIState.Loading
            getTriviaQuestionsUseCase(topic).collect { result ->
                result?.onSuccess { questions ->
                    _uiState.value = TriviaUIState.Success(questions, 0, 0)
                }?.onFailure { exception ->
                    _uiState.value = TriviaUIState.Error(exception.message ?: "Unknown error")
                } ?: run { _uiState.value = TriviaUIState.Loading }
            }
        }
    }

    fun processIntent(intent: TriviaIntent) {
        when (intent) {
            is TriviaIntent.SubmitAnswer -> submitAnswer(intent.selectedOptionIndex)
            TriviaIntent.NextQuestion -> nextQuestion()
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

    private fun submitAnswer(selectedOptionIndex: Int) {
        val currentState = _uiState.value
        if (currentState is TriviaUIState.Success) {
            val currentQuestion = currentState.questions[currentState.currentQuestionIndex]
            if (currentQuestion.options[selectedOptionIndex] == currentQuestion.answer) {
                _uiState.value =
                    currentState.copy(score = currentState.score + POINTS)
            }
            nextQuestion()
        }
    }
}

sealed class TriviaIntent {
    data class SubmitAnswer(val selectedOptionIndex: Int) : TriviaIntent()
    object NextQuestion : TriviaIntent()
}
