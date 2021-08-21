package ru.gubatenko.core_android.android.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.gubatenko.core.AuthHandler
import ru.gubatenko.core_android.R
import ru.gubatenko.core_android.android.onClick

class AuthFragment : BottomSheetDialogFragment() {

    private val signInButton: Button by lazy { requireView().findViewById(R.id.sign_in_button_id) }

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
            (requireActivity() as? AuthHandler)?.signIn()
        }
    }
}