package com.example.taskvmg6.ui.model

data class Task(
    val id : Int,
    val title: String,
    val description: String,
    val isCompleted: Boolean = false
)

