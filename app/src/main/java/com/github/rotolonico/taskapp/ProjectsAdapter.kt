package com.github.rotolonico.taskapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.rotolonico.taskapp.models.Project
import kotlinx.android.synthetic.main.item_project.view.*
import java.text.SimpleDateFormat
import java.util.*

class ProjectsAdapter(var data: List<Project>): RecyclerView.Adapter<ProjectsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_project, parent, false) as View

        return ViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.projectTitle.text = data[position].name
        holder.view.projectCreated.text = timestampToReadable(data[position].creation)
    }

    fun timestampToReadable(timestamp: Long): String? = SimpleDateFormat("dd MM yyyy HH:mm").format(Date(timestamp))

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}