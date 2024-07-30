package com.triviagenai.triviagen.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color.White,
    secondary = PurpleGrey80,
    secondaryContainer = DarkPurple,
    tertiary = Pink80,
    background = RoyalPurple,
    onBackground = Color.White,
    surface = RoyalPurple,
    outline = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = DarkPurple,
    secondary = PurpleGrey40,
    onSecondaryContainer = DarkPurple,
    tertiary = Pink40,
    background = Color.White,
    onBackground = DarkPurple,
    surface = Color.White,
    onSurface = DarkPurple,
    outline = RoyalPurple
)

@Composable
fun TriviaGenTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}