package ru.gubatenko.patterns

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import ru.gubatenko.core.AuthHandler

class MainActivity : AppCompatActivity(), AuthHandler {

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
            val account = GoogleSignIn.getSignedInAccountFromIntent(it?.data)
                .getResult(ApiException::class.java)
            Toast.makeText(this, account.displayName, Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun signIn() {
        googleSignInForResult.launch(googleSignInIntent)
    }

    override fun signOut() {
        TODO("Not yet implemented")
    }
}