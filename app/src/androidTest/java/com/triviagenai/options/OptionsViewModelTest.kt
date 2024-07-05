package com.triviagenai.options

import com.triviagenai.triviagen.options.domain.usecase.GetDarkmodePreferenceUseCase
import com.triviagenai.triviagen.options.domain.usecase.SetDarkmodePreferenceUseCase
import com.triviagenai.triviagen.options.presentation.options.OptionsViewModel
import org.junit.Test

class OptionsViewModelTest {
    private val repository = FakeUserPreferenceRepository()
    private val viewModel = OptionsViewModel(
        GetDarkmodePreferenceUseCase(repository),
        SetDarkmodePreferenceUseCase(repository)
    )

    @Test
    fun stateIsUpdatedCorrectly() {
        val currentState = viewModel.darkmodeState.value

        viewModel.changeDarkmode()

        val newState = viewModel.darkmodeState.value

        assert(currentState != newState)
    }


    @Test
    fun testInitialValue() {
        val state = viewModel.darkmodeState.value
        assert(!state)
    }
}