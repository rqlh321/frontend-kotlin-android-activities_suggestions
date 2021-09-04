package ru.gubatenko.patterns

import android.app.Application
import androidx.work.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ThisApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin { androidContext(this@ThisApplication) }
    }

}