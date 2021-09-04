package ru.gubatenko.patterns

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.lifecycleScope
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.navigation.AUTH_SUCCESS_BROADCAST
import com.example.navigation.NavigationRoot
import com.example.navigation.NavigationScope
import com.example.navigation.OnSuccessAuthorizationSensitive
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
import ru.gubatenko.patterns.UploadWorker.Companion.runUploadWorker

class RootActivity : AppCompatActivity(R.layout.activity_root), NavigationRoot {

    private val rootScope = listOf(
        rootScopeStoredMapperImplModuleDI,
        rootScopeDtoMapperImplModuleDI,
        rootScopeDaoModuleDI,
        rootScopeServiceImplModuleDI,
        rootScopeRepoImplModuleDI,
        rootScopeUsaCaseImplModuleDI,
    )

    private val isAuthorizedUseCase: IsAuthorizedUseCase by inject()

    private val successAuthReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) = setupAsAuthorized()
    }

    private val fragmentContainerView: FragmentContainerView by lazy { findViewById(R.id.root_host_fragment) }

    private fun setupAsAuthorized() {
        runUploadWorker()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        val frameFragment = fragmentContainerView.getFragment<NavHostFragment>()
            ?.childFragmentManager
            ?.fragments
            ?.filterIsInstance(FrameFragment::class.java)
            ?.firstOrNull()
        val navigationHostFragmentOfFrameFragment = frameFragment
            ?.childFragmentManager
            ?.fragments
            ?.filterIsInstance(NavHostFragment::class.java)
            ?.firstOrNull()
        navigationHostFragmentOfFrameFragment
            ?.childFragmentManager
            ?.fragments
            ?.filterIsInstance(OnSuccessAuthorizationSensitive::class.java)
            ?.forEach { it.handleSuccessAuthorization() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(rootScope)
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(
                successAuthReceiver,
                IntentFilter(AUTH_SUCCESS_BROADCAST)
            )

        if (savedInstanceState == null) {
            lifecycleScope.launchWhenCreated {
                if (isAuthorizedUseCase.execute()) {
                    setupAsAuthorized()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(this)
            .unregisterReceiver(successAuthReceiver)
        unloadKoinModules(rootScope)
    }

    override fun startAuthorizationFlow() {
        findNavController(R.id.root_host_fragment).navigate(R.id.auth_graph)
    }

    override fun navigationScopeId(scope: NavigationScope) = R.id.frame_graph_id

}