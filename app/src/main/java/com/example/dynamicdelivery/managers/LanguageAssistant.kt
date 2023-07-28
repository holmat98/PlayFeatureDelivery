package com.example.dynamicdelivery.managers

import com.example.dynamicdelivery.extensions.toLocale
import com.example.dynamicdelivery.extensions.toText
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.Locale
import javax.inject.Inject

@EntryPoint
@InstallIn(SingletonComponent::class)
interface LanguageAssistantEntryPoint {
    val languageAssistant: LanguageAssistant
}


interface LanguageAssistant {

    var language: Locale
}

class LanguageAssistantImpl @Inject constructor(
    private val preferenceAssistant: PreferenceAssistant,
) : LanguageAssistant {

    private var _language: Locale =
        preferenceAssistant.getString(CHOSEN_LANGUAGE)?.toLocale() ?: Locale.US
    override var language: Locale
        get() = _language
        set(value) {
            preferenceAssistant.saveString(CHOSEN_LANGUAGE, value.toText())
            _language = value
        }

    private companion object {
        const val CHOSEN_LANGUAGE = "CHOSEN_LANGUAGE"
    }
}