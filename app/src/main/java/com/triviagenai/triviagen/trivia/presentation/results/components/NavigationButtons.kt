package com.triviagenai.triviagen.trivia.presentation.results.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.triviagenai.triviagen.R
import com.triviagenai.triviagen.core.presentation.ButtonData
import com.triviagenai.triviagen.core.presentation.navigation.Route
import com.triviagenai.triviagen.trivia.presentation.TriviaQuestionViewModel
import com.triviagenai.triviagen.ui.theme.RoyalPurple

@Composable
fun NavigationButtons(navController: NavHostController, viewModel: TriviaQuestionViewModel) {
    val buttonData = listOf(
        ButtonData(
            text = stringResource(R.string.retry),
            onClick = {
                viewModel.reset()
                navController.navigate(Route.RoundSetupRoute) {
                    popUpTo(Route.ResultsRoute) { inclusive = true }
                }
            }
        ),
        ButtonData(
            text = stringResource(R.string.home),
            onClick = {
                viewModel.reset()
                navController.navigate(Route.MainMenuRoute) {
                    popUpTo(Route.ResultsRoute) { inclusive = true }
                }
            }
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