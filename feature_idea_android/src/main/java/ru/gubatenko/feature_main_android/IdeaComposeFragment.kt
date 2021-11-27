package ru.gubatenko.feature_main_android

import android.os.Bundle
import android.view.View
import androidx.compose.runtime.Composable
import androidx.navigation.fragment.findNavController
import ru.gubatenko.common_android.BaseComposeFragment
import ru.gubatenko.common_android.sharedGraphViewModel
import ru.gubatenko.domain.navigation.NavigationMain
import ru.gubatenko.feature_main.IdeaStore
import ru.gubatenko.feature_main.ideaStoreModuleDI

class IdeaComposeFragment : BaseComposeFragment() {

    private val viewModel: IdeaViewModel by sharedGraphViewModel()

    override val diModules = listOf(ideaStoreModuleDI, ideaFeatureAndroidModuleDI)

    @Composable
    override fun Screen() = IdeaCompose(viewModel)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.event.observe(viewLifecycleOwner, ::handle)
    }

    private fun handle(event: IdeaStore.Event) {
        when (event) {
            is IdeaStore.Event.NavigateTo         -> findNavController().navigate(event.locationId)
            is IdeaStore.Event.NavigateToAuthFlow -> (requireActivity() as? NavigationMain)?.oferAuthorizationFlow()
        }
    }
}