package com.example.taskvmg6.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.taskvmg6.ui.screen.TaskDetailScreen
import com.example.taskvmg6.ui.screen.TaskListScreen
import com.example.taskvmg6.ui.service.ServiceLocator
import com.example.taskvmg6.ui.viewmodel.TaskDetailViewModel
import com.example.taskvmg6.ui.viewmodel.TaskDetailViewModelFactory
import com.example.taskvmg6.ui.viewmodel.TaskListViewModel
import com.example.taskvmg6.ui.viewmodel.TaskListViewModelFactory

@Composable
fun AppNavigation(modifier: Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController
        , startDestination = TaskList)
    {
        composable<TaskList> {
            val viewModel : TaskListViewModel =
                viewModel(factory = TaskListViewModelFactory(
                        ServiceLocator.taskRepository
                    )
                )
            TaskListScreen(navController,viewModel)
        }
        composable<TaskDetail> { backStackEntry ->
            val viewModel : TaskDetailViewModel =
                viewModel(factory = TaskDetailViewModelFactory(
                    ServiceLocator.taskRepository
                )
            )
            val taskId = backStackEntry
                .toRoute<TaskDetail>().taskId
            Log.i("INFO APINAVIGATION","ID: ${taskId}")
            TaskDetailScreen(navController, taskId,viewModel)
        }
    }
}