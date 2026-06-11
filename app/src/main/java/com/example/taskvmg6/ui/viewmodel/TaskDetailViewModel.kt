package com.example.taskvmg6.ui.viewmodel

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

    fun findById(id: Int) {
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

    fun save(task: Task) {
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
}