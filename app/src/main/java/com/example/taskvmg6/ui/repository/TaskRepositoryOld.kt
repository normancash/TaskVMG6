package com.example.taskvmg6.ui.repository

import com.example.taskvmg6.ui.model.Task
import com.example.taskvmg6.ui.service.ApiResult
import com.example.taskvmg6.ui.service.TaskService

class TaskRepositoryOld() {
    private val tasks = mutableListOf<Task>()

    fun addTask(task: Task) = tasks.add(task)

    fun getTasks() = tasks

    fun getTask(id: Int) = tasks.find { it.id.equals(id) }

    fun updateTask(task: Task) = tasks.replaceAll {
        if (it.id == task.id) task else it }

    fun deleteTask(taskId: Int) = tasks.removeIf { it.id.equals(taskId) }

    fun deleteTask(task: Task) = tasks.remove(task)
}