package com.example.taskvmg6.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taskvmg6.ui.repository.TaskRepository

class TaskDetailViewModelFactory(private val repository: TaskRepository)
    : ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TaskDetailViewModel(repository) as T
    }
}