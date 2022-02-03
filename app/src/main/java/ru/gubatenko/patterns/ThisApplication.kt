package ru.gubatenko.patterns

import android.app.Application
import com.example.audit_android.auditModule
import com.example.domain_android.useCaseAndroidModuleDI
import com.example.lib.mapperActionImplModuleDI
import com.example.lib.serviceImplModuleDI
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.gubatenko.data_impl.*
import ru.gubatenko.domain_impl.repoImplModuleDI
import ru.gubatenko.domain_impl.usaCaseImplModuleDI

class ThisApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ThisApplication)
            modules(
                auditModule(),

                prefsAndroidModuleDI(),
                textImplModuleDI(),

                mapperActionAndroidModuleDI(),
                mapperActionImplModuleDI(),

                databaseSQLiteModuleDI(),

                serviceAndroidModuleDI(),
                serviceImplModuleDI(),

                repoImplModuleDI(),
                usaCaseImplModuleDI(),
                useCaseAndroidModuleDI()
            )
        }
    }

}
