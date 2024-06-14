package com.triviagenai.triviagen.trivia.presentation.results

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.triviagenai.triviagen.R
import com.triviagenai.triviagen.core.presentation.TriviaGenScaffold
import com.triviagenai.triviagen.core.presentation.navigation.NavigationStatus
import com.triviagenai.triviagen.core.presentation.navigation.Route
import com.triviagenai.triviagen.trivia.presentation.TriviaQuestionViewModel
import com.triviagenai.triviagen.trivia.presentation.TriviaUIState
import com.triviagenai.triviagen.trivia.presentation.results.components.NavigationButtons
import com.triviagenai.triviagen.trivia.presentation.results.components.TriviaScoreIndicator

@Composable
fun ResultsScreen(
    triviaQuestionViewModel: TriviaQuestionViewModel,
    navController: NavHostController
) {
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

    TriviaGenScaffold(
        navigationStatus = NavigationStatus.None
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .testTag("ResultsScreen"),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TriviaScoreIndicator(
                score = score,
                questionsSize = questionsSize
            )

            OutlinedButton(
                onClick = { navController.navigate(Route.AnswersRoute) },
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

            NavigationButtons(navController, triviaQuestionViewModel)
        }
    }
}