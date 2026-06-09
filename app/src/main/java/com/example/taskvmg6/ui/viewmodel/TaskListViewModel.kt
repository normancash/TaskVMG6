package com.example.taskvmg6.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.taskvmg6.ui.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TaskListViewModel(
    private val taskRepository: TaskRepository
) : ViewModel()
{
    private val _state =
        MutableStateFlow<TaskListState>(TaskListState.Loading)
    val state = _state.asStateFlow()
}