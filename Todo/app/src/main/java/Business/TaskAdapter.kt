package Business

import Data.Task
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistapp.R

class TaskAdapter(private var tasks: List<Task>, private val onEdit: (Task) -> Unit, private val onDelete: (Task) -> Unit) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.bind(task, onEdit, onDelete)
    }

    override fun getItemCount() = tasks.size

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.taskTitleTextView)
        private val editButton: Button = itemView.findViewById(R.id.editTaskButton)
        private val deleteButton: Button = itemView.findViewById(R.id.deleteTaskButton)

        fun bind(task: Task, onEdit: (Task) -> Unit, onDelete: (Task) -> Unit) {
            titleTextView.text = task.title
            editButton.setOnClickListener { onEdit(task) }
            deleteButton.setOnClickListener { onDelete(task) }
        }
    }
}
