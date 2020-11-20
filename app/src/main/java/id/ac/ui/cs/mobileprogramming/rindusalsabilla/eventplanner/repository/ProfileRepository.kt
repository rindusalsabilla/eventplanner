package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.login.LoginDao
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.login.LoginEntity
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.profile.ProfileDao
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.profile.ProfileDb
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.profile.ProfileEntity
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.utils.Utilities

class ProfileRepository private constructor(
    private var profileDao: ProfileDao ){


    fun createUserProfile(profileEntity: ProfileEntity) {
        profileDao.createUserProfile(profileEntity)
    }

    fun updateUserProfile(profileEntity: ProfileEntity) {
        profileDao.createUserProfile(profileEntity)
    }

    fun getUserProfileById(id: Int) : LiveData<ProfileEntity>{
        return profileDao.getUserProfileById(id)
    }

    companion object {
        @Volatile private var instance: ProfileRepository? = null

        fun getInstance(profileDao: ProfileDao) =
            instance ?: synchronized(this) {
                instance ?: ProfileRepository(profileDao).also { instance = it }
            }
    }
}