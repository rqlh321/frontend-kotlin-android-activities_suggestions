package ru.gubatenko.patterns

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class FrameFragment : Fragment(R.layout.fragment_frame) {

    private val navigationView: BottomNavigationView by lazy { requireView().findViewById(R.id.nav_view) }
    private val navHost: FragmentContainerView by lazy { requireView().findViewById(R.id.frame_nav_host_fragment) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navHost.getFragment<NavHostFragment>()?.let {
            navigationView.setupWithNavController(it.navController)
        }
    }
}