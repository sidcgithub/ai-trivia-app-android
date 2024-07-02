package com.triviagenai.triviagen.options.presentation.options

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class OptionsViewModel @Inject constructor() : ViewModel() {
    private val _themeState = MutableStateFlow(false)
    val themeState = _themeState.asStateFlow()

    fun changeTheme() {
        _themeState.value = !_themeState.value
    }
}