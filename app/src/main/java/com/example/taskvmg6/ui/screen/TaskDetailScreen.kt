package com.example.taskvmg6.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.taskvmg6.ui.viewmodel.TaskViewModel

@Composable
fun TaskDetailScreen(
    navController: NavController,
    taskId: Int,
    viewModel: TaskViewModel = viewModel()
) {
    LaunchedEffect(taskId) {
        viewModel.loadTask(taskId)
    }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        OutlinedTextField(
            value = viewModel.id.toString(),
            onValueChange = {
                viewModel.onIdChange(it)
            },
            label = {
                Text("ID")
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = viewModel.title,
            onValueChange = {

                viewModel.onTitleChange(it)
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
                checked = viewModel.isCompleted,
                onCheckedChange = {

                    viewModel.onIsCompletedChange(it)
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