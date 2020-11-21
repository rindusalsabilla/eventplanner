package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.profile

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.R
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.profile.ProfileEntity
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.databinding.FragmentProfileBinding
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.event.AddEventActivity
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel

    private lateinit var binding: FragmentProfileBinding
    val ADD_PROFILE_REQUEST = 1
    private lateinit var mView: View
    private lateinit var buttonEditProfile: Button

    @SuppressLint("WrongViewCast")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val  binding: FragmentProfileBinding = FragmentProfileBinding.inflate(inflater, container, false)
        this.mView = binding.root

        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        profileViewModel.getProfile()

        profileViewModel.profile.observe(viewLifecycleOwner, Observer<ProfileEntity> {
            binding.viewName.text = it?.name
            binding.viewPhone.text = it?.number_phone

            val bitmap: Bitmap = profileViewModel.convertToBitmap(it?.image!!)
            binding.viewImage.setImageBitmap(bitmap)
        })


        mView.edit_profile.setOnClickListener {
//            val context= edit_profile?.context
            val intent: Intent = Intent(mView.context, EditProfileActivity::class.java)
            startActivity(intent)

        }
        return mView
    }

}