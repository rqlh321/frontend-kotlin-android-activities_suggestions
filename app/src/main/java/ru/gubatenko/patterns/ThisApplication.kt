package ru.gubatenko.patterns

import android.app.Application
import android.content.IntentFilter
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.work.*
import com.example.navigation.AUTH_SUCCESS_BROADCAST
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ThisApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin { androidContext(this@ThisApplication) }
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(
                SuccessAuthReceiver(),
                IntentFilter(AUTH_SUCCESS_BROADCAST)
            )
    }

}