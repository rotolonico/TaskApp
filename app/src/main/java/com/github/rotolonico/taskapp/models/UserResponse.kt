package com.github.rotolonico.taskapp.models

import com.google.firebase.auth.FirebaseUser

data class UserResponse(
    var user : FirebaseUser? = null,
    var success : Boolean = true,
    var error : String = ""
)