package com.triviagenai.triviagen

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.triviagenai.triviagen.trivia.presentation.mainmenu.MainMenuScreen
import com.triviagenai.triviagen.ui.theme.TriviaGenTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TriviaGenTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) {
                    MainMenuScreen()
                }
            }
        }
    }
}