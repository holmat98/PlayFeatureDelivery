package com.example.dynamicdelivery.extensions

import java.util.Locale

fun Locale.toText(): String = "${this.language}_${this.country}"