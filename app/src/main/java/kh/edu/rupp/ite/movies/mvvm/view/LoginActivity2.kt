package kh.edu.rupp.ite.movies.mvvm.view

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kh.edu.rupp.ite.movies.Activities.MainActivity
import kh.edu.rupp.ite.movies.Activities.SignupActivity
import kh.edu.rupp.ite.movies.R
import kh.edu.rupp.ite.movies.api.client.ApiClient
import kh.edu.rupp.ite.movies.api.model.Profile
import kh.edu.rupp.ite.movies.mvvm.viewmodel.LoginViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity2: AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var userNameEt: EditText
    private lateinit var passwordEt: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        userNameEt = findViewById(R.id.email)
        passwordEt = findViewById(R.id.password)
        val loginBtn = findViewById<EditText>(R.id.btnLogin)
        loginBtn.setOnClickListener({
            loginViewModel.login(userNameEt.getText().toString(), passwordEt.getText().toString())
        })

        val signup = findViewById<TextView>(R.id.txtSignup)
        signup.setOnClickListener({
            val intent = Intent(this@LoginActivity2, SignupActivity::class.java)
            startActivities(arrayOf(intent))
        })

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        loginViewModel.getIsLoginSuccessful()?.observe(this, loginObserver)
    }
    private val loginObserver: Observer<Boolean> = object : Observer<Boolean> {
        override fun onChanged(isSuccess: Boolean) {
            if (isSuccess) {
                Toast.makeText(this@LoginActivity2, "Login Successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@LoginActivity2, MainActivity::class.java)
                startActivities(arrayOf(intent))
                finish()
            } else {
                Toast.makeText(
                    this@LoginActivity2,
                    "Login Failed",
                    Toast.LENGTH_SHORT

                )
                    .show()
            }
        }
    }

}