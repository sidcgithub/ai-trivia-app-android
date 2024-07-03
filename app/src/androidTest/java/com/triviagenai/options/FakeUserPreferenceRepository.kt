package com.triviagenai.options

import com.triviagenai.triviagen.options.domain.repository.UserPreferenceRepository

class FakeUserPreferenceRepository(): UserPreferenceRepository {
    override var darkMode: Boolean
        get() = true
        set(value) {}

}