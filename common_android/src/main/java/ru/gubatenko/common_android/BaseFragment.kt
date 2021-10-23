package ru.gubatenko.common_android

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module

abstract class BaseFragment(layout: Int) : Fragment(layout) {

    abstract val diModules: List<Module>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(diModules)
    }

    override fun onDestroyView() {
        unloadKoinModules(diModules)
        super.onDestroyView()
    }
}