package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.event

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.event.EventEntity
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.repository.EventRepository

class EventViewModel (
    private val eventRepository: EventRepository
): ViewModel() {
    private val allEventEntity: LiveData<List<EventEntity>> = eventRepository.getAllEvent()

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    fun insert(eventEntity: EventEntity) {
        eventRepository.insertEvent(eventEntity)
    }

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    fun update(eventEntity: EventEntity) {
        eventRepository.updateEvent(eventEntity)
    }

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    fun delete(eventEntity: EventEntity) {
        eventRepository.deleteEvent(eventEntity)
    }

    fun getAllEvent(): LiveData<List<EventEntity>> {
        return allEventEntity
    }
}