package com.example.taskvmg6.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.taskvmg6.ui.viewmodel.TaskDetailEvent
import com.example.taskvmg6.ui.viewmodel.TaskDetailState
import com.example.taskvmg6.ui.viewmodel.TaskDetailViewModel
import com.example.taskvmg6.ui.viewmodel.TaskViewModel

@Composable
fun TaskDetailScreen(
    navController: NavController,
    taskId: String,
    viewModel: TaskDetailViewModel
) {
    val state = viewModel.state.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    )
    {
        padding ->
        LaunchedEffect(taskId) {
            viewModel.findById(taskId)
        }
        LaunchedEffect(Unit) {
            viewModel.event.collect {
                    event ->
                when(event) {
                    is TaskDetailEvent.Saved -> {
                        navController.popBackStack()
                    }
                    is TaskDetailEvent.Error -> {
                        snackbarHostState.showSnackbar(
                            message = event.message,
                            withDismissAction = true)
                        navController.popBackStack()
                    }
                }

            }
        }
        when(val currentState = state) {
            is TaskDetailState.Loading -> {
                Box(modifier = Modifier.fillMaxSize()
                    ,contentAlignment = Alignment.Center)
                {
                    CircularProgressIndicator()
                }
            }
            is TaskDetailState.Error -> {
                Box(modifier = Modifier.fillMaxSize()
                    ,contentAlignment = Alignment.Center)
                {
                    Text(currentState.message)
                }
            }
            is TaskDetailState.Success -> {
                Column(
                    modifier = Modifier.padding(16.dp)
                )
                {
                    OutlinedTextField(
                        value = viewModel.id,
                        onValueChange = {
                            viewModel.onIdChange(currentState.task.id)
                        },
                        label = {
                            Text("ID")
                        }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = viewModel.title,
                        onValueChange = {

                            viewModel.onTitleChange(currentState.task.title)
                        },
                        label = {
                            Text("Título")
                        }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Checkbox(
                            checked = viewModel.completed,
                            onCheckedChange = {

                                viewModel.onIsCompletedChange(currentState.task.completed)
                            }
                        )

                        Text("Completada")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {

                            navController.popBackStack()
                        }
                    ) {

                        Text("Regresar")
                    }
                }
            }
        }
    }
}