package ru.gubatenko.patterns

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module
import ru.gubatenko.domain_impl.data.dao.ActivityDao
import ru.gubatenko.domain_impl.mapperImplModuleDI
import ru.gubatenko.domain_impl.repoImplModuleDI
import ru.gubatenko.domain_impl.serviceImplModuleDI
import ru.gubatenko.domain_impl.usaCaseImplModuleDI
import ru.gubatenko.feature_main_android.mainFeatureAndroidModuleDI
import ru.gubatenko.patterns.storage.ActionDaoSharedPrefImpl

class ThisApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@ThisApplication)
            modules(
                module { single<ActivityDao> { ActionDaoSharedPrefImpl() } },
                serviceImplModuleDI,
                mapperImplModuleDI,
                repoImplModuleDI,
                usaCaseImplModuleDI,
                mainFeatureAndroidModuleDI,
            )
        }
    }
}