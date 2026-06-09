package com.example.taskvmg6.ui.service

sealed interface ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>
    data class Error(val message: String) : ApiResult<Nothing>
}