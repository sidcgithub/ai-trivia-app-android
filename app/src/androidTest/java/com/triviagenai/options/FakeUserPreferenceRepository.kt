package com.triviagenai.options

import com.triviagenai.triviagen.options.domain.repository.UserPreferenceRepository

class FakeUserPreferenceRepository(): UserPreferenceRepository {
    private var darkmodeState = false

    override suspend fun getDarkmodeState(): Boolean {
        return darkmodeState
    }

    override suspend fun setDarkmodeState(value: Boolean) {
        darkmodeState = !darkmodeState
    }
}