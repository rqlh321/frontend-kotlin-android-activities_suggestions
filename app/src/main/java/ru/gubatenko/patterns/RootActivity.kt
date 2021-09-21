package ru.gubatenko.patterns

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.navigation.NavigationRoot
import com.example.navigation.NavigationScope
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import ru.gubatenko.data_impl.rootScopeDaoModuleDI
import ru.gubatenko.data_impl.rootScopeDtoMapperImplModuleDI
import ru.gubatenko.data_impl.rootScopeServiceImplModuleDI
import ru.gubatenko.data_impl.rootScopeStoredMapperImplModuleDI
import ru.gubatenko.domain_impl.rootScopeRepoImplModuleDI
import ru.gubatenko.domain_impl.rootScopeUsaCaseImplModuleDI

class RootActivity : AppCompatActivity(R.layout.activity_root), NavigationRoot {

    private val rootScope = listOf(
        rootScopeStoredMapperImplModuleDI,
        rootScopeDtoMapperImplModuleDI,
        rootScopeDaoModuleDI,
        rootScopeServiceImplModuleDI,
        rootScopeRepoImplModuleDI,
        rootScopeUsaCaseImplModuleDI,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(rootScope)
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(rootScope)
    }

    override fun startAuthorizationFlow() {
        findNavController(R.id.root_host_fragment).navigate(R.id.auth_graph)
    }

    override fun navigationScopeId(scope: NavigationScope) = R.id.frame_graph_id

}