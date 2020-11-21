package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.profile

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.MainActivity
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.R

class ChangeProfileActivity : AppCompatActivity() {

    private lateinit var uploadImage: TextView
    private lateinit var uriImage: Uri
    private lateinit var viewImage: ImageView
    private lateinit var bitmap: Bitmap
    private lateinit var changeProfileViewModel: ChangeProfileViewModel
    private lateinit var nextButton: Button
    private lateinit var editTextName: EditText
    private lateinit var editTextPhone: EditText
    val GALLERY_REQUEST_CODE = 1

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        changeProfileViewModel = ViewModelProviders.of(this).get(ChangeProfileViewModel::class.java)

        uploadImage = findViewById(R.id.upload_image)
        editTextName = findViewById(R.id.edit_name)
        editTextPhone = findViewById(R.id.edit_phone)
        nextButton = findViewById(R.id.next_btn)

        uploadImage.setOnClickListener {
            uploadImage()
        }

        nextButton.setOnClickListener {
            editProfile()
            changeProfileViewModel.loaded.observe(this, Observer {
                if (it == true) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            })
        }
    }



    @RequiresApi(Build.VERSION_CODES.O)
    fun editProfile() {
        val name: String = editTextName.text.toString()
        val number_phone: String = editTextPhone.text.toString()
        changeProfileViewModel.insertProfile(name, number_phone, bitmap)
    }

    fun uploadImage() {
        var intent = Intent(Intent.ACTION_PICK)
        intent.setType("image/*")
        val mimeTypes = arrayOf("image/jpeg", "image/png")
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        startActivityForResult(intent, GALLERY_REQUEST_CODE)
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