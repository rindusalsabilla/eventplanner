package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.profile

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.profile.ProfileEntity
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class ProfileViewModel (application: Application) : AndroidViewModel(application) {

    val profile = MutableLiveData<ProfileEntity>()

    private var profileRepository: ProfileRepository = ProfileRepository(application)

    @RequiresApi(Build.VERSION_CODES.O)
    fun changeToBitmap(stringImage: String): Bitmap {
        val decodeByte = Base64.getDecoder().decode(stringImage)
        return BitmapFactory.decodeByteArray(decodeByte, 0, decodeByte.size)
    }

    fun profile() {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                var profileEntity: ProfileEntity
                withContext(Dispatchers.IO) {
                    profileEntity = profileRepository.getProfile()!!

                }
                profile.value = profileEntity

            }
        }
    }
}