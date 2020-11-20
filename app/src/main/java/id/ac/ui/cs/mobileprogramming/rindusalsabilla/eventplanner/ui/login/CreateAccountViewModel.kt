package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.login.Login
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.login.LoginEntity
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.repository.LoginRepository

class CreateAccountViewModel(private val loginRepository: LoginRepository) : ViewModel() {

//    private val userProfileRepository: ProfileRepository
    fun createUser(loginEntity: LoginEntity) {
        return loginRepository.createUser(loginEntity)
    }

//    fun createUserProfile(name: String?, numberPhone: String?) {
//        val entity = Login()
//        entity.setName(name)
//        entity.setNumberPhone(numberPhone)
//        userProfileRepository.createUserProfile(entity)
//    }

}
