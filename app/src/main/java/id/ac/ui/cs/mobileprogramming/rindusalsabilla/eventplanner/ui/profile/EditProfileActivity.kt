package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.profile

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.lifecycle.Observer
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.MainActivity
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.R
import java.text.SimpleDateFormat
import java.util.*

class EditProfileActivity : AppCompatActivity() {
    val GALLERY_REQUEST_CODE = 1

//    private lateinit var spManager: SharedPreferenceManager
    private lateinit var uploadImage: TextView
    private lateinit var viewImage: ImageView
    private lateinit var uriImage: Uri
    private lateinit var bitmap: Bitmap

    private lateinit var viewModel: EditProfileViewModel

    private lateinit var nextBtn: Button
    private lateinit var editName: EditText
    private lateinit var editPhone: EditText

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

//        spManager = SharedPreferenceManager(this)
//
//        if (!spManager.isFirstTime()) {
//            val intent = Intent(this, WelcomeActivity::class.java)
//            startActivity(intent)
//            finish()
//        }

        viewModel = ViewModelProviders.of(this).get(EditProfileViewModel::class.java)

        uploadImage = findViewById(R.id.upload_image)
        editName = findViewById(R.id.edit_name)
        editPhone = findViewById(R.id.edit_phone)
        nextBtn = findViewById(R.id.next_btn)

        uploadImage.setOnClickListener {
            uploadImage()
        }

        nextBtn.setOnClickListener {
            editProfile()
            viewModel.loaded.observe(this, Observer {
                if (it == true) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            })
        }
    }

    fun uploadImage() {
        var intent = Intent(Intent.ACTION_PICK)
        intent.setType("image/*")
        val mimeTypes = arrayOf("image/jpeg", "image/png")
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun editProfile() {
        val name: String = editName.text.toString()
        val number_phone: String = editPhone.text.toString()

//        spManager = SharedPreferenceManager(this)
//        spManager.setFirstTime(false)
        viewModel.insertProfile(name, number_phone, bitmap)
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            try {
                uriImage = data?.data!!
                val source: ImageDecoder.Source = ImageDecoder.createSource(this.contentResolver!!, uriImage)
                bitmap = ImageDecoder.decodeBitmap(source)
                viewImage.setImageBitmap(bitmap)
                viewImage.contentDescription = "Profile picture"
            }
            catch (e: Exception) {

            }
        }
    }
}