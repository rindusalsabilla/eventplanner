package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.dashboard

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.R
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.utils.AppConstants

class QuoteActivity: AppCompatActivity() {
    private var quoteReceiver: BroadcastReceiver? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quote_activity)
        startMyService()
        quoteReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                if (intent.action.toString() == AppConstants.GET_QUOTE) {
                    val author = intent.getStringExtra("author")
                    val content = intent.getStringExtra("content")
                    val tv1: TextView = findViewById(R.id.quote_content)
                    val tv2: TextView = findViewById(R.id.quote_author)
                    tv1.text = content
                    tv2.text = author
                }
            }
        }
        LocalBroadcastManager
            .getInstance(this)
            .registerReceiver(
                quoteReceiver as BroadcastReceiver,
                IntentFilter(AppConstants.GET_QUOTE)
            )
    }

    override fun onStop() {
        quoteReceiver?.let {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(it)
        }
        super.onStop()
    }
    override fun onDestroy() {
        quoteReceiver?.let {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(it)
        }
        super.onDestroy()
    }
    private fun startMyService(){
        Intent(this, MyService::class.java).also { intent ->
            startService(intent)
        }
    }


}