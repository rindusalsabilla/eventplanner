package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.repository

import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.event.EventDao
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.event.EventEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext

class EventRepository private constructor(
    private val eventDao: EventDao
) {
    private val allEventEntity: LiveData<List<EventEntity>> = eventDao.getAll()

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    var thread = newSingleThreadContext("eventRepository") as CoroutineDispatcher

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    fun insertEvent(eventEntity: EventEntity) = GlobalScope.launch(thread) {
        eventDao.insert(eventEntity)
    }

    fun getAllEvent(): LiveData<List<EventEntity>> {
        return allEventEntity
    }

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    fun deleteEvent(eventEntity: EventEntity) = GlobalScope.launch(thread) {
        eventDao.delete(eventEntity)
    }

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    fun updateEvent(eventEntity: EventEntity) = GlobalScope.launch(thread) {
        eventDao.update(eventEntity)
    }

    companion object {
        @Volatile private var instance: EventRepository? = null

        fun getInstance(eventDao: EventDao) =
            instance ?: synchronized(this) {
                instance ?: EventRepository(eventDao).also { instance = it }
            }
    }
}