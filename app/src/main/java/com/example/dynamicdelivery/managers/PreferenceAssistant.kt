package com.example.dynamicdelivery.managers

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface PreferenceAssistant {

    fun saveString(key: String, value: String)

    fun getString(key: String): String?

}

class PreferenceAssistantImpl @Inject constructor(
    @ApplicationContext context: Context
) : PreferenceAssistant {

    private val sharedPreferences = context.getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)

    override fun saveString(key: String, value: String) {
        sharedPreferences.edit()
            .putString(key, value)
            .apply()
    }

    override fun getString(key: String): String? =
        sharedPreferences.getString(key, null)
}