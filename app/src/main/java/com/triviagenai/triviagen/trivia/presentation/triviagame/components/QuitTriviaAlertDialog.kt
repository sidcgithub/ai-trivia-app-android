package com.triviagenai.triviagen.trivia.presentation.triviagame.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.triviagenai.triviagen.R
import com.triviagenai.triviagen.core.presentation.navigation.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuitTriviaAlertDialog(
    isShowingExitDialog: MutableState<Boolean>,
    navController: NavController
) {
    if (isShowingExitDialog.value) {
        AlertDialog(
            onDismissRequest = { isShowingExitDialog.value = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        isShowingExitDialog.value = false
                        navController.navigateUp()
                    },
                ) {
                    Text(text = stringResource(R.string.yes))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { isShowingExitDialog.value = false },
                    modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_medium))
                ) {
                    Text(text = stringResource(R.string.no))
                }
            },
            text = {
                Text(text = stringResource(R.string.do_you_really_want_to_leave_a_trivia))
            },
            title = {
                Text(text = stringResource(R.string.leaving_a_trivia))
            }
        )
    }
}