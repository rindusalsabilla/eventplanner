package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.profile

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [ProfileEntity::class],
    version = 2,
    exportSchema = false
)
abstract class ProfileDb : RoomDatabase() {

    abstract fun profileDao(): ProfileDao

    companion object {

        @Volatile
        private var instance: ProfileDb? = null

        fun getInstance(context: Context) : ProfileDb? {
            if(instance == null) {
                synchronized(ProfileDb::class.java) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            ProfileDb::class.java, "profile_db"
                        )
                            .build()
                    }
                }
            }
            return instance
        }
    }
}