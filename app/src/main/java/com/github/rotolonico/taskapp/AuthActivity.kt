package com.github.rotolonico.taskapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity() {

    lateinit var viewModel : AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)

        if (viewModel.isSignedIn()) viewModel.goToMainActivity(this)

        loginButton.setOnClickListener {
            viewModel.signInUser(emailInput.text.toString(), passwordInput.text.toString()).observe(this, Observer {
                if (it == "") viewModel.goToMainActivity(this)
                else errorText.text = it
            })
        }

        registerButton.setOnClickListener {
            viewModel.signUpUser(emailInput.text.toString(), passwordInput.text.toString()).observe(this, Observer {
                if (it == "") viewModel.goToMainActivity(this)
                else errorText.text = it
            })
        }
    }
}
