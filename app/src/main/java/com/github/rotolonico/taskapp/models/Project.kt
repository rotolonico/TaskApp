package com.github.rotolonico.taskapp.models

data class Project(
    var name: String = "",
    var creation: Long = -1L,
    var tasks: List<Task> = ArrayList()
)