package com.example.feature_auth_android

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.feature_auth.OfferAuthStore
import com.example.navigation.AUTH_REQUEST_BROADCAST
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import ru.gubatenko.common_android.onClick

class OfferAuthFragment : BottomSheetDialogFragment() {

    private val viewModel: OfferAuthViewModel by viewModel()

    private val titleText: TextView by lazy { requireView().findViewById(R.id.title_text_id) }
    private val acceptButton: Button by lazy { requireView().findViewById(R.id.accept_button_id) }

    private val localBroadcastManager by lazy { LocalBroadcastManager.getInstance(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(
        R.layout.fragment_offer_auth,
        container,
        false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(offerAuthAndroidModuleDI)
        acceptButton.onClick(viewModel::accept)

        viewModel.state.observe(viewLifecycleOwner, ::render)
        viewModel.event.observe(viewLifecycleOwner, ::handle)
    }

    override fun onDestroyView() {
        unloadKoinModules(offerAuthAndroidModuleDI)
        super.onDestroyView()
    }

    private fun handle(event: OfferAuthStore.Event) {
        when (event) {
            is OfferAuthStore.Event.NavigateToAuthorization -> {
                dismiss()
                localBroadcastManager.sendBroadcast(Intent(AUTH_REQUEST_BROADCAST))
            }
        }
    }

    private fun render(state: OfferAuthStore.State) {
        titleText.text = state.titleText
        acceptButton.text = state.acceptTextButton
    }

    override fun onCancel(dialog: DialogInterface) {
        viewModel.reject()
        super.onCancel(dialog)
    }
}