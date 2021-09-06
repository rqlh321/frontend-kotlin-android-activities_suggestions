package ru.gubatenko.feature_main_android

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.ContentLoadingProgressBar
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.navigation.NavigationRoot
import com.example.navigation.NavigationScope
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import ru.gubatenko.common_android.BaseFragment
import ru.gubatenko.common_android.onClick
import ru.gubatenko.common_android.sharedGraphViewModel
import ru.gubatenko.feature_main.MainStore

class MainFragment : BaseFragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by sharedGraphViewModel(NavigationScope.FRAME_SCOPE)

    private val mainText: TextView by lazy { requireView().findViewById(R.id.result_text_id) }
    private val errorText: TextView by lazy { requireView().findViewById(R.id.error_text_id) }
    private val retryButton: Button by lazy { requireView().findViewById(R.id.retry_button_id) }
    private val saveButton: Button by lazy { requireView().findViewById(R.id.save_button_id) }
    private val loadingProgress: ContentLoadingProgressBar by lazy { requireView().findViewById(R.id.loading_progress_id) }
    private val refresh: SwipeRefreshLayout by lazy { requireView().findViewById(R.id.refresh_id) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(mainFeatureAndroidModuleDI)

        mainText.onClick(viewModel::onContentClick)
        retryButton.onClick(viewModel::reload)
        saveButton.onClick(viewModel::save)
        refresh.setOnRefreshListener(viewModel::refresh)

        viewModel.event.observe(viewLifecycleOwner, ::handle)
        viewModel.state.observe(viewLifecycleOwner, ::render)
    }

    override fun onDestroyView() {
        unloadKoinModules(mainFeatureAndroidModuleDI)
        super.onDestroyView()
    }

    private fun handle(event: MainStore.Event) {
        when (event) {
            is MainStore.Event.ShowToast -> Toast.makeText(
                requireContext(),
                event.message,
                Toast.LENGTH_SHORT
            ).show()
            is MainStore.Event.NavigateTo -> findNavController().navigate(event.locationId)
            is MainStore.Event.NavigateToAuthFlow -> (requireActivity() as? NavigationRoot)?.startAuthorizationFlow()
        }
    }

    private fun render(state: MainStore.State) {
        refresh.isEnabled = state.isRefreshEnabled
        refresh.isRefreshing = state.isRefreshInProgress

        if (state.isLoadingProgressVisible) loadingProgress.show() else loadingProgress.hide()

        retryButton.text = state.retryButtonText
        retryButton.isVisible = state.isRetryButtonVisible

        saveButton.text = state.saveButtonText
        saveButton.isVisible = state.isSaveButtonVisible
        saveButton.isClickable = state.isSaveButtonClickable

        mainText.text = state.action?.activity
        mainText.isVisible = state.isActionTextVisible

        errorText.text = state.errorText
        errorText.isVisible = state.isErrorTextVisible
    }
}