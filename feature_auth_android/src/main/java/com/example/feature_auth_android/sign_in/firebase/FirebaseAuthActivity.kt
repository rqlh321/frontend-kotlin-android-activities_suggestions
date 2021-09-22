package com.example.feature_auth_android.sign_in.firebase

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.feature_auth_android.R
import com.example.navigation.AUTH_SUCCESS_BROADCAST
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import timber.log.Timber

class FirebaseAuthActivity : AppCompatActivity() {

    private val localBroadcastManager by lazy { LocalBroadcastManager.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            ::handleResult
        ).launch(
            GoogleSignIn.getClient(this, gso).signInIntent
        )
    }

    private fun handleResult(it: ActivityResult?) {
        try {
            val signedInAccountFromIntent = GoogleSignIn.getSignedInAccountFromIntent(it?.data)
            val account = signedInAccountFromIntent.getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)

            Firebase.auth
                .signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        localBroadcastManager.sendBroadcast(Intent(AUTH_SUCCESS_BROADCAST))
                        //todo add run works
                    }
                    finish()
                }
        } catch (e: Exception) {
            Timber.d(e)
            finish()
        }
    }
}