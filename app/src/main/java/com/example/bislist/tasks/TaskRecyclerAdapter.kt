package com.example.bislist.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bislist.TaskHolder
import com.example.bislist.databinding.TaskLayoutBinding
import com.example.bislist.tasks.data.Task
import com.example.bislist.tasks.data.TaskActivity
import java.math.RoundingMode
import java.util.*

class TaskRecyclerAdapter(private var tasks:List<Task>, private val onTaskClicked:(Task) -> Unit) : RecyclerView.Adapter<TaskRecyclerAdapter.ViewHolder>(){

    class ViewHolder(val binding:TaskLayoutBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task, onTaskClicked:(Task) -> Unit) {

            binding.title.text = task.taskTitle

            binding.progressTracker.text = task.progressStatus.toString().plus("%")

            binding.card.setOnClickListener {
                onTaskClicked(task)
            }
            binding.removeTaskBt.setOnClickListener {
                TaskDepoManger.instance.removeTask(task)
            }
        }
    }

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = tasks[position]
        holder.bind(task, onTaskClicked)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TaskLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }


    public fun updateCollection(newTasks:List<Task>){
        tasks = newTasks
        notifyDataSetChanged()
    }

}

