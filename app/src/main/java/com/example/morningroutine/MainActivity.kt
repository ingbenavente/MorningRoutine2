package com.example.morningroutine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Task(val title: String, var isCompleted: Boolean)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MorningRoutineScreen()
        }
    }
}

@Composable
fun MorningRoutineScreen() {
    val tasks = listOf(
        Task("Wake up at 6 AM", false),
        Task("Exercise for 30 minutes", false),
        Task("Prepare breakfast", false),
        Task("Check emails", false),
        Task("Plan daily tasks", false),
        Task("Start work at 9 AM", false)
    ).toMutableStateList()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(tasks) { task ->
            TaskItem(task = task, onTaskCompleted = { task.isCompleted = !task.isCompleted })
        }
    }
}

@Composable
fun TaskItem(task: Task, onTaskCompleted: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            painter = painterResource(id = if (task.isCompleted) R.drawable.a else R.drawable.b),
            contentDescription = null,
            tint = if (task.isCompleted) Color.Green else Color.Red,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = task.title,
            fontSize = 18.sp,
            modifier = Modifier.weight(1f).padding(start = 8.dp)
        )
        IconButton(onClick = onTaskCompleted) {
            Text(
                text = if (task.isCompleted) "Undo" else "Complete",
                color = Color.Blue,
                fontSize = 14.sp
            )
        }
    }
}