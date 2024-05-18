package com.triviagenai.triviagen.trivia.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triviagenai.triviagen.trivia.domain.usecase.GetTriviaQuestionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TriviaQuestionViewModel @Inject constructor(
    private val getTriviaQuestionsUseCase: GetTriviaQuestionsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(TriviaUIState())
    val uiState: StateFlow<TriviaUIState> = _uiState.asStateFlow()

    init {
        fetchTriviaQuestions("Science")
    }

    private fun fetchTriviaQuestions(topic: String) {
        viewModelScope.launch {
            getTriviaQuestionsUseCase(topic).collect { result ->
                result?.onSuccess { questions ->
                    _uiState.value =
                        _uiState.value.copy(questions = questions, isLoading = false, error = null)
                }?.onFailure { exception ->
                    _uiState.value =
                        _uiState.value.copy(isLoading = false, error = exception.message)
                } ?: {
                    _uiState.value = _uiState.value.copy(
                        isLoading = true,
                        error = null
                    )
                }
            }
        }
    }

    fun nextQuestion() {
        val nextIndex = _uiState.value.currentQuestionIndex + 1
        if (nextIndex < _uiState.value.questions.size) {
            _uiState.value = _uiState.value.copy(currentQuestionIndex = nextIndex)
        }
    }

    fun submitAnswer(selectedOptionIndex: Int) {
        val currentQuestion = _uiState.value.questions[_uiState.value.currentQuestionIndex]
        if (currentQuestion.options[selectedOptionIndex] == currentQuestion.answer) {
            _uiState.value = _uiState.value.copy(score = _uiState.value.score + 5)
        }
        nextQuestion()
    }
}
