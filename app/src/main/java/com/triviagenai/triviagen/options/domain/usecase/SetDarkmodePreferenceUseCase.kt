package com.triviagenai.triviagen.options.domain.usecase

import com.triviagenai.triviagen.options.domain.repository.UserPreferenceRepository
import javax.inject.Inject

class SetDarkmodePreferenceUseCase @Inject constructor(
    private val repository: UserPreferenceRepository
) {
    operator fun invoke(state: Boolean) {
        repository.darkMode = state
    }
}