package com.triviagenai.triviagen.trivia.presentation.results.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.dimensionResource
import com.triviagenai.triviagen.R

@Composable
fun CircularScoreProgressBar(score: Float) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            progress = score,
            color = if (score > 0.5f) Color.Green else Color.Red,
            strokeWidth = dimensionResource(id = R.dimen.element_small),
            modifier = Modifier.size(dimensionResource(id = R.dimen.element_large)),
            strokeCap = StrokeCap.Round
        )

        Text(
            text = "Score: ${(score * 100).toInt()}"
        )
    }
}