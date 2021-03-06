package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.repository

import android.app.Application
import android.content.ContentValues
import android.util.Log
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.profile.ProfileDao
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.profile.ProfileDb
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.profile.ProfileEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class ProfileRepository (application: Application) : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var profileDao: ProfileDao?

    init {
        val db = ProfileDb.getInstance(application)
        profileDao = db?.profileDao()
    }

    fun insertProfile(profile: ProfileEntity) {
        profileDao?.insert(profile)
        Log.d(ContentValues.TAG,profileDao?.getProfile().toString())
    }

    fun getProfile(): ProfileEntity? {
        return profileDao?.getProfile()
    }

    fun getAllProfile(): List<ProfileEntity> {
        return profileDao!!.getAllProfile()
    }
}