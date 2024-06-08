package com.triviagenai.triviagen.trivia.presentation.triviagame.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.triviagenai.triviagen.R
import com.triviagenai.triviagen.trivia.domain.model.SelectedAnswerState
import com.triviagenai.triviagen.trivia.presentation.TriviaIntent
import com.triviagenai.triviagen.trivia.presentation.TriviaQuestionViewModel
import com.triviagenai.triviagen.trivia.presentation.TriviaUIState
import com.triviagenai.triviagen.ui.theme.LightGreen
import com.triviagenai.triviagen.ui.theme.LightRed
import com.triviagenai.triviagen.ui.theme.TriviaGreen
import com.triviagenai.triviagen.ui.theme.TriviaRed

@Composable
fun DisplayGameContent(
    triviaRound: TriviaUIState,
    selectedIndex: Int,
    setSelectedIndex: (Int) -> Unit,
    viewModel: TriviaQuestionViewModel,
    navController: NavHostController
) {
    val questionBlock =
        (triviaRound as TriviaUIState.Success).questions[(triviaRound).currentQuestionIndex]

    val borderColors: (correctAnswerColor: Color, wrongAnswerColor: Color, optionColor: Color, index: Int) -> Color = { correctAnswerColor, wrongAnswerColor, optionColor, index ->
        if(index == questionBlock.answer && questionBlock.selectedAnswer != SelectedAnswerState.Unanswered)
            correctAnswerColor
        else if(selectedIndex == index && questionBlock.selectedAnswer != SelectedAnswerState.Unanswered)
            wrongAnswerColor
        else
            optionColor
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
                            index,
                            navController
                        )
                    )
                },
                shape = AbsoluteRoundedCornerShape(dimensionResource(id = R.dimen.rounded_corner)),
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .width(dimensionResource(id = R.dimen.element_xlarge))
                    .border(
                        width = if(questionBlock.selectedAnswer != SelectedAnswerState.Unanswered && (index == questionBlock.answer || selectedIndex == index)) 2.dp else 1.dp,
                        color = borderColors(TriviaGreen, TriviaRed, Color.Gray, index),
                        shape = AbsoluteRoundedCornerShape(dimensionResource(id = R.dimen.rounded_corner))
                    )
                    .shadow(
                        elevation = dimensionResource(id = R.dimen.elevation_small),
                        ambientColor =
                            borderColors(LightGreen, LightRed, Color.Transparent, index),
                        spotColor =
                            borderColors(TriviaGreen, TriviaRed, Color.Gray, index)
                    ),
                enabled = viewModel.isOptionButtonEnabled,
                colors = ButtonDefaults.buttonColors(
                    disabledContentColor = MaterialTheme.colorScheme.onBackground,
                    disabledContainerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = trivia, color = Color.White
                )
            }
        }
    }
}