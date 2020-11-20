package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.R
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.auth.LoginActivity

class SplashScreenActivity : Activity() {
    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        handler = Handler()
        handler.postDelayed({
            val intent = Intent(this@SplashScreenActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}