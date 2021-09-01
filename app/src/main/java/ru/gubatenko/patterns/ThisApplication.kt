package ru.gubatenko.patterns

import android.app.Application
import androidx.work.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.gubatenko.data_impl.daoModuleDI
import ru.gubatenko.data_impl.dtoMapperImplModuleDI
import ru.gubatenko.data_impl.serviceImplModuleDI
import ru.gubatenko.data_impl.storedMapperImplModuleDI
import ru.gubatenko.domain_impl.repoImplModuleDI
import ru.gubatenko.domain_impl.usaCaseImplModuleDI
import ru.gubatenko.feature_main_android.mainFeatureAndroidModuleDI
import ru.gubatenko.patterns.firebase.service.serviceFirebaseImplModuleDI
import java.util.concurrent.TimeUnit

class ThisApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@ThisApplication)
            modules(
                storedMapperImplModuleDI,
                dtoMapperImplModuleDI,
                daoModuleDI,
                serviceFirebaseImplModuleDI,
                serviceImplModuleDI,
                repoImplModuleDI,
                usaCaseImplModuleDI,
                mainFeatureAndroidModuleDI,
            )
        }

        val constraint = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val uploadWorkRequest = PeriodicWorkRequestBuilder<UploadWorker>(
            repeatInterval = 1,
            repeatIntervalTimeUnit = TimeUnit.MINUTES,
            flexTimeInterval = 10,
            flexTimeIntervalUnit = TimeUnit.SECONDS
        )
            .setConstraints(constraint)
            .build()

        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork(
                "MyAppNameBackgroundSync",
                ExistingPeriodicWorkPolicy.KEEP,
                uploadWorkRequest
            )
    }
}