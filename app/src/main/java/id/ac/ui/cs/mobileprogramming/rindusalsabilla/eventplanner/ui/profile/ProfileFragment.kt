package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.profile

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.profile.ProfileEntity
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.databinding.FragmentProfileBinding
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.auth.LoginActivity
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.utils.EventPlannerConstant
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment : Fragment() {
    private lateinit var myView: View
    private lateinit var profileFragmentViewModel: ProfileViewModel

    @SuppressLint("WrongViewCast")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val  binding: FragmentProfileBinding = FragmentProfileBinding.inflate(inflater, container, false)
        this.myView = binding.root

        profileFragmentViewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        profileFragmentViewModel.profile()

        profileFragmentViewModel.profile.observe(viewLifecycleOwner, Observer<ProfileEntity> {
            binding.viewName.text = it?.name
            binding.viewPhone.text = it?.number_phone

            val bitmap: Bitmap = profileFragmentViewModel.changeToBitmap(it?.image!!)
            binding.viewImage.setImageBitmap(bitmap)
        })


        myView.edit_profile.setOnClickListener {
            val intent: Intent = Intent(myView.context, ChangeProfileActivity::class.java)
            startActivity(intent)

        }
        myView.logout.setOnClickListener(View.OnClickListener { logout() })

        return myView
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