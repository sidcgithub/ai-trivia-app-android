package com.triviagenai.triviagen.options.presentation.options

import androidx.lifecycle.ViewModel
import com.triviagenai.triviagen.options.domain.usecase.GetDarkmodePreferenceUseCase
import com.triviagenai.triviagen.options.domain.usecase.SetDarkmodePreferenceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class OptionsViewModel @Inject constructor(
    getDarkmodePreferenceUseCase: GetDarkmodePreferenceUseCase,
    private val setDarkmodePreferenceUseCase: SetDarkmodePreferenceUseCase
) : ViewModel() {
    private val _darkmodeState = MutableStateFlow(false)
    val darkmodeTheme = _darkmodeState.asStateFlow()

    init {
        _darkmodeState.value = getDarkmodePreferenceUseCase()
    }

    fun changeDarkmode() {
        _darkmodeState.value = !_darkmodeState.value
        setDarkmodePreferenceUseCase(_darkmodeState.value)
    }
}