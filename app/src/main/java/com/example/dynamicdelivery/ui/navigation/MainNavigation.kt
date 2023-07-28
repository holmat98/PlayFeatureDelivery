package com.example.dynamicdelivery.ui.navigation

import android.app.Activity
import android.content.Intent
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.dynamicdelivery.contract.InstallTimeFeatureModule
import com.example.dynamicdelivery.ui.start.StartScreen
import com.example.dynamicdelivery.ui.language.ChangeLanguageScreen
import com.example.dynamicdelivery.ui.moduleinstallation.ModuleInstallationScreen
import com.example.dynamicdelivery.ui.moduleinstallation.ModuleInstallationViewModel.Companion.MODULE_NAME_ARGUMENT

object MainNavigation {

    const val ROOT = "ROOT"
    private const val START = "$ROOT/START"
    private const val CHANGE_LANGUAGE = "$ROOT/CHANGE_LANGUAGE"
    private const val MODULE_INSTALLATION = "$ROOT/MODULE_INSTALLATION"
    private const val INSTALL_TIME_MODULE = "$ROOT/INSTALL_TIME"

    fun NavGraphBuilder.mainNavigationGraph(navController: NavController): Unit =
        navigation(startDestination = START, route = ROOT) {
            startScreen(navController)
            changeLanguage(navController)
            module1Installation(navController)
            installTimeModule(navController)
        }

    private fun NavGraphBuilder.startScreen(navController: NavController): Unit =
        composable(START) {
            val activity = LocalContext.current as Activity

            StartScreen(
                onChangeLanguageClicked = { navController.navigateToChangeLanguage() },
                onGoToModule1Clicked = { navController.navigateToDynamicModule1Installation() },
                onGoToInstallationTimeModule = { navController.navigateToInstallTimeModule() },
                onGoToConditionalModule = {
                    val intent = Intent().apply {
                        setClassName(
                            activity.packageName,
                            "com.example.conditionalfeaturemodule.ConditionalFeatureModuleActivity"
                        )
                    }
                    activity.startActivity(intent)
                }
            )
        }

    private fun NavGraphBuilder.changeLanguage(navController: NavController): Unit =
        composable(CHANGE_LANGUAGE) {
            ChangeLanguageScreen(
                onBackPressed = { navController.navigateUp() }
            )
        }

    private fun NavGraphBuilder.module1Installation(navController: NavController): Unit =
        composable(
            route = "$MODULE_INSTALLATION/$MODULE_NAME_ARGUMENT={$MODULE_NAME_ARGUMENT}",
            arguments = listOf(
                navArgument(MODULE_NAME_ARGUMENT) { type = NavType.StringType },
            )
        ) {
            ModuleInstallationScreen(
                onInstallationSucceeded = { navController.navigateUp() },
                onInstallationFailed = { navController.navigateUp() }
            )
        }

    private fun NavGraphBuilder.installTimeModule(navController: NavController): Unit =
        composable(INSTALL_TIME_MODULE) {
            InstallTimeFeatureModule.newInstance().DisplayScreen(
                onBackPressed = { navController.navigateUp() }
            )
        }

    private fun NavController.navigateToChangeLanguage(): Unit =
        navigate(CHANGE_LANGUAGE)

    private fun NavController.navigateToInstallTimeModule(): Unit =
        navigate(INSTALL_TIME_MODULE)

    private fun NavController.navigateToDynamicModule1Installation() =
        navigate("$MODULE_INSTALLATION/$MODULE_NAME_ARGUMENT=dynamicfeature1")
}