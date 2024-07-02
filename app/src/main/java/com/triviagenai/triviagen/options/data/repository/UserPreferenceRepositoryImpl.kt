package com.triviagenai.triviagen.options.data.repository

import com.triviagenai.triviagen.options.data.source.local.UserSharedPreferences
import com.triviagenai.triviagen.options.domain.repository.UserPreferenceRepository

class UserPreferenceRepositoryImpl(private val userSharedPreferences: UserSharedPreferences) : UserPreferenceRepository {
    override var darkMode: Boolean
        get() = userSharedPreferences.readDarkmodePreference()
        set(value) {
            userSharedPreferences.saveDarkmodePreference(value)
        }
}