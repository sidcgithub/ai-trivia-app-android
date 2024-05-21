package com.triviagenai.triviagen.trivia.presentation.mainmenu

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
import com.triviagenai.triviagen.MainActivity
import com.triviagenai.triviagen.R
import com.triviagenai.triviagen.core.presentation.ComposableData
import com.triviagenai.triviagen.core.presentation.TriviaGenScaffold

@Composable
fun MainMenuScreen() {
    TriviaGenScaffold {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.yellow_app_logo),
                contentDescription = "App logo",
                modifier = Modifier.size(dimensionResource(id = R.dimen.element_large))
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_medium)))

            val activity = LocalContext.current as MainActivity
            val textButtonData = listOf(
                ComposableData(
                    onClick = { /*navigates to the trivia screen*/ },
                    text = stringResource(R.string.quick_game)
                ),
                ComposableData(
                    onClick = { /*navigates to the options screen*/ },
                    text = stringResource(R.string.options)
                ),
                ComposableData(
                    onClick = { activity.finish() },
                    text = stringResource(R.string.exit)
                )
            )

            for (i in textButtonData.indices) {
                TextButton(
                    onClick = textButtonData[i].onClick
                ) {
                    Text(textButtonData[i].text)
                }
            }
        }
    }
}