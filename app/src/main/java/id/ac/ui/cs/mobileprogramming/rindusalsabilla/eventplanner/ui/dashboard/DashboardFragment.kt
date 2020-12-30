package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.dashboard

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.R
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.event.AddEventActivity
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.profile.ChangeProfileActivity
import kotlinx.android.synthetic.main.fragment_dashboard.view.*
//import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.databinding.FragmentDashboardBinding
import kotlinx.android.synthetic.main.fragment_profile.view.*

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    val ADD_EVENT_REQUEST = 1
    private lateinit var myView: View

    @SuppressLint("WrongViewCast")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        val buttonCount: Button = root.findViewById(R.id.count_button)
        buttonCount.setOnClickListener {
            val intent: Intent = Intent(root.context, CountEventActivity::class.java)
            startActivity(intent)

        }
        return root
    }

}