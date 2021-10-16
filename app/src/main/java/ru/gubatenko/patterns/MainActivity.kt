package ru.gubatenko.patterns

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.navigation.NavigationMain

class MainActivity : AppCompatActivity(R.layout.activity_main), NavigationMain {

    override fun startAuthorizationFlow() = findNavController(R.id.fragment_container_view_id)
        .navigate(R.id.auth_graph)

    override fun oferAuthorizationFlow() = findNavController(R.id.fragment_container_view_id)
        .navigate(R.id.ofer_auth_graph)

    override fun restartApp() {
        finish()
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun frameGraphId() = R.id.frame_graph_id

}
