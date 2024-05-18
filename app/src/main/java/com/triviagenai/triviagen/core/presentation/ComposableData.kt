package com.triviagenai.triviagen.core.presentation

data class ComposableData(
    val text: String,
    val onClick: () -> Unit
)

