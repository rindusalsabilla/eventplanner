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

class LoginRepository(private val loginDao: LoginDao) {

//    private val allLoginEntity: LiveData<List<LoginEntity>> = loginDao.getAllUser()

    fun createUser(loginEntity: LoginEntity) {
        loginDao.createUser(loginEntity)
    }

    fun getUserLoginByUsernameAndPassword(username: String, password: String) : LiveData<LoginEntity>{
        return loginDao.getUserByUsernameAndPassword(username, password)
    }

    companion object {
        @Volatile private var instance: LoginRepository? = null

        fun getInstance(loginDao: LoginDao) =
            instance ?: synchronized(this) {
                instance ?: LoginRepository(loginDao).also { instance = it }
            }
    }
}