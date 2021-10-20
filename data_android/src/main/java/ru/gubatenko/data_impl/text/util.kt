package ru.gubatenko.data_impl.text

import android.content.Context
import android.os.Build
import java.util.*

val Context.country: String
    get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        val locales = this.resources.configuration.locales
        if (locales.size() > 0) locales.get(0).language else Locale.ENGLISH.language
    } else {
        resources.configuration.locale.language
    }