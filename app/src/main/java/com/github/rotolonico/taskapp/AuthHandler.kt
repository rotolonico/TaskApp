package com.github.rotolonico.taskapp

import com.github.rotolonico.taskapp.models.UserResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class AuthHandler {
    companion object {
        fun isSignedIn(): FirebaseUser? = FirebaseAuth.getInstance().currentUser

        suspend fun signUpUser(email: String, password: String): UserResponse {
            val signUpUserResponse = UserResponse()
            return try {
                val authResult =
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .await()
                signUpUserResponse.apply {
                    user = authResult.user
                }
            } catch (e: Exception) {
                signUpUserResponse.apply {
                    success = false
                    error = e.localizedMessage
                }
            }
        }

        suspend fun signInUser(email: String, password: String): UserResponse {
            val signInUserResponse = UserResponse()
            return try {
                val authResult =
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).await()
                signInUserResponse.apply {
                    user = authResult.user
                }
            } catch (e: Exception) {
                signInUserResponse.apply {
                    success = false
                    error = e.localizedMessage
                }
            }
        }


    }
}