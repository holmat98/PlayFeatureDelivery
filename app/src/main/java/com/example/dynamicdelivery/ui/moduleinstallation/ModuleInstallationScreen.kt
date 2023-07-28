package com.example.dynamicdelivery.ui.moduleinstallation

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dynamicdelivery.managers.DynamicDeliveryManager

@Composable
fun ModuleInstallationScreen(
    onInstallationSucceeded: () -> Unit,
    onInstallationFailed: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ModuleInstallationViewModel = hiltViewModel(),
) {
    val installationState by viewModel.installationState.collectAsState()

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = installationState.name,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 25.sp
        )
    }

    if (installationState == DynamicDeliveryManager.InstallationState.FAILED) {
        LaunchedEffect(Unit) { onInstallationFailed() }
    }

    if (installationState == DynamicDeliveryManager.InstallationState.INSTALLED) {
        val activity = LocalContext.current as Activity
        LaunchedEffect(Unit) {
            val intent = Intent().apply {
                setClassName(
                    activity.packageName,
                    "com.example.ondemandmodule.OnDemandModuleActivity"
                )
            }
            activity.startActivity(intent)
            onInstallationSucceeded()
        }
    }
}