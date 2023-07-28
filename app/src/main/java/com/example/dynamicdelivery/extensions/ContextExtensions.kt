package com.example.dynamicdelivery.extensions

import android.content.Context
import java.util.Locale

infix fun Context?.with(locale: Locale): Context? {
    if (this == null) return null

    val configuration = resources.configuration

    configuration?.setLocale(locale)

    return applicationContext.createConfigurationContext(configuration)
}