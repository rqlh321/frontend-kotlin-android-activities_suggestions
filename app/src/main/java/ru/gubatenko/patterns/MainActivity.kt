package ru.gubatenko.patterns

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import ru.gubatenko.mvi.AuthHandler.AUTH_REQUEST_BROADCAST
import ru.gubatenko.mvi.AuthHandler.AUTH_SUCCESS_BROADCAST

class MainActivity : AppCompatActivity() {

    private val googleSignInIntent: Intent by lazy {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        GoogleSignIn.getClient(this, gso).signInIntent
    }

    private val googleSignInForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        try {
            val signedInAccountFromIntent = GoogleSignIn.getSignedInAccountFromIntent(it?.data)
            val account = signedInAccountFromIntent.getResult(ApiException::class.java)
            account.id
            localBroadcastManager.sendBroadcast(Intent(AUTH_SUCCESS_BROADCAST))
            AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    private val authRequestBroadcastListener = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) =
            googleSignInForResult.launch(googleSignInIntent)
    }

    private val localBroadcastManager by lazy { LocalBroadcastManager.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        localBroadcastManager.registerReceiver(
            authRequestBroadcastListener,
            IntentFilter(AUTH_REQUEST_BROADCAST)
        )
    }

    override fun onStop() {
        localBroadcastManager.unregisterReceiver(authRequestBroadcastListener)
        super.onStop()
    }
}