package com.triviagenai.triviagen.options.data.source.local

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration

class UserSharedPreferences(
    context: Context
) {
    private val preference: SharedPreferences = context.getSharedPreferences("darkmode", Context.MODE_PRIVATE)
    private val systemTheme = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES

    fun saveDarkmodePreference(value: Boolean) {
        with(preference.edit()) {
            putBoolean("darkmode", value)
            apply()
        }
    }

    fun readDarkmodePreference(): Boolean {
        return preference.getBoolean("darkmode", systemTheme)
    }
}