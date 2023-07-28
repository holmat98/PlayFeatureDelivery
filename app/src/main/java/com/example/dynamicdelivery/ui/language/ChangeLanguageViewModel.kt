package com.example.dynamicdelivery.ui.language

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dynamicdelivery.managers.DynamicDeliveryManager.InstallationState
import com.example.dynamicdelivery.managers.LanguageInstallationManager
import com.example.dynamicdelivery.managers.LanguageAssistant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class ChangeLanguageViewModel @Inject constructor(
    private val languageAssistant: LanguageAssistant,
    private val languageInstallationManager: LanguageInstallationManager,
) : ViewModel() {

    val supportedLanguages = listOf(
        Locale.US,
        Locale.FRANCE,
        Locale.GERMANY,
        Locale("pl", "PL")
    )

    var currentLanguage = mutableStateOf(languageAssistant.language)
        private set

    var languageInstallationState = mutableStateOf(
        InstallationState.UNKNOWN
    )
        private set

    fun changeLanguage(locale: Locale) {
        viewModelScope.launch(Dispatchers.IO) {
            languageInstallationManager.startInstallation(locale)
                .collect {
                    if (it == InstallationState.INSTALLED) {
                        currentLanguage.value = locale
                        languageAssistant.language = locale
                    }
                    languageInstallationState.value = it
                }
        }
    }
}