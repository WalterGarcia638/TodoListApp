package com.example.todolistapp

import Business.TaskAdapter
import Data.Task
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.databinding.ActivityMainBinding
import com.example.todolistapp.ui.theme.TodoListAppTheme
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : ComponentActivity() {
    private lateinit var taskEditText: EditText
    private lateinit var tasksRecyclerView: RecyclerView
    private val db = FirebaseFirestore.getInstance()
    private lateinit var adapter: TaskAdapter
    private var tasks = mutableListOf<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        taskEditText = findViewById(R.id.taskEditText)
        tasksRecyclerView = findViewById(R.id.tasksRecyclerView)

        adapter = TaskAdapter(tasks, { task -> editTask(task) }, { task -> deleteTask(task.id) })
        tasksRecyclerView.layoutManager = LinearLayoutManager(this)
        tasksRecyclerView.adapter = adapter

        findViewById<Button>(R.id.addTaskButton).setOnClickListener {
            addTask(taskEditText.text.toString())
        }

        loadTasks()
    }

    private fun loadTasks() {
        db.collection("tasks").get().addOnSuccessListener { result ->
            tasks.clear()
            for (document in result) {
                val task = Task(document.id, document.getString("title") ?: "")
                tasks.add(task)
            }
            adapter.notifyDataSetChanged()
        }
    }

    private fun addTask(title: String) {
        // Añadir tarea a Firestore
    }

    private fun editTask(task: Task) {
        // Mostrar diálogo de edición
    }

    private fun deleteTask(taskId: String) {
        // Borrar tarea de Firestore
    }
}