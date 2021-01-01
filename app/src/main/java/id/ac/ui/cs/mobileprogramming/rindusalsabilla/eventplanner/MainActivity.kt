package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.dashboard.MyService

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_event, R.id.navigation_dashboard, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

    override fun onConfigurationChanged(newConfig: Configuration) {

        val orientation = resources.configuration.orientation

        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            val intent = Intent(this, MainActivityLandscape::class.java)
            startActivity(intent)
        }
        super.onConfigurationChanged(newConfig)
    }




}