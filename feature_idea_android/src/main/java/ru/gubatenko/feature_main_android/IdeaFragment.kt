package ru.gubatenko.feature_main_android

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.core.widget.ContentLoadingProgressBar
import androidx.navigation.fragment.findNavController
import com.example.navigation.NavigationMain
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import ru.gubatenko.common_android.BaseFragment
import ru.gubatenko.common_android.onClick
import ru.gubatenko.common_android.sharedGraphViewModel
import ru.gubatenko.feature_main.MainStore

class IdeaFragment : BaseFragment(R.layout.fragment_idea) {

    private val viewModel: IdeaViewModel by sharedGraphViewModel()

    private val mainText: TextView by lazy { requireView().findViewById(R.id.result_text_id) }
    private val errorText: TextView by lazy { requireView().findViewById(R.id.error_text_id) }
    private val retryButton: Button by lazy { requireView().findViewById(R.id.retry_button_id) }
    private val saveButton: Button by lazy { requireView().findViewById(R.id.save_button_id) }
    private val nextButton: Button by lazy { requireView().findViewById(R.id.next_button_id) }
    private val loadingProgress: ContentLoadingProgressBar by lazy { requireView().findViewById(R.id.loading_progress_id) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(ideaFeatureAndroidModuleDI)

        retryButton.onClick(viewModel::reload)
        saveButton.onClick(viewModel::save)
        nextButton.onClick(viewModel::next)

        viewModel.event.observe(viewLifecycleOwner, ::handle)
        viewModel.state.observe(viewLifecycleOwner, ::render)
    }

    override fun onDestroyView() {
        unloadKoinModules(ideaFeatureAndroidModuleDI)
        super.onDestroyView()
    }

    private fun handle(event: MainStore.Event) {
        when (event) {
            is MainStore.Event.NavigateTo         -> findNavController().navigate(event.locationId)
            is MainStore.Event.NavigateToAuthFlow -> (requireActivity() as? NavigationMain)?.oferAuthorizationFlow()
        }
    }

    private fun render(state: MainStore.State) {
        if (state.isLoadingProgressVisible) loadingProgress.show() else loadingProgress.hide()

        retryButton.text = state.retryButtonText
        retryButton.isVisible = state.isRetryButtonVisible

        saveButton.text = state.saveButtonText
        saveButton.isVisible = state.isSaveButtonVisible
        saveButton.isClickable = state.isSaveButtonClickable

        nextButton.text = state.nextButtonText
        nextButton.isVisible = state.isNextButtonVisible
        nextButton.isClickable = state.isNextButtonClickable

        mainText.text = state.idea?.activity
        mainText.isVisible = state.isIdeaTextVisible

        errorText.text = state.errorText
        errorText.isVisible = state.isErrorTextVisible
    }
}
