package com.github.rotolonico.taskapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.rotolonico.taskapp.models.Project
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var adapter: ProjectsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecycler()
    }

    fun updateData(projects: List<Project>){
        adapter.apply {
            data = projects
            notifyDataSetChanged()
        }
    }

    private fun initRecycler() {
        projectsRecycler.layoutManager = LinearLayoutManager(this)
        projectsRecycler.adapter = ProjectsAdapter(ArrayList())
    }
}
