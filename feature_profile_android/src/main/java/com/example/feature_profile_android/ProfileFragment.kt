package com.example.feature_profile_android

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.feature_profile.ProfileStore
import com.example.navigation.NavigationRoot
import com.example.navigation.NavigationScope
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import ru.gubatenko.common_android.BaseFragment
import ru.gubatenko.common_android.onClick
import ru.gubatenko.common_android.sharedGraphViewModel

class ProfileFragment : BaseFragment<ProfileViewModel>(R.layout.fragment_profile) {

    override val viewModel: ProfileViewModel by sharedGraphViewModel(NavigationScope.PROFILE_SCOPE)

    private val avatar: ImageView by lazy { requireView().findViewById(R.id.avatar_id) }
    private val name: TextView by lazy { requireView().findViewById(R.id.name_id) }
    private val signIn: Button by lazy { requireView().findViewById(R.id.sign_in_id) }
    private val signOut: Button by lazy { requireView().findViewById(R.id.sign_out_id) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(profileFeatureAndroidModuleDI)

        signIn.onClick(viewModel::signIn)
        signOut.onClick(viewModel::signOut)

        viewModel.event.observe(viewLifecycleOwner, ::handle)
        viewModel.state.observe(viewLifecycleOwner, ::render)
    }

    override fun onDestroyView() {
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
        Glide.with(avatar).load(state.avatar).into(avatar)
        signIn.text = state.signInButtonText
        signIn.isVisible = state.isSignInButtonVisible
        signOut.text = state.signOutButtonText
        signOut.isVisible = state.isSignOutButtonVisible
    }
}