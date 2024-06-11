package com.triviagenai.triviagen.trivia.presentation.mainmenu

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.triviagenai.triviagen.R
import com.triviagenai.triviagen.core.presentation.ButtonData
import com.triviagenai.triviagen.core.presentation.TriviaGenScaffold
import com.triviagenai.triviagen.core.presentation.navigation.NavigationStatus
import com.triviagenai.triviagen.core.presentation.navigation.Route

@Composable
fun MainMenuScreen(
    navController: NavHostController
) {
    val activity = LocalContext.current as Activity

    TriviaGenScaffold(
        navigationStatus = NavigationStatus.None
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .testTag("MainMenuScreen"),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.yellow_app_logo),
                contentDescription = "App logo",
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.element_large))
                    .padding(
                        top = dimensionResource(id = R.dimen.padding_large),
                    )
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
                        activity.finishAffinity()
                    }
                )
            )

            for(buttonData in textButtonsData) {
                TextButton(
                    onClick = buttonData.onClick,
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_small))
                ) {
                    Text(
                        buttonData.text
                    )
                }
            }
        }
    }
}