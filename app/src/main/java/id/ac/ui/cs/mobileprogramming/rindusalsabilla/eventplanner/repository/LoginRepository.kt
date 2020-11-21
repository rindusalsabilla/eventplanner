package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.repository

import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.login.LoginDao
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.login.LoginEntity

class LoginRepository private constructor(private val loginDao: LoginDao) {

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