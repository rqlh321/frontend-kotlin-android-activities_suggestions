package ru.gubatenko.feature_main_android

import android.os.Bundle
import android.view.View
import androidx.compose.runtime.Composable
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import ru.gubatenko.common_android.BaseComposeFragment
import ru.gubatenko.common_android.sharedGraphViewModel
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
            is IdeaStore.Event.NavigateTo -> findNavController().navigate(event.locationId)
            is IdeaStore.Event.NavigateToAuthFlow -> requireActivity()
                .findNavController(R.id.fragment_container_view_id)
                .navigate(R.id.ofer_auth_graph)
        }
    }
}