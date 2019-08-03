package com.github.rotolonico.taskapp

import com.google.firebase.auth.FirebaseUser

data class UserResponse(
    var user : FirebaseUser? = null,
    var success : Boolean = true,
    var error : String = ""
)