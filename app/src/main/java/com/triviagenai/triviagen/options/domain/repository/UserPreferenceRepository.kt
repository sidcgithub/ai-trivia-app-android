package com.triviagenai.triviagen.options.domain.repository

interface UserPreferenceRepository {
    suspend fun getDarkmodeState(): Boolean
    suspend fun setDarkmodeState(value: Boolean)
}