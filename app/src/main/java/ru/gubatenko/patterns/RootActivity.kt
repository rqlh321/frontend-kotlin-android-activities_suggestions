package ru.gubatenko.patterns

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.findNavController
import com.example.navigation.AUTH_SUCCESS_BROADCAST
import com.example.navigation.NavigationRoot
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import ru.gubatenko.data_impl.rootScopeDaoModuleDI
import ru.gubatenko.data_impl.rootScopeDtoMapperImplModuleDI
import ru.gubatenko.data_impl.rootScopeServiceImplModuleDI
import ru.gubatenko.data_impl.rootScopeStoredMapperImplModuleDI
import ru.gubatenko.domain.usecase.IsAuthorizedUseCase
import ru.gubatenko.domain_impl.rootScopeRepoImplModuleDI
import ru.gubatenko.domain_impl.rootScopeUsaCaseImplModuleDI

class RootActivity : AppCompatActivity(), NavigationRoot {

    private val rootScope = listOf(
        rootScopeStoredMapperImplModuleDI,
        rootScopeDtoMapperImplModuleDI,
        rootScopeDaoModuleDI,
        rootScopeServiceImplModuleDI,
        rootScopeRepoImplModuleDI,
        rootScopeUsaCaseImplModuleDI,
    )

    private val isAuthorizedUseCase: IsAuthorizedUseCase by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(rootScope)
        setContentView(R.layout.activity_root)
        if (savedInstanceState == null) {
            lifecycleScope.launchWhenCreated {
                if (isAuthorizedUseCase.execute()) {
                    LocalBroadcastManager.getInstance(this@RootActivity)
                        .sendBroadcast(Intent(AUTH_SUCCESS_BROADCAST))
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(rootScope)
    }

    override fun startAuthorizationFlow() {
        findNavController(R.id.nav_host_fragment).navigate(R.id.auth_graph)
    }

}