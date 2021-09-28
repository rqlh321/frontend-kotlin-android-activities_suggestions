package com.example.feature_profile_android

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.bumptech.glide.Glide
import com.example.feature_profile.ProfileStore
import com.example.navigation.AUTH_SUCCESS_BROADCAST
import com.example.navigation.NavigationRoot
import com.example.navigation.NavigationScope
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.RelativeCornerSize
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import ru.gubatenko.common_android.BaseFragment
import ru.gubatenko.common_android.onClick
import ru.gubatenko.common_android.sharedGraphViewModel

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {

    private val viewModel: ProfileViewModel by sharedGraphViewModel(NavigationScope.FRAME_SCOPE)

    private val avatar: ShapeableImageView by lazy { requireView().findViewById(R.id.avatar_id) }
    private val name: TextView by lazy { requireView().findViewById(R.id.name_id) }
    private val email: TextView by lazy { requireView().findViewById(R.id.email_id) }
    private val signIn: Button by lazy { requireView().findViewById(R.id.sign_in_id) }
    private val signOut: Button by lazy { requireView().findViewById(R.id.sign_out_id) }

    private val successAuthReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            viewModel.successAuthorization()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(profileFeatureAndroidModuleDI)

        avatar.shapeAppearanceModel = avatar.shapeAppearanceModel
            .toBuilder()
            .setAllCornerSizes(RelativeCornerSize(.5f))
            .build()

        LocalBroadcastManager.getInstance(requireContext())
            .registerReceiver(
                successAuthReceiver,
                IntentFilter(AUTH_SUCCESS_BROADCAST)
            )

        signIn.onClick(viewModel::signIn)
        signOut.onClick(viewModel::signOut)

        viewModel.event.observe(viewLifecycleOwner, ::handle)
        viewModel.state.observe(viewLifecycleOwner, ::render)
    }

    override fun onDestroyView() {
        LocalBroadcastManager.getInstance(requireContext())
            .unregisterReceiver(successAuthReceiver)

        unloadKoinModules(profileFeatureAndroidModuleDI)
        super.onDestroyView()
    }

    private fun handle(event: ProfileStore.Event) {
        when (event) {
            is ProfileStore.Event.NavigateToAuthFlow -> (requireActivity() as? NavigationRoot)?.startAuthorizationFlow()
        }
    }

    private fun render(state: ProfileStore.State) {
        name.text = state.name
        email.text = state.email
        Glide.with(avatar).load(state.avatar).into(avatar)
        signIn.text = state.signInButtonText
        signIn.isVisible = state.isSignInButtonVisible
        signOut.text = state.signOutButtonText
        signOut.isVisible = state.isSignOutButtonVisible
    }

}