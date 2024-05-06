package com.triviagenai.triviagen.trivia.presentation

import androidx.lifecycle.ViewModel
import com.triviagenai.triviagen.trivia.domain.usecase.GetTriviaQuestionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TriviaQuestionViewModel @Inject constructor(getTriviaQuestionsUseCase: GetTriviaQuestionsUseCase) :
    ViewModel() {

}