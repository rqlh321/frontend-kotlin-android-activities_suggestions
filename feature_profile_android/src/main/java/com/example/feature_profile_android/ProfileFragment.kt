package com.example.feature_profile_android

import android.app.UiModeManager
import android.app.UiModeManager.MODE_NIGHT_NO
import android.app.UiModeManager.MODE_NIGHT_YES
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.UI_MODE_SERVICE
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isVisible
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.feature_profile.ProfileStore
import com.example.feature_profile.profileFeatureModuleDI
import com.example.feature_profile_android.adapter.PrefAdapter
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.RelativeCornerSize
import ru.gubatenko.common_android.BaseFragment
import ru.gubatenko.common_android.onClick
import ru.gubatenko.common_android.sharedGraphViewModel
import ru.gubatenko.domain.navigation.AUTH_SUCCESS_BROADCAST

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {

    private val viewModel: ProfileViewModel by sharedGraphViewModel()

    private val avatar: ShapeableImageView by lazy { requireView().findViewById(R.id.avatar_id) }
    private val name: TextView by lazy { requireView().findViewById(R.id.name_id) }
    private val email: TextView by lazy { requireView().findViewById(R.id.email_id) }
    private val signIn: Button by lazy { requireView().findViewById(R.id.sign_in_id) }
    private val signOut: Button by lazy { requireView().findViewById(R.id.sign_out_id) }
    private val prefsList: RecyclerView by lazy { requireView().findViewById(R.id.prefs_list_id) }

    private val prefsListAdapter: PrefAdapter by lazy { PrefAdapter(viewModel::switchPref) }

    private val successAuthReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            viewModel.successAuthorization()
        }
    }
    override val diModules = listOf(profileFeatureModuleDI, profileFeatureAndroidModuleDI)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefsList.adapter = prefsListAdapter

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

        super.onDestroyView()
    }

    private fun handle(event: ProfileStore.Event) {
        when (event) {
            is ProfileStore.Event.NavigateToAuthFlow -> requireActivity()
                .findNavController(R.id.fragment_container_view_id)
                .navigate(R.id.auth_graph)
            is ProfileStore.Event.ChangeAppThem -> {
//                AppCompatDelegate.setDefaultNightMode(
//                    if (event.isDarkThemEnabled) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
//                )
                //todo handle recreate
                requireActivity().recreate()
            }
        }
    }

    private fun render(state: ProfileStore.State) {
        Glide.with(avatar).load(state.avatar).into(avatar)
        name.text = state.name
        email.text = state.email
        prefsListAdapter.data = state.prefs
        signIn.text = state.signInButtonText
        signIn.isVisible = state.isSignInButtonVisible
        signOut.text = state.signOutButtonText
        signOut.isVisible = state.isSignOutButtonVisible
    }

}
