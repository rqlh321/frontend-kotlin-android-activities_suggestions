package ru.gubatenko.patterns

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.gubatenko.data_impl.serviceImplModuleDI
import ru.gubatenko.domain_impl.repoImplModuleDI
import ru.gubatenko.domain_impl.usaCaseImplModuleDI
import ru.gubatenko.feature_main_android.mainFeatureAndroidModuleDI
import ru.gubatenko.data_impl.daoModuleDI
import ru.gubatenko.data_impl.dtoMapperImplModuleDI
import ru.gubatenko.data_impl.storedMapperImplModuleDI

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
    }
}