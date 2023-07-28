package com.example.dynamicdelivery.extensions

import java.util.Locale

fun String.toLocale(): Locale {
    val (language, country) = this.split("_")

    return Locale(language, country)
}