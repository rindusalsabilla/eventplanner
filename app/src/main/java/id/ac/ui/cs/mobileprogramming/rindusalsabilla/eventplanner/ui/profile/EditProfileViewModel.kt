package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.profile

import android.app.Application
import android.graphics.Bitmap
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
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.net.URL
import java.util.*

class EditProfileViewModel (application: Application) : AndroidViewModel(application) {
    private lateinit var profile: ProfileEntity
    private val profileRepository = ProfileRepository(application)
    val loaded = MutableLiveData<Boolean>()
    val prof = MutableLiveData<List<ProfileEntity>>()

    @RequiresApi(Build.VERSION_CODES.O)
    fun insertProfile(name: String, phone: String, image: Bitmap){
        loaded.value = false
        GlobalScope.launch(Dispatchers.Main){

//            var profiles: List<ProfileEntity>?
            withContext(Dispatchers.IO) {
                val imageString = convertBitmap(image)
                profile = ProfileEntity(name = name, number_phone = phone, image = imageString)
                profileRepository.insertProfile(profile)

//                prof.value = profile
//                loaded.value = true
//                profiles = profileRepository.getAllProfile()

            }
//            prof.value = profileRepository.getAllProfile()
            loaded.value = true
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun convertBitmap(bitmap: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos)
        return Base64.getEncoder().encodeToString(baos.toByteArray())
    }
}