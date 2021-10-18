package ru.gubatenko.patterns

import android.app.Application
import androidx.work.*
import com.example.audit_android.auditModule
import com.example.domain_android.domainUseCaseAndroidModuleDI
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.gubatenko.data_impl.*
import ru.gubatenko.domain_impl.rootScopeRepoImplModuleDI
import ru.gubatenko.domain_impl.rootScopeUsaCaseImplModuleDI

class ThisApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ThisApplication)
            modules(
                auditModule,
                prefsImplModuleDI,
                textImplModuleDI,
                mapperActionImplModuleDI,
                databaseModuleDI,
                serviceImplModuleDI,
                rootScopeRepoImplModuleDI,
                rootScopeUsaCaseImplModuleDI,
                domainUseCaseAndroidModuleDI
            )
        }
    }

}
