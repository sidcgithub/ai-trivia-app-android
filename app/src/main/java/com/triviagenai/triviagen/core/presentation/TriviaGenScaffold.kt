package com.triviagenai.triviagen.core.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.triviagenai.triviagen.R
import com.triviagenai.triviagen.core.presentation.navigation.NavigationStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TriviaGenScaffold(
    navigationStatus: NavigationStatus,
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name)
                    )
                },
                navigationIcon = {
                    if (navigationStatus is NavigationStatus.Enabled) {
                        IconButton(
                            onClick = {
                                navigationStatus.backNav()
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Arrow back",
                                modifier = Modifier
                                    .size(dimensionResource(id = R.dimen.element_small))
                                    .padding(bottom = 4.dp)
                            )
                        }
                    }
                }
            )
        }
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            content()
        }
    }
}