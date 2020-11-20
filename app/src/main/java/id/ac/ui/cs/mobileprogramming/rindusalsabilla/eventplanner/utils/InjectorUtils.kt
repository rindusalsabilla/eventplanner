package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.utils

import android.content.Context
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.event.EventDb
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.repository.EventRepository
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.event.EventViewModelFactory

object InjectorUtils {
    fun provideQuotesViewModelFactory(context: Context): EventViewModelFactory {

        val eventRepository = EventRepository.getInstance(EventDb.getInstance(context).eventDao())
        return EventViewModelFactory(eventRepository)
    }
}