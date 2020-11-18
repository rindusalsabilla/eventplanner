package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.event

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.repository.EventRepository

class EventViewModelFactory (private val eventRepository: EventRepository)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T: ViewModel?> create(modelClass: Class<T>): T {
        return EventViewModel(eventRepository) as T
    }
}