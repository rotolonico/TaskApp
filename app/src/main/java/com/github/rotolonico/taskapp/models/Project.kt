package com.github.rotolonico.taskapp.models

data class Project(
    var id : String  = "",
    var name: String = "",
    var creation: Long = 0,
    var owner: String = ""
)