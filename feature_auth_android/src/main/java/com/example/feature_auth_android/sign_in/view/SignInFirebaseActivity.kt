package com.example.feature_auth_android.sign_in.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.feature_auth.sign_in.SignInStore
import com.example.feature_auth_android.R
import com.example.feature_auth_android.sign_in.SignInViewModel
import com.example.feature_auth_android.sign_in.signInFeatureAndroidModuleDI
import com.example.navigation.AUTH_SUCCESS_BROADCAST
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import timber.log.Timber

class SignInFirebaseActivity : AppCompatActivity() {

    private val viewModel: SignInViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Firebase_SignIn);
        super.onCreate(savedInstanceState)
        loadKoinModules(signInFeatureAndroidModuleDI)

        viewModel.event.observe(this, ::handleEvent)

        if (savedInstanceState == null) {
            registerForActivityResult(
                ActivityResultContracts.StartActivityForResult(),
                ::handleResult
            ).launch(
                GoogleSignIn.getClient(
                    this,
                    GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build()
                ).signInIntent
            )
        }
    }

    override fun onDestroy() {
        unloadKoinModules(signInFeatureAndroidModuleDI)
        super.onDestroy()
    }

    private fun handleEvent(event: SignInStore.Event) {
        when (event) {
            is SignInStore.Event.SignInSuccess -> {
                LocalBroadcastManager.getInstance(this)
                    .sendBroadcast(Intent(AUTH_SUCCESS_BROADCAST))
                finish()
            }
            is SignInStore.Event.SignInFail -> finish()
        }
    }

    private fun handleResult(it: ActivityResult?) {
        try {
            val signedInAccountFromIntent = GoogleSignIn.getSignedInAccountFromIntent(it?.data)
            val account = signedInAccountFromIntent.getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            viewModel.signIn(credential)
        } catch (e: Exception) {
            Timber.d(e)
            finish()
        }
    }
}