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

class MainActivityLandscape : AppCompatActivity() {
    var swIsRunning: Boolean = false
    var swTxt: TextView? = null
    private var msTime: Long = 0
    private var start: Long = 0
    private var buff: Long = 0
    private var updt: Long = 0
    private var scnd: Int = 0
    private var min: Int = 0
    private var milScn: Int = 0
    private lateinit var handle: Handler
    private lateinit var runnable: Runnable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        handle = Handler(Looper.getMainLooper())
        runnable = Runnable {
            msTime = SystemClock.uptimeMillis() - start
            updt = buff + msTime;
            scnd = (updt / 1000).toInt();
            min = scnd / 60;
            scnd %= 60;
            milScn = (updt % 100).toInt();
            handle.postDelayed(runnable, 0);
            swTxt?.text =
                (String.format("%02d", min) + ":" + String.format("%02d", scnd) + ":" + String.format("%02d", milScn));
        }

        val navController = findNavController(R.id.nav_host_fragment)
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

    fun startStopwatch() {
        start = SystemClock.uptimeMillis()
        handle.postDelayed(runnable, 0)
        swIsRunning = true
    }

    fun pauseStopwatch() {
        buff += msTime
        handle.removeCallbacks(runnable)
        swIsRunning = false
    }

    fun resetStopwatch() {
        msTime = 0
        start = 0
        buff = 0
        updt = 0
        scnd = 0
        min = 0
        milScn = 0
    }
}