package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.dashboard

import android.app.Service
import android.content.Intent
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import android.os.Handler
import android.os.IBinder
import android.util.Log
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.R
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.utils.AppConstants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MyService: Service() {
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    private var mHandler: Handler? = null

    private val runservice: Runnable = object : Runnable {
        override fun run() {
            pushLoadAPI()
            mHandler?.postDelayed(this, DEFAULT_SYNC_INTERVAL)
        }
    }

    companion object{
        const val DEFAULT_SYNC_INTERVAL = 10 * 1000.toLong()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mHandler = Handler()
        mHandler!!.post(runservice)
        return START_STICKY
    }

    @Synchronized
    private fun pushLoadAPI() {
        val valRetrofit = Retrofit.Builder()
            .baseUrl(APIConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val request = valRetrofit.create(APIConfig::class.java)
        val call = request.quote
        call?.enqueue(object:Callback<Quote?>{
            override fun onFailure(call: Call<Quote?>, t: Throwable) {
                Toast.makeText(
                    applicationContext,
                    R.string.fail_con,
                    Toast.LENGTH_SHORT).show()
            }
            override fun onResponse(call: Call<Quote?>, response: Response<Quote?>) {
                val quote: Quote? = response.body() as Quote
                val author = quote?.author
                val content = quote?.content
                val intent = Intent(AppConstants.GET_QUOTE)
                intent.putExtra("author", author)
                intent.putExtra("content", content)
                val broadcastManager = LocalBroadcastManager.getInstance(applicationContext)
                broadcastManager.sendBroadcast(intent)
            }
        })
    }
}