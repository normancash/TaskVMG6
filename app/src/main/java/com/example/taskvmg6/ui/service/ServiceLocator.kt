package com.example.taskvmg6.ui.service

import com.example.taskvmg6.ui.repository.TaskRepository

object ServiceLocator {
    private val api =
        RetrofitClient.taskService

    val taskRepository =
        TaskRepository(api)
}