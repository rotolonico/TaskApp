package com.github.rotolonico.taskapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.rotolonico.taskapp.Helpers.SingleLiveEvent
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    fun isSignedIn() : Boolean {
        return AuthHandler.isSignedIn()
    }

    fun signUpUser(email : String, password : String) : SingleLiveEvent<String> {
        val event = SingleLiveEvent<String>()

        viewModelScope.launch {
            val userResponse = AuthHandler.signUpUser(email, password)
            if (userResponse.success){
                event.value = ""
            } else {
                event.value = userResponse.error
            }
        }

        return event
    }

    fun signInUser(email : String, password : String) : SingleLiveEvent<String> {
        val event = SingleLiveEvent<String>()

        viewModelScope.launch {
            val userResponse = AuthHandler.signInUser(email, password)
            if (userResponse.success){
                event.value = ""
            } else {
                event.value = userResponse.error
            }
        }

        return event
    }

    fun goToMainActivity(context : Activity){
        context.startActivity(Intent(context, MainActivity::class.java))
        context.finish()
    }
}