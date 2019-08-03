package com.github.rotolonico.taskapp

import androidx.lifecycle.ViewModel
import com.github.rotolonico.taskapp.models.Project

class ProjectViewModel : ViewModel() {

    fun createProject(projectName : String, projectOwner : String){

        return ProjectRepository.createProject(
            Project(
                id = System.currentTimeMillis().toString(),
                name = projectName,
                owner = projectOwner,
                creation = System.currentTimeMillis()
        ))
    }

    fun getProject(projectId : String, projectOwner : String) = ProjectRepository.getProject(projectId, projectOwner)

    fun getProjects(projectId : String, projectOwner : String) = ProjectRepository.getProjects(projectOwner)


}