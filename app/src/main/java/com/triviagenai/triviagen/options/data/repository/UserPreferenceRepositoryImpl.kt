package com.triviagenai.triviagen.options.data.repository

import com.triviagenai.triviagen.options.data.source.local.UserSharedPreferences
import com.triviagenai.triviagen.options.domain.repository.UserPreferenceRepository

class UserPreferenceRepositoryImpl(private val userSharedPreferences: UserSharedPreferences) : UserPreferenceRepository {
    override suspend fun getDarkmodeState(): Boolean {
        return userSharedPreferences.readDarkmodePreference()
    }

    override suspend fun setDarkmodeState(value: Boolean) {
        userSharedPreferences.saveDarkmodePreference(value)
    }
}