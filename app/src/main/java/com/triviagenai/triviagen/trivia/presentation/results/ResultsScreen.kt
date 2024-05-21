package com.triviagenai.triviagen.trivia.presentation.results

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.triviagenai.triviagen.R
import com.triviagenai.triviagen.core.presentation.ComposableData
import com.triviagenai.triviagen.core.presentation.TriviaGenScaffold
import com.triviagenai.triviagen.trivia.presentation.results.components.CircularScoreProgressBar
import com.triviagenai.triviagen.ui.theme.RoyalPurple

@Composable
fun ResultsScreen() {
    val score = 0.51f

    TriviaGenScaffold {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularScoreProgressBar(score)

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_medium)))

            OutlinedButton(
                onClick = { /*TODO*/ },
                shape = AbsoluteRoundedCornerShape(dimensionResource(id = R.dimen.rounded_corner)),
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .width(dimensionResource(id = R.dimen.element_xlarge))
                    .height(dimensionResource(id = R.dimen.element_height))
                    .border(1.dp, color = Color.White, shape = AbsoluteRoundedCornerShape(dimensionResource(id = R.dimen.rounded_corner)))
            ) {
                Text(
                    text = stringResource(R.string.view_answers),
                    fontWeight = FontWeight.Bold
                )
            }

            val buttonData = listOf(
                ComposableData(
                    text = stringResource(R.string.retry),
                    onClick = { /*TODO*/ }
                ),
                ComposableData(
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