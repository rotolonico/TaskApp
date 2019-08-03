package com.github.rotolonico.taskapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.rotolonico.taskapp.models.Project
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var adapter : ProjectsAdapter
    lateinit var viewModel : ProjectViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ProjectViewModel::class.java)
        initRecycler()
        viewModel.getProjects(System.currentTimeMillis().toString(), (application as TaskAppApplication).userId).observe(this, Observer {
            updateData(it)
        })
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
