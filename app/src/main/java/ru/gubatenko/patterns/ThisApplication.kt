package ru.gubatenko.patterns

import android.app.Activity
import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.work.*
import com.example.feature_auth_android.offerAuthAndroidModuleDI
import com.example.navigation.AUTH_REQUEST_BROADCAST
import com.example.navigation.AUTH_SUCCESS_BROADCAST
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.gubatenko.data_impl.daoModuleDI
import ru.gubatenko.data_impl.dtoMapperImplModuleDI
import ru.gubatenko.data_impl.serviceImplModuleDI
import ru.gubatenko.data_impl.storedMapperImplModuleDI
import ru.gubatenko.domain_impl.repoImplModuleDI
import ru.gubatenko.domain_impl.usaCaseImplModuleDI
import ru.gubatenko.feature_main_android.mainFeatureAndroidModuleDI
import ru.gubatenko.patterns.firebase.service.serviceFirebaseImplModuleDI

class ThisApplication : Application(), Application.ActivityLifecycleCallbacks {

    private var currentActivity: AppCompatActivity? = null

    private val googleSignInIntent: Intent by lazy {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        GoogleSignIn.getClient(this, gso).signInIntent
    }

    private lateinit var googleSignInForResult: ActivityResultLauncher<Intent>

    private val authRequestBroadcastListener = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) =
            googleSignInForResult.launch(googleSignInIntent)
    }

    private val localBroadcastManager by lazy { LocalBroadcastManager.getInstance(this) }

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(this)
        localBroadcastManager.registerReceiver(
            authRequestBroadcastListener,
            IntentFilter(AUTH_REQUEST_BROADCAST)
        )
        startKoin {
            androidLogger()
            androidContext(this@ThisApplication)
            modules(
                storedMapperImplModuleDI,
                dtoMapperImplModuleDI,
                daoModuleDI,
                serviceFirebaseImplModuleDI,
                serviceImplModuleDI,
                repoImplModuleDI,
                usaCaseImplModuleDI,
                offerAuthAndroidModuleDI,
                mainFeatureAndroidModuleDI,
            )
        }
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        Firebase.auth.addAuthStateListener {
            if (it.currentUser != null) {
                authorizedUserDetected()
            }
        }
        (activity as? AppCompatActivity)?.let { appCompatActivity ->
            currentActivity = appCompatActivity
            googleSignInForResult = appCompatActivity.registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) {
                try {
                    val signedInAccountFromIntent =
                        GoogleSignIn.getSignedInAccountFromIntent(it?.data)
                    val account = signedInAccountFromIntent.getResult(ApiException::class.java)
                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                    Firebase.auth
                        .signInWithCredential(credential)
                        .addOnCompleteListener(appCompatActivity) { task ->
                            if (task.isSuccessful) {
                                localBroadcastManager.sendBroadcast(Intent(AUTH_SUCCESS_BROADCAST))
                            }
                        }
                } catch (e: Exception) {
                    Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityStarted(activity: Activity) = Unit

    override fun onActivityResumed(activity: Activity) = Unit

    override fun onActivityPaused(activity: Activity) = Unit

    override fun onActivityStopped(activity: Activity) = Unit

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) = Unit

    override fun onActivityDestroyed(activity: Activity) {
        currentActivity = null
    }

    private fun authorizedUserDetected() {
        runUploadWorker()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }
}