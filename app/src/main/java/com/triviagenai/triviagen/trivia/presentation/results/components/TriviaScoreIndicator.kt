package com.triviagenai.triviagen.trivia.presentation.results.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.dimensionResource
import com.triviagenai.triviagen.R
import com.triviagenai.triviagen.trivia.presentation.TriviaQuestionViewModel
import com.triviagenai.triviagen.ui.theme.TriviaGreen
import com.triviagenai.triviagen.ui.theme.TriviaRed

@Composable
fun TriviaScoreIndicator(
    score: Float,
    questionsSize: Int,
) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            progress = score / (TriviaQuestionViewModel.POINTS * questionsSize),
            color = if (score > 0.5f) TriviaGreen else TriviaRed,
            strokeWidth = dimensionResource(id = R.dimen.element_small),
            modifier = Modifier.size(dimensionResource(id = R.dimen.element_large)),
            strokeCap = StrokeCap.Round
        )

        Text(
            text = "Score: ${(score).toInt()}"
        )
    }
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_medium)))
}