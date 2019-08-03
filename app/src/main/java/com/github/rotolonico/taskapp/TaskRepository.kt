package com.github.rotolonico.taskapp

import com.google.firebase.database.FirebaseDatabase

class TaskRepository{

    val database = FirebaseDatabase.getInstance()
    val databaseReference = database.getReference("message")


}