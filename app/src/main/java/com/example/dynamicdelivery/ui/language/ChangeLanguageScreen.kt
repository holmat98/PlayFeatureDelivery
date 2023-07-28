package com.example.dynamicdelivery.ui.language

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dynamicdelivery.R
import com.example.dynamicdelivery.extensions.toText
import com.example.dynamicdelivery.managers.DynamicDeliveryManager
import com.example.dynamicdelivery.ui.MainActivity
import java.util.Locale

@Composable
fun ChangeLanguageScreen(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ChangeLanguageViewModel = hiltViewModel(),
) {
    val currentLanguage by remember { viewModel.currentLanguage }
    val languageInstallationState by viewModel.languageInstallationState

    ChangeLanguage(
        modifier = modifier,
        languageInstallationState = languageInstallationState,
        currentLanguage = currentLanguage,
        supportedLanguages = viewModel.supportedLanguages,
        onBackPressed = onBackPressed,
        onLanguageItemClicked = { viewModel.changeLanguage(it) }
    )

    if (languageInstallationState == DynamicDeliveryManager.InstallationState.INSTALLED) {
        val context = LocalContext.current as Activity
        LaunchedEffect(languageInstallationState, Unit) {
            val intent = MainActivity.newInstance(context)
            context.finish()
            context.startActivity(intent)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChangeLanguage(
    modifier: Modifier,
    languageInstallationState: DynamicDeliveryManager.InstallationState,
    currentLanguage: Locale,
    supportedLanguages: List<Locale>,
    onBackPressed: () -> Unit,
    onLanguageItemClicked: (Locale) -> Unit,
) {
    var selectedLanguage: Locale? by remember { mutableStateOf(currentLanguage) }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                    }
                },
                title = { Text(text = stringResource(R.string.change_language)) }
            )
        },
        content = { paddingValues ->
            LazyColumn(modifier = Modifier.padding(paddingValues)) {

                if (languageInstallationState != DynamicDeliveryManager.InstallationState.UNKNOWN) {
                    item {
                        Text(
                            modifier = Modifier.padding(16.dp),
                            text = languageInstallationState.name,
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }

                items(items = supportedLanguages) { language ->
                    LanguageItem(
                        modifier = Modifier.padding(
                            top = 16.dp,
                            start = 16.dp,
                            end = 16.dp
                        ),
                        locale = language,
                        isSelected = selectedLanguage == language,
                        onItemClicked = { selectedLanguage = it }
                    )
                }

                item {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        enabled = selectedLanguage != currentLanguage,
                        onClick = { selectedLanguage?.let { onLanguageItemClicked(it) } }
                    ) {
                        Text(
                            text = stringResource(R.string.button_resource)
                        )
                    }
                }
            }
        }
    )
}

@Composable
private fun LanguageItem(
    locale: Locale,
    isSelected: Boolean,
    onItemClicked: (Locale) -> Unit,
    modifier: Modifier = Modifier,
) {
    val backgroundColor = if (isSelected) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.secondary
    }

    val textColor = if (isSelected) {
        MaterialTheme.colorScheme.onPrimary
    } else {
        MaterialTheme.colorScheme.onSecondary
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .clickable { onItemClicked(locale) },
        contentAlignment = Alignment.CenterStart,
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = locale.toText(),
            fontSize = 20.sp,
            color = textColor
        )
    }
}
