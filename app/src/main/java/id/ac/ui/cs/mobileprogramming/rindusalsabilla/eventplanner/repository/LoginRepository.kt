package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.event.EventDao
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.event.EventEntity
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.login.Login
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.login.LoginDao
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.login.LoginDb
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.login.LoginEntity
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.utils.Utilities

class LoginRepository(application: Application?) {
    private val loginDao: LoginDao
    private val mUserLogin: LiveData<List<LoginEntity>>

    val allUsers: LiveData<List<Any>>
        get() = mUserLogin

    private val allLoginEntity: LiveData<List<LoginEntity>> = loginDao.getAllUser()

    fun createUser(entity: Login) {
        val userLogin = LoginEntity()
        userLogin.setUsername(entity.getUsername())
        userLogin.setPassword(entity.getPassword())
        userLogin.setCreatedAt(Utilities.getCurrentTimestamp())
        insertUserLoginAsync(loginDao).execute(userLogin)
    }

    fun getUserLoginByUsernameAndPassword(
        username: String?,
        password: String?
    ): LiveData<LoginEntity> {
        return loginDao.getUserByUsernameAndPassword(username, password)
    }

    private class insertUserLoginAsync internal constructor(loginDao: LoginDao) :
        AsyncTask<LoginEntity?, Void?, Long>() {
        private val loginDao: LoginDao
        override fun doInBackground(vararg userLogin: LoginEntity): Long {
            return loginDao.createUser(userLogin[0])
        }

        override fun onPostExecute(aLong: Long) {
            super.onPostExecute(aLong)
        }

        init {
            this.loginDao = loginDao
        }
    }

    init {
        val db: LoginDb = LoginDb.getDatabase(application)
        loginDao = db.loginDao()
        mUserLogin = loginDao.getAllUser()
    }

    companion object {
        @Volatile private var instance: LoginRepository? = null

        fun getInstance(loginDao: LoginDao) =
            instance ?: synchronized(this) {
                instance ?: LoginRepository(loginDao).also { instance = it }
            }
    }
}