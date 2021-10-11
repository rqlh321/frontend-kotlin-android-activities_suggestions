package ru.gubatenko.patterns

import android.app.Application
import androidx.work.*
import com.example.audit.Logger
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import ru.gubatenko.patterns.audit.LoggerTimber
import ru.gubatenko.patterns.work.workerUseCaseImplModuleDI
import timber.log.Timber

class ThisApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin {
            androidContext(this@ThisApplication)
            modules(
                module { single<Logger> { LoggerTimber() } },
                workerUseCaseImplModuleDI
            )
        }
    }

}
