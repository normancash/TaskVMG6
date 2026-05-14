package com.example.taskvmg6.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.taskvmg6.ui.screen.TaskDetailScreen
import com.example.taskvmg6.ui.screen.TaskListScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController
        , startDestination = TaskList)
    {
        composable<TaskList> {
            TaskListScreen(navController)
        }
        composable<TaskDetail> { backStackEntry ->
            val taskId = backStackEntry
                .toRoute<TaskDetail>().taskId
            TaskDetailScreen(navController, taskId)
        }
    }
}