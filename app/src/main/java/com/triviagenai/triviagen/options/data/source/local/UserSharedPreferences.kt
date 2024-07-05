package com.triviagenai.triviagen.options.data.source.local

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserSharedPreferences(
    context: Context
) {
    private val preference: SharedPreferences = context.getSharedPreferences("darkmode", Context.MODE_PRIVATE)
    private val systemTheme = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES

    suspend fun saveDarkmodePreference(value: Boolean) {
        withContext(Dispatchers.IO) {
            with(preference.edit()) {
                putBoolean("darkmode", value)
                apply()
            }
        }
    }

    suspend fun readDarkmodePreference(): Boolean {
        return withContext(Dispatchers.IO) {
            preference.getBoolean("darkmode", systemTheme)
        }
    }
}