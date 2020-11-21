package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.utils

import java.sql.Timestamp
import java.util.*

object Utilities {
    val timeNow: String
        get() {
            val date = Date()
            val time = date.time
            val timestamp = Timestamp(time)
            return timestamp.toString()
        }
}