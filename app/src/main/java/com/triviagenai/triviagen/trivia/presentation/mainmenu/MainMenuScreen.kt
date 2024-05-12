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
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.element_large))
            )
            
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_medium)))
            
            TextButton(
                onClick = { /*navigates to the trivia screen*/ }
            ) {
                Text(stringResource(R.string.quick_game))
            }

            TextButton(
                onClick = { /*navigates to the options screen*/ }
            ) {
                Text(stringResource(R.string.options))
            }

            val activity = LocalContext.current as MainActivity

            TextButton(
                onClick = { activity.finish() }
            ) {
                Text(stringResource(R.string.exit))
            }
        }
     }
}