package com.triviagenai.triviagen.trivia.presentation.mainmenu

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.navigation.NavHostController
import com.triviagenai.triviagen.R
import com.triviagenai.triviagen.core.presentation.ButtonData
import com.triviagenai.triviagen.core.presentation.TriviaGenScaffold
import com.triviagenai.triviagen.core.presentation.navigation.Route
import com.triviagenai.triviagen.trivia.presentation.TriviaQuestionViewModel

@Composable
fun MainMenuScreen(
    navController: NavHostController,
    triviaQuestionViewModel: TriviaQuestionViewModel
) {
    val activity = LocalContext.current as Activity

    TriviaGenScaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .semantics { contentDescription = "MainMenuScreen" },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.yellow_app_logo),
                contentDescription = "App logo",
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.element_large))
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_medium)))

            val textButtonsData = listOf(
                ButtonData(
                    text = stringResource(R.string.quick_game),
                    onClick = {
                        navController.navigate(
                            Route.RoundSetupRoute
                        )
                    }
                ),
                ButtonData(
                    text = stringResource(R.string.options),
                    onClick = {
                        //TODO: navigate to options screen
                    }
                ),
                ButtonData(
                    text = stringResource(R.string.exit),
                    onClick = {
                        triviaQuestionViewModel.exitApp(activity)
                    }
                )
            )

            for(buttonData in textButtonsData) {
                TextButton(
                    onClick = buttonData.onClick
                ) {
                    Text(buttonData.text)
                }
            }
        }
    }
}