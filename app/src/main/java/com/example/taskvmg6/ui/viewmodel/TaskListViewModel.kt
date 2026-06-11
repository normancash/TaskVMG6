package com.example.taskvmg6.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskvmg6.ui.repository.TaskRepository
import com.example.taskvmg6.ui.service.ApiResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TaskListViewModel(
    private val taskRepository: TaskRepository
) : ViewModel()
{
    private val _state =
        MutableStateFlow<TaskListState>(TaskListState.Loading)
    val state = _state.asStateFlow()

    init {
        findAll()
    }

    private fun findAll() {
        viewModelScope.launch {
            _state.value = TaskListState.Loading
            when (val result = taskRepository.findAll())
            {
                is ApiResult.Success ->{
                    _state.value = TaskListState.Success(result.data)
                }
                is ApiResult.Error ->{
                    _state.value = TaskListState.Error(result.message)
                }
            }
        }
    }
}