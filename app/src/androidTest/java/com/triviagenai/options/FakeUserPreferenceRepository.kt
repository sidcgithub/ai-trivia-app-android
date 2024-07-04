package com.triviagenai.options

import com.triviagenai.triviagen.options.domain.repository.UserPreferenceRepository

class FakeUserPreferenceRepository(): UserPreferenceRepository {
    private var darkmodeState = false

    override var darkmode: Boolean
        get() = darkmodeState
        set(value) { darkmodeState = !darkmodeState}
}