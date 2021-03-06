package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.R
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.data.login.LoginEntity
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.utils.InjectorUtils
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.utils.Utilities

class RegisterActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var registerViewModel: RegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val factory = InjectorUtils.provideRegisterViewModelFactory(this.applicationContext)
        registerViewModel = ViewModelProviders.of(this, factory).get(RegisterViewModel::class.java)

        val createAccount = findViewById<View>(R.id.button_create)
        createAccount.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_create -> createUser()
        }
    }

    private fun createUser() {
        val editUsername =
            findViewById<View>(R.id.field_username) as EditText
        val editPassword =
            findViewById<View>(R.id.field_password) as EditText
        val editName = findViewById<View>(R.id.field_name) as EditText
        val editNumberPhone =
            findViewById<View>(R.id.field_phone) as EditText
        val username = editUsername.text.toString()
        val password = editPassword.text.toString()
        val name = editName.text.toString()
        val phone = editNumberPhone.text.toString()
        if (username.isEmpty() || password.isEmpty() || name.isEmpty() || phone.isEmpty()) {
            val toast: Toast = Toast.makeText(
                applicationContext, R.string.required_field,
                Toast.LENGTH_SHORT
            )
            toast.show()
        } else {
            val loginEntity =  LoginEntity(username = username, password = password, createdAt = Utilities.timeNow)
            registerViewModel!!.createUser(loginEntity)
            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }
}
