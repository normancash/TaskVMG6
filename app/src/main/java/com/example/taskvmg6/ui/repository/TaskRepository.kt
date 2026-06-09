package com.example.taskvmg6.ui.repository

import com.example.taskvmg6.ui.model.Task
import com.example.taskvmg6.ui.service.ApiResult
import com.example.taskvmg6.ui.service.TaskService

class TaskRepository(private val api: TaskService) {
    private val tasks = mutableListOf<Task>()

    suspend fun findAll(): ApiResult<List<Task>>  {
        return try {
            val response = api.getAllTasks()
            if (response.isSuccessful) {
                ApiResult.Success(response.body() ?: emptyList())
            }
            else {
                ApiResult.Error("Error HTTP ${response.message()}")
            }
          } catch (e: Exception) {
            ApiResult.Error(e.message ?: "Unknown error")
          }
    }

    fun addTask(task: Task) = tasks.add(task)

    fun getTasks() = tasks

    fun getTask(id: Int) = tasks.find { it.id == id }

    fun updateTask(task: Task) = tasks.replaceAll {
        if (it.id == task.id) task else it }

    fun deleteTask(taskId: Int) = tasks.removeIf { it.id == taskId }

    fun deleteTask(task: Task) = tasks.remove(task)
}