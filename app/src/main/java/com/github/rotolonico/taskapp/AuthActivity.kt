package com.github.rotolonico.taskapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.activity_main.loginButton
import kotlinx.android.synthetic.main.activity_main.registerButton

class AuthActivity : AppCompatActivity() {

    lateinit var viewModel : AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)

        if (viewModel.isSignedIn()) goToMainActivity()

        loginButton.setOnClickListener {
            viewModel.signInUser(emailInput.text.toString(), passwordInput.text.toString()).observe(this, Observer {
                if (it == "") goToMainActivity()
                else errorText.text = it
            })
        }

        registerButton.setOnClickListener {
            viewModel.signUpUser(emailInput.text.toString(), passwordInput.text.toString()).observe(this, Observer {
                if (it == "") goToMainActivity()
                else errorText.text = it
            })
        }
    }

    private fun goToMainActivity(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
