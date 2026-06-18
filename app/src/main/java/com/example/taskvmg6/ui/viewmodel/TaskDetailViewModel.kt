package com.example.taskvmg6.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskvmg6.ui.model.Task
import com.example.taskvmg6.ui.repository.TaskRepository
import com.example.taskvmg6.ui.service.ApiResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TaskDetailViewModel(
    private val taskRepository: TaskRepository
) : ViewModel()
{
    private val _state =
        MutableStateFlow<TaskDetailState>(TaskDetailState.Loading)
    val state = _state.asStateFlow()

    var id by mutableStateOf("")
        private set

    var title by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set

    var completed by mutableStateOf(false)
        private set

    fun onIdChange(newId: String) {
        id = newId ?: ""
    }
    fun onTitleChange(newTitle: String) {
        title = newTitle
    }
    fun onDescriptionChange(newDescription: String) {
        description = newDescription
    }
    fun onIsCompletedChange(newIsCompleted: Boolean) {
        completed = newIsCompleted
    }

    fun findById(id: String) {
      clearForm()
      viewModelScope.launch {
          when (val result = taskRepository.findById(id)) {
              is ApiResult.Success -> {
                  _state.value = TaskDetailState.Success(result.data)
              }
              is ApiResult.Error -> {
                  _state.value = TaskDetailState.Error(result.message)
              }
          }
      }
    }

    fun save(){
        val task = Task(
            id = id,
            title = title,
            description = description,
            completed = completed
        )
        viewModelScope.launch {
            when (val result = taskRepository.save(task))
            {
                is ApiResult.Success -> {
                    _state.value = TaskDetailState.Success(result.data)
                }
                is ApiResult.Error -> {
                    _state.value = TaskDetailState.Error(result.message)
                }
            }
        }
    }

    fun clearForm() {
        id = ""
        title = ""
        description = ""
        completed = false
    }
}