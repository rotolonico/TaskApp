package com.github.rotolonico.taskapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.rotolonico.taskapp.models.Project
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.core.view.marginRight
import android.widget.LinearLayout




class MainActivity : AppCompatActivity() {

    lateinit var adapter : ProjectsAdapter
    lateinit var viewModel : ProjectViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ProjectViewModel::class.java)

        userEmail.text = (application as TaskAppApplication).userEmail

        addListeners()
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

    private fun showAddDialog() {
        val projectTitle = EditText(this)
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        ).apply {
            setMargins(16F.toDp(this@MainActivity), 0, 16F.toDp(this@MainActivity), 0)
        }

        projectTitle.layoutParams = layoutParams
        val dialog = AlertDialog.Builder(this, R.style.ThemeOverlay_MaterialComponents_Dialog_Alert)
            .setTitle("Add a new task")
            .setMessage("What do you want to do next?")
            .setView(projectTitle)
            .setPositiveButton("Add"
            ) { dialog, which ->
                val title = projectTitle.text.toString()
                viewModel.createProject(title, (application as TaskAppApplication).userId)
            }
            .setNegativeButton("Cancel", null)
            .create()

        dialog.show()
    }

    private fun addListeners() {
        addProject.setOnClickListener {
            showAddDialog()
        }
    }

    private fun initRecycler() {
        adapter = ProjectsAdapter(ArrayList())
        projectsRecycler.layoutManager = LinearLayoutManager(this)
        projectsRecycler.adapter = adapter
    }
}
