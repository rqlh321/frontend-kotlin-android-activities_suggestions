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
                serviceImplModuleDI,
                repoImplModuleDI,
                usaCaseImplModuleDI,
                mainFeatureAndroidModuleDI,
            )
        }

        val constraint = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()

        val uploadWorkRequest = PeriodicWorkRequestBuilder<UploadWorker>(5, TimeUnit.SECONDS)
            .setConstraints(constraint)
            .build()

//        WorkManager.getInstance(this)
//            .enqueueUniquePeriodicWork(
//                "MyAppNameBackgroundSync",
//                ExistingPeriodicWorkPolicy.KEEP,
//                uploadWorkRequest
//            )
    }
}