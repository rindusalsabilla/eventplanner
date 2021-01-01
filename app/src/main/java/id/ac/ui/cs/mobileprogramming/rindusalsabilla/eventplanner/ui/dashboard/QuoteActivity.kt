package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.R

class QuoteActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quote_activity)
        startMyService()
    }

    private fun startMyService(){
        Intent(this, MyService::class.java).also { intent ->
            startService(intent)
        }
    }
}