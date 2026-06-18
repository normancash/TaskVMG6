package com.example.taskvmg6.ui.service

import com.example.taskvmg6.ui.model.Task
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TaskService {

    @GET("task/all")
    suspend fun getAllTasks(): Response<List<Task>>

    @GET("task/get/{id}")
    suspend fun getTaskById(@Path("id") id: String):
            Response<Task>

    @POST("task/save")
    suspend fun saveTask(@Body task: Task): Response<Task>

}