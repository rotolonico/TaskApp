package com.github.rotolonico.taskapp.models

data class Task(
    var name: String,
    var expiration: Long,
    var difficulty: Int
)