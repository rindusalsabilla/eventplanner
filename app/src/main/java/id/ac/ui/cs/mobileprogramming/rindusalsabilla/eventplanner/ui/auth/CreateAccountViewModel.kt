package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.auth

import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.login.LoginEntity
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.profile.ProfileEntity
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.repository.LoginRepository
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.repository.ProfileRepository

class CreateAccountViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private lateinit var profileRepository: ProfileRepository

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    fun createUser(loginEntity: LoginEntity) {
        return loginRepository.createUser(loginEntity)
    }

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    fun createUserProfile(profileEntity: ProfileEntity) {
        return profileRepository.createUserProfile(profileEntity)
    }

}
