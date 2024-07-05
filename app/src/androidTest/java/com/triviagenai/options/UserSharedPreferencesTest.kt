package com.triviagenai.options

import android.content.Context
import android.content.SharedPreferences
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserSharedPreferencesTest {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var context: Context

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        sharedPreferences = context.getSharedPreferences(
            "darkmode", Context.MODE_PRIVATE
        )
    }

    @Test
    fun savingAndReadingFromSharedPreferenceTest() {
        with(sharedPreferences.edit()) {
            putBoolean("darkmode", true)
            apply()
        }

        val returnedValue = sharedPreferences.getBoolean("darkmode", false)

        assert(returnedValue)
    }
}