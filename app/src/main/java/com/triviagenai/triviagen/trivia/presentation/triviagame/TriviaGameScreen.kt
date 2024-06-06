package com.triviagenai.triviagen.trivia.presentation.triviagame

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.triviagenai.triviagen.R
import com.triviagenai.triviagen.core.presentation.TriviaGenScaffold
import com.triviagenai.triviagen.trivia.domain.model.SelectedAnswerState
import com.triviagenai.triviagen.trivia.presentation.TriviaIntent
import com.triviagenai.triviagen.trivia.presentation.TriviaQuestionViewModel
import com.triviagenai.triviagen.trivia.presentation.TriviaUIState
import com.triviagenai.triviagen.ui.theme.LightGreen
import com.triviagenai.triviagen.ui.theme.LightRed
import com.triviagenai.triviagen.ui.theme.TriviaGreen
import com.triviagenai.triviagen.ui.theme.TriviaRed

@Composable
fun TriviaGameScreen(triviaQuestionViewModel: TriviaQuestionViewModel) {
    val triviaRound by triviaQuestionViewModel.uiState.collectAsState()
    var selectedIndex by remember { mutableIntStateOf(-1) }
    TriviaGenScaffold {
        when (triviaRound) {
            is TriviaUIState.Success -> DisplayGameContent(
                triviaRound,
                selectedIndex,
                { index -> selectedIndex = index },
                triviaQuestionViewModel
            )

            is TriviaUIState.Error -> Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = (triviaRound as TriviaUIState.Error).message,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            TriviaUIState.Loading -> Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "Loading...",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
private fun DisplayGameContent(
    triviaRound: TriviaUIState,
    selectedIndex: Int,
    setSelectedIndex: (Int) -> Unit,
    viewModel: TriviaQuestionViewModel
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val questionBlock =
            (triviaRound as TriviaUIState.Success).questions[(triviaRound).currentQuestionIndex]

        Text(
            text = questionBlock.question,
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium))
        )

        questionBlock.options.forEachIndexed { index, trivia ->
            Button(
                onClick = {
                    setSelectedIndex(index)
                    viewModel.processIntent(
                        TriviaIntent.SubmitAnswer(
                            index
                        )
                    )
                },
                shape = AbsoluteRoundedCornerShape(dimensionResource(id = R.dimen.rounded_corner)),
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .width(dimensionResource(id = R.dimen.element_xlarge))
                    .border(
                        dimensionResource(id = R.dimen.border_width),
                        if (selectedIndex == index && questionBlock.selectedAnswer != SelectedAnswerState.Unanswered) {
                            if (selectedIndex == questionBlock.answer) TriviaGreen else TriviaRed
                        } else {
                            Color.Gray
                        },
                        AbsoluteRoundedCornerShape(dimensionResource(id = R.dimen.rounded_corner))
                    )
                    .shadow(
                        elevation = dimensionResource(id = R.dimen.elevation_small),
                        ambientColor =
                        if (selectedIndex == index && questionBlock.selectedAnswer != SelectedAnswerState.Unanswered) {
                            if (selectedIndex == questionBlock.answer) LightGreen else LightRed
                        } else {
                            Color.Transparent
                        },
                        spotColor =
                        if (selectedIndex == index && questionBlock.selectedAnswer != SelectedAnswerState.Unanswered) {
                            if (selectedIndex == questionBlock.answer) LightGreen else LightRed
                        } else {
                            Color.Gray
                        }
                    )
            ) {
                Text(
                    text = trivia, color = Color.White
                )
            }
        }
    }
}
