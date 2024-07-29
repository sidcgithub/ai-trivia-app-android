package com.triviagenai.auth.presentation.login

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.triviagenai.triviagen.R
import com.triviagenai.triviagen.core.presentation.TriviaGenScaffold
import com.triviagenai.triviagen.core.presentation.navigation.NavigationStatus
import com.triviagenai.triviagen.trivia.presentation.triviagame.components.QuitTriviaAlertDialog

@Composable
fun LoginScreen(
    navController: NavHostController
) {
    val isShowingExitDialog = remember { mutableStateOf(false) }
    var userName by remember { mutableStateOf("") }
    val scrollState = remember { ScrollState(0) }
    TriviaGenScaffold(
        navigationStatus = NavigationStatus.None
    ) {
        QuitTriviaAlertDialog(
            isShowingExitDialog,
            navController
        )

        BackHandler {
            isShowingExitDialog.value = true
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .testTag("MainMenuScreen")
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "App logo",
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.element_large))
                    .padding(
                        top = dimensionResource(id = R.dimen.padding_large),
                    )
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_medium)))

            OutlinedTextField(
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen.element_xlarge))
                    .height(dimensionResource(id = R.dimen.element_height) + 10.dp) // without plus 10.dp the text field has a smaller height than the buttons
                    .padding(bottom = dimensionResource(id = R.dimen.padding_small)),
                value = userName,
                onValueChange = { userName = it },
                label = {
                    Text(
                        text = stringResource(R.string.username_label),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                singleLine = true
            )
            OutlinedTextField(
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen.element_xlarge))
                    .height(dimensionResource(id = R.dimen.element_height) + 10.dp) // without plus 10.dp the text field has a smaller height than the buttons
                    .padding(bottom = dimensionResource(id = R.dimen.padding_small)),
                value = userName,
                onValueChange = { userName = it },
                label = {
                    Text(
                        text = stringResource(R.string.password_label),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true
            )
            Button(
                onClick = {
                },
                shape = AbsoluteRoundedCornerShape(dimensionResource(id = R.dimen.rounded_corner)),
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .width(dimensionResource(id = R.dimen.element_xlarge))
                    .height(dimensionResource(id = R.dimen.element_height))
                    .testTag("RandomRoundButton")
            ) {
                Text(
                    text = stringResource(R.string.login_button_label),
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(id = R.dimen.padding_large)),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Forgot Password?",
                )

            }
            Button(
                onClick = {
                },
                shape = AbsoluteRoundedCornerShape(dimensionResource(id = R.dimen.rounded_corner)),
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .width(dimensionResource(id = R.dimen.element_xlarge))
                    .height(dimensionResource(id = R.dimen.element_height))
                    .testTag("RandomRoundButton")
            ) {
                Text(
                    text = stringResource(R.string.register_button_label),
                    fontWeight = FontWeight.Bold
                )
            }


        }
    }
}