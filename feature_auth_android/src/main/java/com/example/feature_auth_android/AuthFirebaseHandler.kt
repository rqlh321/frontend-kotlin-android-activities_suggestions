package com.example.feature_auth_android

import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.navigation.AUTH_SUCCESS_BROADCAST
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import timber.log.Timber

class AuthFirebaseHandler(private val activity: FragmentActivity) : AuthHandler {

    private val googleSignInForResult = activity.registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        try {
            val signedInAccountFromIntent = GoogleSignIn.getSignedInAccountFromIntent(it?.data)
            val account = signedInAccountFromIntent.getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)

            Firebase.auth
                .signInWithCredential(credential)
                .addOnCompleteListener(activity) { task ->
                    if (task.isSuccessful) {
                        localBroadcastManager.sendBroadcast(Intent(AUTH_SUCCESS_BROADCAST))
                    }
                    activity.finish()
                }
        } catch (e: Exception) {
            Timber.d(e)
            activity.finish()
        }
    }

    private val localBroadcastManager by lazy { LocalBroadcastManager.getInstance(activity) }

    private val googleSignInIntent: Intent by lazy {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(activity.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        GoogleSignIn.getClient(activity, gso).signInIntent
    }

    override fun authorize() {
        googleSignInForResult.launch(googleSignInIntent)
    }

}