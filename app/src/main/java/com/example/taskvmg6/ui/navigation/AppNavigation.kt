package com.example.taskvmg6.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.taskvmg6.ui.repository.TaskRepository
import com.example.taskvmg6.ui.screen.TaskDetailScreen
import com.example.taskvmg6.ui.screen.TaskListScreen
import com.example.taskvmg6.ui.service.RetrofitClient
import com.example.taskvmg6.ui.viewmodel.TaskListViewModel
import com.example.taskvmg6.ui.viewmodel.TaskListViewModelFactory

@Composable
fun AppNavigation(modifier: Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController
        , startDestination = TaskList)
    {
        composable<TaskList> {
            val viewModel = TaskListViewModel =
                viewModel(factory = TaskListViewModelFactory(
                    TaskRepository(RetrofitClient.taskService)
                    )
                )
            TaskListScreen(navController)
        }
        composable<TaskDetail> { backStackEntry ->
            val taskId = backStackEntry
                .toRoute<TaskDetail>().taskId
            TaskDetailScreen(navController, taskId)
        }
    }
}