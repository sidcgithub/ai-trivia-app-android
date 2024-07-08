package com.triviagenai.triviagen.options

import com.triviagenai.triviagen.options.domain.usecase.GetDarkmodePreferenceUseCase
import com.triviagenai.triviagen.options.domain.usecase.SetDarkmodePreferenceUseCase
import com.triviagenai.triviagen.options.presentation.options.OptionsViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class OptionsViewModelTest {
    private val testDispatcher = StandardTestDispatcher()

    @Mock
    private lateinit var getDarkmodePreferenceUseCase: GetDarkmodePreferenceUseCase

    @Mock
    private lateinit var setDarkmodePreferenceUseCase: SetDarkmodePreferenceUseCase

    private lateinit var optionsViewModel: OptionsViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `init should set darkmodeState from use case`() = runTest {
        `when`(getDarkmodePreferenceUseCase.invoke()).thenReturn(true)

        optionsViewModel = OptionsViewModel(getDarkmodePreferenceUseCase, setDarkmodePreferenceUseCase)

        advanceUntilIdle()

        assertEquals(true, optionsViewModel.darkmodeState.first())
        verify(getDarkmodePreferenceUseCase, times(1)).invoke()
    }

    @Test
    fun `changeDarkmode should toggle darkmodeState and call setDarkmodePreferenceUseCase`() = runTest {
        `when`(getDarkmodePreferenceUseCase()).thenReturn(false)

        optionsViewModel = OptionsViewModel(getDarkmodePreferenceUseCase, setDarkmodePreferenceUseCase)

        optionsViewModel.changeDarkmode()

        advanceUntilIdle()

        assertEquals(true, optionsViewModel.darkmodeState.first())
        verify(setDarkmodePreferenceUseCase, times(1)).invoke(true)
    }
}
