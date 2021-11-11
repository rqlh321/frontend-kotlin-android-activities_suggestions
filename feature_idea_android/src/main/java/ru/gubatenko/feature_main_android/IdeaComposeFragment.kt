package ru.gubatenko.feature_main_android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import ru.gubatenko.common_android.sharedGraphViewModel
import ru.gubatenko.feature_main.ideaStoreModuleDI

class IdeaComposeFragment : Fragment() {

    private val viewModel: IdeaViewModel by sharedGraphViewModel()

    private val diModules = listOf(ideaStoreModuleDI, ideaFeatureAndroidModuleDI)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setContent {
            ComposePlaygroundTheme {
                IdeaScreen(viewModel)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(diModules)
    }

    override fun onDestroyView() {
        unloadKoinModules(diModules)
        super.onDestroyView()
    }

}