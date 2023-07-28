package com.example.installtimefeaturemodule.contract

import androidx.compose.runtime.Composable
import com.example.dynamicdelivery.contract.InstallTimeFeatureModule
import com.example.installtimefeaturemodule.InstallTimeFeatureModuleScreen

class InstallTimeFeatureModuleImpl : InstallTimeFeatureModule {

    @Composable
    override fun DisplayScreen(onBackPressed: () -> Unit) {
        InstallTimeFeatureModuleScreen(onBackPressed = onBackPressed)
    }
}