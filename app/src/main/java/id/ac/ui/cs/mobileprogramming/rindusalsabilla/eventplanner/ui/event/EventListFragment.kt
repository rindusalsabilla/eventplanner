package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.event

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.R
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.event.EventEntity
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.databinding.FragmentEventListBinding
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.utils.InjectorUtils

class EventListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    val ADD_EVENT_REQUEST = 1
    private lateinit var mView: View
    private lateinit var eventViewModel: EventViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentEventListBinding = FragmentEventListBinding.inflate(inflater, container, false)
        this.mView = binding.root
        this.recyclerView = mView.findViewById(R.id.recycler_view)

        val buttonAddEvent = mView.findViewById(R.id.button_add) as ImageButton
        buttonAddEvent.setOnClickListener {
            val intent: Intent = Intent(mView.context, AddEventActivity::class.java)
            startActivityForResult(intent, ADD_EVENT_REQUEST)
        }

        showRecyclerList()
        return mView
    }

    fun goToSelectedEvent(data: EventEntity, position: Int) {
        val fragment = EventDetailsFragment(data, position)
        val fragmentTransaction: FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.nav_host_fragment, fragment, fragment.toString())
        fragmentTransaction?.addToBackStack(fragment.toString())
        fragmentTransaction?.commit()
    }

    private fun showRecyclerList() {
        recyclerView.setLayoutManager(LinearLayoutManager(mView.context))
        recyclerView.setHasFixedSize(true)

        val adapter = EventAdapter()
        recyclerView.setAdapter(adapter)

        val factory = InjectorUtils.provideEventViewModelFactory(mView.context)

        eventViewModel = ViewModelProviders.of(this, factory)
            .get(EventViewModel::class.java)
        eventViewModel.getAllEvent().observe(viewLifecycleOwner, Observer { contacts ->
            adapter.setContacts(contacts)
        })

        adapter.setOnItemClickCallback(object: EventAdapter.OnItemClickCallback {
            override fun onItemClicked(data: EventEntity, position: Int) {
                goToSelectedEvent(data, position)
            }
        })
    }

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_EVENT_REQUEST && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                val event: String = data.getStringExtra(AddEventActivity.EXTRA_EVENT)?: ""
                val desc: String = data.getStringExtra(AddEventActivity.EXTRA_DESC)?: ""
                val date: String = data.getStringExtra(AddEventActivity.EXTRA_DATE)?: ""

                val events =
                    EventEntity(
                        event,
                        desc,
                        date
                    )
                eventViewModel.insert(events)

                val toast: Toast = Toast.makeText(
                    mView.context, R.string.event_saved,
                    Toast.LENGTH_SHORT
                )
                toast.show()
            }
        }
    }
}