package com.triviagenai.triviagen.core.presentation

data class ButtonData(
    val text: String,
    val onClick: () -> Unit
)
