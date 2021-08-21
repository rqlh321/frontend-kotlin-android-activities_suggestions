package ru.gubatenko.feature_main_android

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import ru.gubatenko.core_android.android.onClick
import ru.gubatenko.feature_main.MainStore

class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels { MainViewModelFactory() }

    private val mainText: TextView by lazy { requireView().findViewById(R.id.result_text_id) }
    private val errorText: TextView by lazy { requireView().findViewById(R.id.error_text_id) }
    private val retryButton: Button by lazy { requireView().findViewById(R.id.retry_button_id) }
    private val saveButton: Button by lazy { requireView().findViewById(R.id.save_button_id) }
    private val loadingProgress: ContentLoadingProgressBar by lazy { requireView().findViewById(R.id.loading_progress_id) }
    private val refresh: SwipeRefreshLayout by lazy { requireView().findViewById(R.id.refresh_id) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainText.onClick(viewModel::onContentClick)
        retryButton.onClick(viewModel::reload)
        saveButton.onClick(viewModel::save)
        refresh.setOnRefreshListener(viewModel::refresh)

        viewModel.event.observe(viewLifecycleOwner, ::handle)
        viewModel.state.observe(viewLifecycleOwner, ::render)
    }

    private fun handle(event: MainStore.Event) {
        when (event) {
            is MainStore.Event.ShowToast -> Toast.makeText(
                requireContext(),
                event.message,
                Toast.LENGTH_SHORT
            ).show()
            is MainStore.Event.NavigateTo -> findNavController().navigate(event.locationId)
        }
    }

    private fun render(state: MainStore.State) {
        refresh.isEnabled = state.isRefreshEnabled
        refresh.isRefreshing = state.isRefreshProgressVisible
        if (state.isLoadingProgressVisible) loadingProgress.show() else loadingProgress.hide()

        retryButton.text = state.retryButtonText
        retryButton.visibility = if (state.isRetryButtonVisible) View.VISIBLE else View.GONE

        saveButton.text = state.saveButtonText
        saveButton.visibility = if (state.isSaveButtonVisible) View.VISIBLE else View.GONE

        mainText.text = state.action?.activity
        mainText.visibility = if (state.isActionTextVisible) View.VISIBLE else View.GONE

        errorText.text = state.errorText
        errorText.visibility = if (state.isErrorTextVisible) View.VISIBLE else View.GONE
    }
}