package com.example.dynamicdelivery.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.dynamicdelivery.extensions.with
import com.example.dynamicdelivery.managers.LanguageAssistantEntryPoint
import com.example.dynamicdelivery.ui.navigation.MainNavigation
import com.example.dynamicdelivery.ui.navigation.MainNavigation.mainNavigationGraph
import com.example.dynamicdelivery.ui.theme.DynamicDeliveryTheme
import com.google.android.play.core.splitcompat.SplitCompat
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            DynamicDeliveryTheme {
                NavHost(
                    navController = navController,
                    startDestination = MainNavigation.ROOT
                ) { mainNavigationGraph(navController) }
            }
        }
    }

    override fun attachBaseContext(newBase: Context) {
        val languageAssistant = EntryPointAccessors.fromApplication(
            newBase,
            LanguageAssistantEntryPoint::class.java
        ).languageAssistant

        super.attachBaseContext(newBase with languageAssistant.language)
        SplitCompat.install(this)
    }

    companion object {
        fun newInstance(context: Context) = Intent(context, MainActivity::class.java)
    }
}
