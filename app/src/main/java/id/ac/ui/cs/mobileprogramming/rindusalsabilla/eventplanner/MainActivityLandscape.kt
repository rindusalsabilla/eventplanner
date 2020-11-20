package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

class MainActivityLandscape : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_event, R.id.navigation_dashboard, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {

        val orientation = resources.configuration.orientation

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        super.onConfigurationChanged(newConfig)
    }

    fun navigateToOtherMenu(givenFragment: Fragment): Boolean {
        val fragmentTransaction: FragmentTransaction = this.supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.nav_host_fragment, givenFragment, givenFragment.toString())
        fragmentTransaction.commit()
        return true
    }


}