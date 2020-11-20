package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.login.LoginEntity
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.repository.LoginRepository

class LoginViewModel(application: Application?) : AndroidViewModel(application!!) {
    private val userLoginRepository: LoginRepository
    fun getUserLoginByUsernameAndPassword(
        username: String?,
        password: String?
    ): LiveData<LoginEntity> {
        return userLoginRepository.getUserLoginByUsernameAndPassword(username, password)
    }

    init {
        userLoginRepository = LoginRepository(application)
    }
}