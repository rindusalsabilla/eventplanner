package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.profile

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.login.LoginDao
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.login.LoginEntity
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.profile.ProfileEntity
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.repository.ProfileRepository

class ProfileViewModel(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    fun editUserProfile(userProfile: ProfileEntity) {
        userProfile.id
        profileRepository.updateUserProfile(userProfile)
    }
    fun getUserProfileById(id: Int) : LiveData<ProfileEntity> {
        return profileRepository.getUserProfileById(id)
    }

}