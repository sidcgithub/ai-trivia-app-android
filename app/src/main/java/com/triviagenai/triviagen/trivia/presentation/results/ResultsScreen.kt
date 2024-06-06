package com.triviagenai.triviagen.trivia.presentation.results

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.triviagenai.triviagen.R
import com.triviagenai.triviagen.core.presentation.TriviaGenScaffold
import com.triviagenai.triviagen.trivia.presentation.TriviaQuestionViewModel
import com.triviagenai.triviagen.trivia.presentation.TriviaQuestionViewModel.Companion.POINTS
import com.triviagenai.triviagen.trivia.presentation.TriviaUIState
import com.triviagenai.triviagen.ui.theme.RoyalPurple
import com.triviagenai.triviagen.ui.theme.TriviaGreen
import com.triviagenai.triviagen.ui.theme.TriviaRed

@Composable
fun ResultsScreen(triviaQuestionViewModel: TriviaQuestionViewModel) {
    val state = triviaQuestionViewModel.uiState.collectAsState()
    val score: Float by remember {
        when (state.value) {
            is TriviaUIState.Success -> {
                mutableFloatStateOf((state.value as TriviaUIState.Success).score.toFloat())
            }

            else -> {
                mutableFloatStateOf(0f)
            }
        }
    }
    val questionsSize: Int by remember {
        when (state.value) {
            is TriviaUIState.Success -> {
                mutableIntStateOf((state.value as TriviaUIState.Success).questions.size)
            }

            else -> {
                mutableIntStateOf(0)
            }
        }
    }

    TriviaGenScaffold {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    progress = score / (POINTS * questionsSize),
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

            OutlinedButton(
                onClick = { /*TODO*/ },
                shape = AbsoluteRoundedCornerShape(dimensionResource(id = R.dimen.rounded_corner)),
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .width(dimensionResource(id = R.dimen.element_xlarge))
                    .height(dimensionResource(id = R.dimen.element_height))
                    .border(
                        1.dp,
                        color = Color.White,
                        shape = AbsoluteRoundedCornerShape(dimensionResource(id = R.dimen.rounded_corner))
                    )
            ) {
                Text(
                    text = stringResource(R.string.view_answers),
                    fontWeight = FontWeight.Bold
                )
            }

            data class ButtonData(
                val text: String,
                val onClick: () -> Unit
            )

            val buttonData = listOf(
                ButtonData(
                    text = stringResource(R.string.retry),
                    onClick = { /*TODO*/ }
                ),
                ButtonData(
                    text = stringResource(R.string.home),
                    onClick = { /*TODO*/ }
                )
            )

            for (button in buttonData) {
                ElevatedButton(
                    onClick = button.onClick,
                    shape = AbsoluteRoundedCornerShape(dimensionResource(id = R.dimen.rounded_corner)),
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_small))
                        .width(dimensionResource(id = R.dimen.element_xlarge))
                        .height(dimensionResource(id = R.dimen.element_height))
                ) {
                    Text(
                        text = button.text,
                        color = RoyalPurple,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}