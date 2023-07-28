package com.example.dynamicdelivery.contract

import androidx.compose.runtime.Composable

interface InstallTimeFeatureModule {

    @Composable
    fun DisplayScreen(onBackPressed: () -> Unit)

    companion object {
        private const val IMPLEMENTATION_NAME =
            "com.example.installtimefeaturemodule.contract.InstallTimeFeatureModuleImpl"

        fun newInstance(): InstallTimeFeatureModule =
            Class.forName(IMPLEMENTATION_NAME).newInstance() as InstallTimeFeatureModule
    }
}