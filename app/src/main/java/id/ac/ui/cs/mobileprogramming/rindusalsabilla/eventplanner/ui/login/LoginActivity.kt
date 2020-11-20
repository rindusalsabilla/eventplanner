package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.MainActivity
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.R
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.utils.EventPlannerConstant

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private var viewModel: LoginViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedpreferences = this.application.getSharedPreferences(
            EventPlannerConstant.PREFERENCES_NAME,
            Context.MODE_PRIVATE
        )
        val userLogin =
            sharedpreferences.getString(EventPlannerConstant.KEY_USER_ID, "")
        if (userLogin != null && !userLogin.isEmpty()) {
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
        setContentView(R.layout.activity_login)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        val createAccount = findViewById<View>(R.id.button_register)
        val loginButton = findViewById<View>(R.id.button_login)
        createAccount.setOnClickListener(this)
        loginButton.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_register -> {
                val intent = Intent(this, CreateAccountActivity::class.java)
                startActivity(intent)
            }
            R.id.button_login -> {
                val inputUsername =
                    findViewById<View>(R.id.field_username) as EditText
                val inputPassword =
                    findViewById<View>(R.id.field_password) as EditText
                val username = inputUsername.text.toString()
                val password = inputPassword.text.toString()
                viewModel!!.getUserLoginByUsernameAndPassword(username, password)
                    .observe(this, Observer<Any?> { user ->
                        if (user != null) {
                            val sharedpreferences = getSharedPreferences(
                                EventPlannerConstant.PREFERENCES_NAME,
                                Context.MODE_PRIVATE
                            )
                            val editor = sharedpreferences.edit()
                            editor.putString(
                                EventPlannerConstant.KEY_USER_ID,
                                String.valueOf(user.getId())
                            )
                            editor.apply()
                            val toast: Toast = Toast.makeText(
                                applicationContext, R.string.toastLoginSuccess,
                                Toast.LENGTH_SHORT
                            )
                            toast.show()
                            val intent =
                                Intent(applicationContext, MainActivity::class.java)
                            intent.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
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