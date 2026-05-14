package com.example.taskvmg6.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.taskvmg6.ui.model.Task
import com.example.taskvmg6.ui.repository.TaskRepository

class TaskViewModel : ViewModel() {
    private val taskRepository = TaskRepository()

    var tasks by mutableStateOf(
        listOf<Task>()
    )
    private set

    var title by mutableStateOf("")

    init {
        loadTask()
    }

    private fun loadTask() {
        tasks = taskRepository.getTasks()
    }
}