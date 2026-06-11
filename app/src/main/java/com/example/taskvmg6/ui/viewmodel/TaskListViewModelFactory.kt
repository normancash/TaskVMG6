package com.example.taskvmg6.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taskvmg6.ui.repository.TaskRepository

class TaskListViewModelFactory(private val repository: TaskRepository)
    : ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TaskListViewModel(repository) as T
    }
}