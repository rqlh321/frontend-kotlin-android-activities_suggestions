package com.example.feature_frame_android

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainframeFragment : Fragment(R.layout.fragment_mainframe) {

    private val navigation: BottomNavigationView by lazy { requireView().findViewById(R.id.bottom_navigation_view_id) }
    private val container: FragmentContainerView by lazy { requireView().findViewById(R.id.fragment_container_view_id) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigation.itemIconTintList = null
        navigation.setOnItemReselectedListener {

        }
        container.getFragment<NavHostFragment>()
            ?.let { navigation.setupWithNavController(it.navController) }
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }
}
