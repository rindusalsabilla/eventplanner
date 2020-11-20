package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.login.LoginEntity
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.repository.LoginRepository

class LoginViewModel(
    private val loginRepository: LoginRepository
) : ViewModel() {

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    fun getUserLoginByUsernameAndPassword(username: String, password: String) : LiveData<LoginEntity>{
        return loginRepository.getUserLoginByUsernameAndPassword(username, password)
    }
}