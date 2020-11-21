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
import java.util.*

class ChangeProfileViewModel (application: Application) : AndroidViewModel(application) {
    private lateinit var profile: ProfileEntity
    private val profileRepository = ProfileRepository(application)
    val loaded = MutableLiveData<Boolean>()

    @RequiresApi(Build.VERSION_CODES.O)
    private fun convertBitmap(bitmap: Bitmap): String {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos)
        return Base64.getEncoder().encodeToString(baos.toByteArray())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun insertProfile(name: String, phone: String, image: Bitmap){
        loaded.value = false
        GlobalScope.launch(Dispatchers.Main){

            withContext(Dispatchers.IO) {
                val imageString = convertBitmap(image)
                profile = ProfileEntity(name = name, number_phone = phone, image = imageString)
                profileRepository.insertProfile(profile)

            }
            loaded.value = true
        }

    }
}