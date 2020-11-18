package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.event

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.EventEntity
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.databinding.FragmentEventDetailsBinding

class EventDetailsFragment (val data: EventEntity, val position: Int): Fragment() {
    private lateinit var mView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentEventDetailsBinding =
            FragmentEventDetailsBinding.inflate(inflater, container, false)
        this.mView = binding.getRoot()

        binding.viewEvent.setText(data.event)
        binding.viewDesc.setText(data.desc)
        return mView
    }
}