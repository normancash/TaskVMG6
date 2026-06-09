package com.example.taskvmg6.ui.viewmodel

import com.example.taskvmg6.ui.model.Task

sealed interface TaskListState {
    data object Loading : TaskListState
    data class Success(val tasks: List<Task>):TaskListState
    data class Error(val message: String) : TaskListState
}