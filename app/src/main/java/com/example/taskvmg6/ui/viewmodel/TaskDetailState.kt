package com.example.taskvmg6.ui.viewmodel

import com.example.taskvmg6.ui.model.Task

sealed interface TaskDetailState {
    data object Loading : TaskDetailState
    data class Success(val task: Task) : TaskDetailState
    data class Error(val message: String) : TaskDetailState
}