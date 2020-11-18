package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [EventEntity::class], version = 2, exportSchema = false)
abstract class EventDb: RoomDatabase() {

    abstract fun eventDao(): EventDao

    companion object {

        @Volatile
        private var instance: EventDb? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: Room.databaseBuilder(
                context.getApplicationContext(),
                EventDb::class.java,
                "contacts_db"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}