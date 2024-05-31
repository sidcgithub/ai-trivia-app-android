package com.triviagenai.triviagen.trivia.presentation.triviagame

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.triviagenai.triviagen.R
import com.triviagenai.triviagen.ResultsRoute
import com.triviagenai.triviagen.core.presentation.TriviaGenScaffold
import com.triviagenai.triviagen.trivia.domain.model.TriviaQuestion
import com.triviagenai.triviagen.ui.theme.RoyalPurple
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun TriviaGameScreen(navController: NavHostController) {
    val fakeData = listOf(
        TriviaQuestion("What is the largest animal?", listOf("Option", "Correct answer", "Option", "Wrong answer"), 1),
        TriviaQuestion("When was the prehistory?", listOf("1", "2", "Never", "Tomorrow"), 0),
    )

    var answer by remember { mutableIntStateOf(-1) }
    var questionIndex by remember { mutableIntStateOf(0) }

    TriviaGenScaffold {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = fakeData[questionIndex].question,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_medium))
            )

            fakeData[questionIndex].options.forEachIndexed { index, trivia ->
                Button(
                    onClick = {
                        answer = index
                        CoroutineScope(Dispatchers.Main).launch {
                            if(questionIndex == fakeData.lastIndex) {
                                delay(3000)
                                navController.navigate(ResultsRoute)
                            }
                            delay(3000)
                            questionIndex++
                            answer = -1
                        }
                    },
                    shape = AbsoluteRoundedCornerShape(dimensionResource(id = R.dimen.rounded_corner)),
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_small))
                        .width(dimensionResource(id = R.dimen.element_xlarge))
                        .border(
                            if (answer > -1) 4.dp else (-1).dp,
                            Brush.linearGradient(
                                colors =
                                    if (index == fakeData[0].answer) listOf(RoyalPurple, Color.Green)
                                    else if (index == answer) listOf(RoyalPurple, Color.Red)
                                    else listOf(RoyalPurple, RoyalPurple),
                                start = Offset(0.0f, 0.0f),
                                end = Offset(100.0f, 300.0f)
                            ),
                            AbsoluteRoundedCornerShape(dimensionResource(id = R.dimen.rounded_corner))
                        )
                ) {
                    Text(
                        text = trivia,
                        color = Color.White
                    )
                }
            }
        }
    }
}