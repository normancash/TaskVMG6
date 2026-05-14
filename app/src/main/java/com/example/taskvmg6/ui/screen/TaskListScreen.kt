package com.example.taskvmg6.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.taskvmg6.ui.navigation.TaskDetail
import com.example.taskvmg6.ui.viewmodel.TaskViewModel

@Composable
fun TaskListScreen(
    navController: NavController,
    viewModel: TaskViewModel = viewModel()
) {

    Column {

        Button(
            onClick = {
                navController.navigate(
                    TaskDetail(-1)
                )
            }
        ) {

            Text("Nueva Tarea")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {

            items(viewModel.tasks) { task ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text("ID: ${task.id}")

                        Text("Título: ${task.title}")

                        Text(
                            if(task.isCompleted)
                                "Completada"
                            else
                                "Pendiente"
                        )

                        Row {

                            Button(
                                onClick = {
                                    navController.navigate(
                                        TaskDetail(task.id)
                                    )
                                }
                            ) {

                                Text("Visualizar")
                            }

                            Spacer(
                                modifier = Modifier.width(8.dp)
                            )

                            Button(
                                onClick = {

                                    viewModel.deleteTask(task.id)
                                }
                            ) {

                                Text("Eliminar")
                            }
                        }
                    }
                }
            }
        }
    }
}