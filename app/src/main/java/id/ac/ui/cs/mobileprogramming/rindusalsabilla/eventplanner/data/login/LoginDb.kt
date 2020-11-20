package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.login

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [LoginEntity::class],
    version = 1,
    exportSchema = false
)
abstract class LoginDb : RoomDatabase() {
    abstract fun loginDao(): LoginDao?

    companion object {
        private var INSTANCE: LoginDb? = null
        fun getDatabase(context: Context): LoginDb? {
            if (INSTANCE == null) {
                synchronized(LoginDb::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            LoginDb::class.java, "login_database"
                        )
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}