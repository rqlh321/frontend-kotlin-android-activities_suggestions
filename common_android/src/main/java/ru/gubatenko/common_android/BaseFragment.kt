package ru.gubatenko.common_android

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.navigation.AUTH_SUCCESS_BROADCAST
import org.koin.android.ext.android.inject
import ru.gubatenko.mvi.Logger

abstract class BaseFragment<VM : BaseViewModel>(layout: Int) : Fragment(layout) {

    val logger: Logger by inject()

    protected abstract val viewModel: VM

    private val authSuccessBroadcastListener = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) =
            viewModel.onSuccessAuthorization()
    }

    private val localBroadcastManager by lazy {
        LocalBroadcastManager.getInstance(requireContext())
    }

    override fun onStart() {
        super.onStart()
        localBroadcastManager.registerReceiver(
            authSuccessBroadcastListener,
            IntentFilter(AUTH_SUCCESS_BROADCAST)
        )
    }

    override fun onStop() {
        localBroadcastManager.unregisterReceiver(authSuccessBroadcastListener)
        super.onStop()
    }
}