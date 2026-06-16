package com.example.taskvmg6.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskvmg6.ui.navigation.TaskDetail
import com.example.taskvmg6.ui.viewmodel.TaskListState
import com.example.taskvmg6.ui.viewmodel.TaskListViewModel

@Composable
fun TaskListScreen(
    navController: NavController,
    viewModel: TaskListViewModel)
{
    val state by viewModel.state.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize()
        ,floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(TaskDetail(-1))
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Agregar tarea"
                )
            }
        }
    )
    { padding ->
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Lista de tareas",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        when (val currentState = state){
            //CARGANDO
            is TaskListState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    CircularProgressIndicator()
                }
            }
            is TaskListState.Success -> {
                if (currentState.tasks.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ){
                        Text("No hay tareas registradas")
                    }
                }
                else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                            .padding(padding)
                            .padding(16.dp)
                    )
                    {
                        items(currentState.tasks.size)
                        {
                            Card(
                                modifier = Modifier.padding(vertical = 8.dp)
                                    .fillMaxWidth()
                            )
                            {
                                Row(
                                    modifier = Modifier.padding(2.dp).fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                    ,verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(currentState.tasks[it].id.toString())
                                    Text(currentState.tasks[it].title)
                                    Checkbox(
                                        checked = currentState.tasks[it].completed,
                                        onCheckedChange = {}
                                    )
                                }
                            }
                        }
                    }
                }
            }
            is TaskListState.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text(currentState.message)
                }
            }
        }
    }
}