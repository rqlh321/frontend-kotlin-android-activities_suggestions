package ru.gubatenko.patterns

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.navigation.NavigationRoot

class RootActivity : AppCompatActivity(), NavigationRoot {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)
    }

    override fun startAuthorizationFlow() {
        findNavController(R.id.nav_host_fragment).navigate(R.id.auth_fragment_id)
    }

}