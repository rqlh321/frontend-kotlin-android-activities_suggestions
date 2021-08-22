package ru.gubatenko.core_android.android

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.gubatenko.mvi.AuthHandler
import ru.gubatenko.core_android.R

class AuthFragment : BottomSheetDialogFragment() {

    private val signInButton: Button by lazy { requireView().findViewById(R.id.sign_in_button_id) }
    private val localBroadcastManager by lazy { LocalBroadcastManager.getInstance(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(
        R.layout.fragment_auth,
        container,
        false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signInButton.onClick {
            dismiss()
            localBroadcastManager.sendBroadcast(Intent(AuthHandler.AUTH_REQUEST_BROADCAST))
        }
    }
}