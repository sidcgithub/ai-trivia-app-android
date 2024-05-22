package com.triviagenai.triviagen.trivia.presentation.triviagame

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.triviagenai.triviagen.R
import com.triviagenai.triviagen.core.presentation.TriviaGenScaffold
import com.triviagenai.triviagen.trivia.domain.model.TriviaQuestion

@Composable
fun TriviaGameScreen() {
    val fakeData = listOf(
        TriviaQuestion("What?", listOf("1", "2"), 0),
        TriviaQuestion("What?", listOf("1", "2"), 0),
    )

    TriviaGenScaffold {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = fakeData[0].question,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_medium))
            )

            fakeData[0].options.forEachIndexed { index, trivia ->
                Button(
                    onClick = {

                    },
                    shape = AbsoluteRoundedCornerShape(dimensionResource(id = R.dimen.rounded_corner)),
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_small))
                        .width(dimensionResource(id = R.dimen.element_xlarge))
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