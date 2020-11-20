package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.login.Login
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.repository.LoginRepository

class CreateAccountViewModel(application: Application) : AndroidViewModel(application) {
    private val userLoginRepository: LoginRepository
//    private val userProfileRepository: ProfileRepository
    fun createUser(username: String?, password: String?) {
        val entity = Login()
        entity.setUsername(username)
        entity.setPassword(password)
        userLoginRepository.createUser(entity)
    }

//    fun createUserProfile(name: String?, numberPhone: String?) {
//        val entity = Login()
//        entity.setName(name)
//        entity.setNumberPhone(numberPhone)
//        userProfileRepository.createUserProfile(entity)
//    }

    init {
        userLoginRepository = LoginRepository(application)
//        userProfileRepository = UserProfileRepository(application)
    }
}
