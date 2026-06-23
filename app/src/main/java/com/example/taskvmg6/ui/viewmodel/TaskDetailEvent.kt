package com.example.taskvmg6.ui.viewmodel

sealed interface TaskDetailEvent {
    data object Saved : TaskDetailEvent
    data class Error(
        val message: String
    ) : TaskDetailEvent
}
