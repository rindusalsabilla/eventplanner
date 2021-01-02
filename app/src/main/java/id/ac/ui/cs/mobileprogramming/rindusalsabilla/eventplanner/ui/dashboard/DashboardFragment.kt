package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.dashboard

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.MainActivity
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.R
//import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    @SuppressLint("WrongViewCast")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)


        val buttonCount: Button = root.findViewById(R.id.count_button)
        buttonCount.setOnClickListener {
            val intent: Intent = Intent(root.context, CountEventActivity::class.java)
            startActivity(intent)

        }

        val buttonQuote: Button = root.findViewById(R.id.quote_button)
        buttonQuote.setOnClickListener {
            val intent: Intent = Intent(root.context, QuoteActivity::class.java)
            startActivity(intent)

        }
        val sw = root.findViewById<TextView>(R.id.stopwatch)
        val str_btn = root.findViewById<Button>(R.id.start_stopwatch)
        val ps_btn = root.findViewById<Button>(R.id.pause_stopwatch)
        val rst_btn = root.findViewById<Button>(R.id.reset_stopwatch)

        if ((activity as MainActivity).swIsRunning) {
            str_btn.isEnabled = false
            rst_btn.isEnabled = false
        }
        (activity as MainActivity).swTxt = sw

        str_btn.setOnClickListener {
            (activity as MainActivity).startStopwatch()
            str_btn.isEnabled = false
            rst_btn.isEnabled = false
        }
        ps_btn.setOnClickListener {
            (activity as MainActivity).pauseStopwatch()
            str_btn.isEnabled = true
            rst_btn.isEnabled = true
        }
        rst_btn.setOnClickListener {
            (activity as MainActivity).resetStopwatch()
            sw.text = "00:00:00"
        }

        return root
    }


}