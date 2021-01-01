package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.dashboard

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
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

    private fun imagePickDialog(){
        val options = arrayOf("Camera", "Gallery")
        
    }
}