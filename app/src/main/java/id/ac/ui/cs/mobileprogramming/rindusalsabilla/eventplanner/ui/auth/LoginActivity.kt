package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.auth

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.MainActivity
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.R
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.login.LoginEntity
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.profile.ChangeProfileActivity
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.utils.EventPlannerConstant
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.utils.InjectorUtils
import java.util.logging.Logger

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var connManager: ConnectivityManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedpreferences = this.application.getSharedPreferences(
            EventPlannerConstant.PREFERENCES_NAME,
            Context.MODE_PRIVATE
        )
        this.connManager = applicationContext?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val userLogin =
            sharedpreferences.getString(EventPlannerConstant.KEY_USER_ID, "")
        if (userLogin != null && !userLogin.isEmpty()) {
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
        setContentView(R.layout.activity_login)

        val factory = InjectorUtils.provideLoginViewModelFactory(this.applicationContext)
        loginViewModel = ViewModelProviders.of(this, factory).get(LoginViewModel::class.java)


        val createAccount = findViewById<View>(R.id.button_register)
        val loginButton = findViewById<View>(R.id.button_login)
        createAccount.setOnClickListener(this)
        loginButton.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_register -> {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }
            R.id.button_login -> {

                val inputUsername =
                    findViewById<View>(R.id.field_username) as EditText
                val inputPassword =
                    findViewById<View>(R.id.field_password) as EditText
                val username = inputUsername.text.toString()
                val password = inputPassword.text.toString()
                loginViewModel!!.getUserLoginByUsernameAndPassword(username, password)
                    .observe(this, Observer<LoginEntity> { user ->
                        if (user != null) {
                            val mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                            if (!mWifi.isConnected) {
                                Toast.makeText(applicationContext, "WiFi tidak terhubung, tolong dinyalakan dulu", Toast.LENGTH_LONG).show()
                            }
                            else{
                                val sharedpreferences = getSharedPreferences(
                                        EventPlannerConstant.PREFERENCES_NAME,
                                        Context.MODE_PRIVATE
                                )
                                val editor = sharedpreferences.edit()
                                editor.putString(
                                        EventPlannerConstant.KEY_USER_ID,
                                        user.id.toString()
                                )
                                editor.apply()
                                val toast: Toast = Toast.makeText(
                                        applicationContext, R.string.toastLoginSuccess,
                                        Toast.LENGTH_SHORT
                                )
                                toast.show()
                                val intent =
                                        Intent(applicationContext, ChangeProfileActivity::class.java)
                                intent.flags =
                                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                startActivity(intent)
                            }
                        } else {
                            val toast: Toast = Toast.makeText(
                                applicationContext, R.string.login_failed,
                                Toast.LENGTH_SHORT
                            )
                            toast.show()
                        }
                    })
            }
        }
    }
}