package com.triviagenai.triviagen.options.presentation.options

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.triviagenai.triviagen.R
import com.triviagenai.triviagen.core.presentation.TriviaGenScaffold
import com.triviagenai.triviagen.core.presentation.navigation.NavigationStatus

@Composable
fun OptionsScreen(
    navController: NavController,
    optionsViewModel: OptionsViewModel
) {
    val themeState by optionsViewModel.darkmodeState.collectAsState()

    TriviaGenScaffold(
        navigationStatus = NavigationStatus.Enabled(navController)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.padding_medium))
        ) {
            Text(
                text = stringResource(id = R.string.dark_theme)
            )
            Switch(
                checked = themeState,
                onCheckedChange = { optionsViewModel.changeDarkmode() },
                thumbContent = {
                    if(themeState) {
                        Icon(
                            painter = painterResource(id = R.drawable.dark_mode_icon_24dp),
                            contentDescription = "Dark mode",
                            modifier = Modifier.size(18.dp)
                        )
                    } else {
                        Icon(
                            painter = painterResource(id = R.drawable.light_mode_icon_24dp),
                            contentDescription = "Light mode",
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            )
        }
    }
}