package ru.gubatenko.feature_main_android

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.gubatenko.feature_main.MainStore

class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels { MainViewModelFactory() }

    private val mainText: TextView by lazy { requireView().findViewById(R.id.main_text_id) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainText.onClick(viewModel::onContentClick)

        viewModel.event.observe(viewLifecycleOwner, ::trigger)
        viewModel.state.observe(viewLifecycleOwner, ::render)
    }

    private fun trigger(event: MainStore.Event) {
        when (event) {
            MainStore.Event.ShowToast -> Toast.makeText(requireContext(), "Click!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun render(state: MainStore.State) {
        mainText.text = state.contentValue.toString()
    }
}