package com.triviagenai.triviagen.trivia.presentation.answers.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.triviagenai.triviagen.R
import com.triviagenai.triviagen.trivia.domain.model.SelectedAnswerState
import com.triviagenai.triviagen.trivia.domain.model.TriviaQuestion
import com.triviagenai.triviagen.ui.theme.TriviaGreen
import com.triviagenai.triviagen.ui.theme.TriviaRed

@Composable
fun TriviaAnswerCard(triviaQuestion: TriviaQuestion) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = dimensionResource(id = R.dimen.padding_small),
                horizontal = dimensionResource(id = R.dimen.padding_medium)
            ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = triviaQuestion.question,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_medium))
            )
            if (triviaQuestion.selectedAnswer != SelectedAnswerState.Unanswered) {
                val selectedOption =
                    (triviaQuestion.selectedAnswer as SelectedAnswerState.Answered).answer
                triviaQuestion.options.forEachIndexed { i, option ->
                    Text(
                        text = option,
                        modifier = Modifier
                            .padding(horizontal = dimensionResource(id = R.dimen.padding_medium))
                            .border(
                                width = dimensionResource(id = R.dimen.border_width),
                                color = if (i == triviaQuestion.answer) TriviaGreen
                                else {
                                    if (selectedOption == i) TriviaRed
                                    else Color.Transparent
                                },
                                shape = AbsoluteRoundedCornerShape(dimensionResource(id = R.dimen.rounded_corner))
                            )
                            .height(dimensionResource(id = R.dimen.element_small))
                            .fillMaxWidth()
                            .background(
                                color = MaterialTheme.colorScheme.background,
                                shape = AbsoluteRoundedCornerShape(dimensionResource(id = R.dimen.rounded_corner))
                            )
                            .padding(top = 5.dp),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
                }
            }
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
        }
    }
}