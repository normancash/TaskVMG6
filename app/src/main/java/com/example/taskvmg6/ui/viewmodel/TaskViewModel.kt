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

    var id by mutableStateOf(0)
        private set

    var title by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set

    var isCompleted by mutableStateOf(false)
        private set

    fun onIdChange(newId: String) {
        id = newId.toIntOrNull() ?: 0
    }
    fun onTitleChange(newTitle: String) {
        title = newTitle
    }
    fun onDescriptionChange(newDescription: String) {
        description = newDescription
    }
    fun onIsCompletedChange(newIsCompleted: Boolean) {
        isCompleted = newIsCompleted
    }

    init {
        loadTask()
    }

    private fun loadTask() {
        tasks = taskRepository.getTasks()
    }
    fun loadTask(taskId: Int) {
        if (taskId == -1) {
            clearForm()
            return
        }
        val task = taskRepository.getTask(taskId)
        if (task != null) {
            id = task.id
            title = task.title
            description = task.description
            isCompleted = task.isCompleted
        }
    }

    fun addTask() {
        taskRepository.addTask(
            Task(
                id = id,
                title = title,
                description = description,
                isCompleted = isCompleted
            )
        )
        loadTask()
    }

    fun updateTask() {
        taskRepository.updateTask(
            Task(
                id = id,
                title = title,
                description = description,
                isCompleted = isCompleted
            )
        )
        loadTask()
    }
    fun deleteTask(taskId: Int) {
        taskRepository.deleteTask(taskId)
        loadTask()
    }

    fun clearForm() {
        id = 0
        title = ""
        description = ""
        isCompleted = false
    }


}