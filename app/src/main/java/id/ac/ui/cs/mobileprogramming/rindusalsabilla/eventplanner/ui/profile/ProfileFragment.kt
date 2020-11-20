package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.profile

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.R
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.profile.ProfileEntity
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.auth.LoginActivity
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.auth.LoginViewModel
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.utils.EventPlannerConstant
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.utils.InjectorUtils
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.utils.Utilities
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var profile: ProfileEntity
    private lateinit var mView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        val button: Button = root.findViewById(R.id.btn_change_img)
        button.setOnClickListener{
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                if(context?.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)  == PackageManager.PERMISSION_DENIED){
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE);
                    requestPermissions(permissions, PERMISSION_CODE);
                }
                else{
                    pickImageFromGallery();
                }
            }
            else{
                pickImageFromGallery();
            }
        }

        val factory = InjectorUtils.provideProfileViewModelFactory(mView.context)
        profileViewModel = ViewModelProviders.of(this, factory).get(ProfileViewModel::class.java)

        val profileField = root.findViewById<EditText>(R.id.fieldNameProfile)
        val numberField = root.findViewById<EditText>(R.id.fieldPhoneProfile)
        val logout = root.findViewById<TextView>(R.id.logoutButton)
        val saveChanges =
            root.findViewById<Button>(R.id.saveProfile)

        profileViewModel.getUserProfileById(id)
            .observe(viewLifecycleOwner, Observer<ProfileEntity> { userProfile ->
                profile = userProfile
                profileField.setText(profile.name)
                numberField.setText(profile.number_phone)
            })

        logout.setOnClickListener { logout() }
        saveChanges.setOnClickListener {
            val nameString = profileField.text.toString()
            val numberPhoneString = numberField.text.toString()
            saveChanges(nameString, numberPhoneString)
        }

        return root
    }

    companion object{
        private val IMAGE_PICK_CODE = 1000;
        private val PERMISSION_CODE = 1001;
    }

    private fun pickImageFromGallery(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.size>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    pickImageFromGallery()
                }
                else {
                    Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            add_image.setImageURI(data?.data)
        }
    }

    private fun saveChanges(name: String, number: String) {
        if (name.isEmpty() || number.isEmpty()) {
            val toast: Toast = Toast.makeText(
                context, R.string.login,
                Toast.LENGTH_LONG
            )
            toast.show()
        } else {
            val profileEntity =  ProfileEntity(name = name, number_phone = number , created_at = Utilities.currentTimestamp, modified_at = Utilities.currentTimestamp)
            profileViewModel!!.editUserProfile(profileEntity)

            /* Refresh Activity */
        val intent = activity?.intent
            activity?.finish()
            startActivity(intent)
        }
    }

    private fun logout() {
        val sharedpreferences = activity?.getSharedPreferences(
            EventPlannerConstant.PREFERENCES_NAME,
            Context.MODE_PRIVATE
        )
        val editor = sharedpreferences?.edit()
        editor?.clear()
        editor?.apply()
        val intent = Intent(context, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

}