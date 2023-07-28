package com.example.dynamicfeature1

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.dynamicdelivery.extensions.with
import com.example.dynamicdelivery.managers.LanguageAssistantEntryPoint
import com.example.dynamicdelivery.ui.theme.DynamicDeliveryTheme
import dagger.hilt.android.EntryPointAccessors
import com.example.dynamicdelivery.R

class DynamicFeature1Activity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DynamicDeliveryTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surface),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.title_dynamicfeature1),
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 25.sp
                    )
                }
            }
        }
    }

    override fun attachBaseContext(newBase: Context) {
        val languageAssistant = EntryPointAccessors.fromApplication(
            newBase,
            LanguageAssistantEntryPoint::class.java
        ).languageAssistant

        super.attachBaseContext(newBase with languageAssistant.language)
    }

}