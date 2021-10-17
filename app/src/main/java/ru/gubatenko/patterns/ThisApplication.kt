package ru.gubatenko.patterns

import android.app.Application
import androidx.work.*
import com.example.audit.Logger
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import ru.gubatenko.data_impl.rootScopeDaoModuleDI
import ru.gubatenko.data_impl.rootScopeDtoMapperImplModuleDI
import ru.gubatenko.data_impl.rootScopeServiceImplModuleDI
import ru.gubatenko.data_impl.rootScopeStoredMapperImplModuleDI
import ru.gubatenko.domain_impl.rootScopeRepoImplModuleDI
import ru.gubatenko.domain_impl.rootScopeUsaCaseImplModuleDI
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
                rootScopeStoredMapperImplModuleDI,
                rootScopeDtoMapperImplModuleDI,
                rootScopeDaoModuleDI,
                rootScopeServiceImplModuleDI,
                rootScopeRepoImplModuleDI,
                rootScopeUsaCaseImplModuleDI,
                workerUseCaseImplModuleDI
            )
        }
    }

}
