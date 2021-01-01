package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.dashboard

import android.app.Activity
import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil.setContentView
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.R
import kotlinx.android.synthetic.main.count_activity.*

class CountEventActivity : AppCompatActivity()  {
    private var num: Int = 100
    private external fun minNumber(num: Int): Int

    private val CAMERA_REQUEST_CODE = 100
    private val STORAGE_REQUEST_CODE = 101

    private val IMAGE_PICK_CAMERA_CODE = 102
    private val IMAGE_PICK_GALLERY_CODE = 103

    private lateinit var cameraPermission: Array<String>
    private lateinit var storagePermission: Array<String>

    private var imagerUri: Uri? = null


    companion object {
        init {
            System.loadLibrary("native-lib")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.count_activity)

        btnMin.setOnClickListener {
            num = minNumber(num)
            txtNum.text = num.toString()
        }

        cameraPermission = arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA)
        storagePermission = arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

        imageView.setOnClickListener{
            imagePickDialog()
        }




    }

    private fun denyPickDialog(){
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Reminder")
        builder.setMessage("We need your camera and storage permission to add your photo")
        builder.setPositiveButton("Ok", { dialogInterface: DialogInterface, i: Int -> imagePickDialog()})
        builder.create().show()
    }

    private fun imagePickDialog(){
        val options = arrayOf("Camera", "Gallery")
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Pick Image From")
        builder.setItems(options){ dialog, which ->
            if (which == 0){
                if (!checkCameraPermission()){
                    requestCameraPermission()
                }
                else{
                    pickFromCamera()
                }
            }
            else if (which == 1){
                if (!checkCameraPermission()){
                    requestStoragePermission()
                }
                else{
                    pickFromStorage()
                }
            }
        }
        builder.create().show()
    }

    private fun checkStoragePermission(): Boolean{
        return (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED)
    }

    private fun requestStoragePermission(){
        ActivityCompat.requestPermissions(this, storagePermission, STORAGE_REQUEST_CODE)
    }

    private fun checkCameraPermission(): Boolean{
        val result = (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED)
        val result1 = (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED)

        return result && result1
    }

    private fun requestCameraPermission(){
        ActivityCompat.requestPermissions(this, cameraPermission, CAMERA_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode){
            CAMERA_REQUEST_CODE ->{
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    val cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED
                    val storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED

                    if (cameraAccepted &&storageAccepted){
                        pickFromCamera()
                    }
                    else{
                        Toast.makeText(this, "Camera Permission Required", Toast.LENGTH_LONG).show()
                    }
                }
                else{
                    denyPickDialog()
                }

            }

            STORAGE_REQUEST_CODE ->{
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                    val storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED

                    if (storageAccepted){
                        pickFromStorage()
                    }
                    else{
                        Toast.makeText(this, "Storage Permission Required", Toast.LENGTH_LONG).show()
                    }
                }
                else{
                    denyPickDialog()
                }
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (resultCode == Activity.RESULT_OK){
            if (requestCode == IMAGE_PICK_GALLERY_CODE){
                CropImage.activity(data!!.data)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1,1)
                    .start(this)
            }
            else if (requestCode == IMAGE_PICK_CAMERA_CODE){
                CropImage.activity(imagerUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1,1)
                    .start(this)
            }
            else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
                val result = CropImage.getActivityResult(data)
                if (resultCode == Activity.RESULT_OK){
                    val resultUri = result.uri
                    imagerUri = resultUri
                    imageView!!.setImageURI(resultUri)
                }
                else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    val error = result.error
                    Toast.makeText(this, ""+error, Toast.LENGTH_LONG).show()
                }

            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun pickFromStorage(){
        val galleryIntent = Intent(Intent.ACTION_PICK)
        galleryIntent.type = "image/*"
        startActivityForResult(galleryIntent, IMAGE_PICK_GALLERY_CODE)
    }

    private fun pickFromCamera(){
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "Image title")
        values.put(MediaStore.Images.Media.DESCRIPTION, "Image descroption")

        imagerUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imagerUri)
        startActivityForResult(cameraIntent, IMAGE_PICK_CAMERA_CODE)
    }
}