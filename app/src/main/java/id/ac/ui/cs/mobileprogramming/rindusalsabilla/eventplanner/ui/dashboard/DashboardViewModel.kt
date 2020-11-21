package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Event Planner"
    }
    val text: LiveData<String> = _text
}