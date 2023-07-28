package com.example.dynamicdelivery.ui.start

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.dynamicdelivery.R
import com.example.dynamicdelivery.ui.ondemandmodule2.OnDemandModule2NavigationActivity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(
    onChangeLanguageClicked: () -> Unit,
    onGoToModule1Clicked: () -> Unit,
    onGoToInstallationTimeModule: () -> Unit,
    onGoToConditionalModule: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val activity = LocalContext.current
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(title = { Text(text = stringResource(R.string.start_screen)) })
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .verticalScroll(scrollState)
            ) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    onClick = onChangeLanguageClicked
                ) {
                    Text(text = stringResource(R.string.change_language))
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onClick = onGoToModule1Clicked
                ) {
                    Text(text = stringResource(R.string.go_to_module_1))
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    onClick = onGoToInstallationTimeModule
                ) {
                    Text(text = stringResource(R.string.go_to_module_2))
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onClick = onGoToConditionalModule
                ) {
                    Text(text = stringResource(R.string.go_to_conditional_module))
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    onClick = {
                        activity.startActivity(OnDemandModule2NavigationActivity.newIntent(activity))
                    }
                ) {
                    Text(text = stringResource(R.string.go_to_module_4))
                }
            }
        }
    )
}