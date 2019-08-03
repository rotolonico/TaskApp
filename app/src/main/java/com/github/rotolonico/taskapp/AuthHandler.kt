package com.github.rotolonico.taskapp

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class AuthHandler {

    fun isSignedIn() : Boolean = FirebaseAuth.getInstance().currentUser != null

    suspend fun signUpUser(email : String, password : String) : UserResponse? {
        val signUpUserResponse = UserResponse()
        return try {
            val authResult = FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).await()
            signUpUserResponse.apply {
                user = authResult.user
            }
        } catch (e : Exception){
            signUpUserResponse.apply {
                success = false
                error = e.localizedMessage
            }
        }
    }

    suspend fun signInUser(email : String, password : String) : UserResponse? {
        val signInUserResponse = UserResponse()
        return try {
            val authResult = FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).await()
            signInUserResponse.apply {
                user = authResult.user
            }
        } catch (e : Exception){
            signInUserResponse.apply {
                success = false
                error = e.localizedMessage
            }
        }
    }
}