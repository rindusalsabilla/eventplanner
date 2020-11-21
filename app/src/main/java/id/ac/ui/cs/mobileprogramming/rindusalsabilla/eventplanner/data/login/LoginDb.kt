package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.login

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [LoginEntity::class],
    version = 2,
    exportSchema = false
)
abstract class LoginDb : RoomDatabase() {

    abstract fun loginDao(): LoginDao

    companion object {

        @Volatile
        private var instance: LoginDb? = null

        fun getInstance(context: Context) = instance
        ?: synchronized(this) {
            instance
                ?: Room.databaseBuilder(
                    context.getApplicationContext(),
                   LoginDb::class.java,
                    "login_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    
        }
    }
}