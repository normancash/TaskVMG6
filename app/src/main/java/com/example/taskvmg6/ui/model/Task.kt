package com.example.taskvmg6.ui.model

data class Task(
    val id : String,
    val title: String,
    val description: String,
    val completed: Boolean = false
)

