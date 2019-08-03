package com.github.rotolonico.taskapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.github.rotolonico.taskapp.models.Project
import com.github.rotolonico.taskapp.models.UserResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class ProjectRepository{

    companion object {

        fun createProject(project: Project) {
            FirebaseFirestore.getInstance().collection("${project.owner}/projects/")
                .document(project.id).set(project)
        }

        fun getProject(projectId: String, projectOwner: String): LiveData<Project> {

            val liveData = MutableLiveData<Project>()

            FirebaseFirestore.getInstance().collection("${projectOwner}/projects/")
                .document(projectId).addSnapshotListener { documentSnapshot, exception ->
                    liveData.value = documentSnapshot?.toObject(Project::class.java)
                }

            return liveData
        }

        fun getProjects(projectsOwner: String): LiveData<List<Project>> {

            val liveData = MutableLiveData<List<Project>>()
            val projects = ArrayList<Project>()

            FirebaseFirestore.getInstance().collection("${projectsOwner}/projects/")
                .addSnapshotListener { documentSnapshot, exception ->

                    documentSnapshot?.documents?.forEach { it ->
                        it?.let {
                            it.toObject(Project::class.java)?.let {
                                projects.add(it)
                            }
                        }
                    }

                    liveData.value = projects
                }

            return liveData
        }
    }
}