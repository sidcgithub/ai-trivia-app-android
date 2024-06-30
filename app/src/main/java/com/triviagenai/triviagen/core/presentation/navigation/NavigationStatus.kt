package com.triviagenai.triviagen.core.presentation.navigation

import androidx.navigation.NavController

sealed class NavigationStatus {
    object None : NavigationStatus()
    data class Enabled(val navController: NavController, val backNav: () -> Unit) :
        NavigationStatus()
}