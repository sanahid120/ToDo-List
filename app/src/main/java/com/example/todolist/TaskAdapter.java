package com.example.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private ArrayList<Task> taskList;
    private DatabaseHelper dbHelper;

    public TaskAdapter(ArrayList<Task> taskList, DatabaseHelper dbHelper) {
        this.taskList = taskList;
        this.dbHelper = dbHelper;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = taskList.get(position);

        // Set the task text and checkbox state
        holder.textView.setText(task.getTaskText());
        holder.checkBox.setChecked(task.isChecked());

        // Reset the listener to avoid triggering it during list updates
        holder.checkBox.setOnCheckedChangeListener(null);

        // Add a new listener
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                int currentPosition = holder.getAdapterPosition();

                // Make sure the position is still valid
                if (currentPosition != RecyclerView.NO_POSITION) {
                    // Delete the task from the database
                    dbHelper.deleteTask(task.getId());

                    // Remove the task from the list
                    taskList.remove(currentPosition);

                    // Notify the adapter of item removal
                    notifyItemRemoved(currentPosition);
                    notifyItemRangeChanged(currentPosition, taskList.size());
                }
            }
        });
    }

    @Override
    public int getItemCount() { return taskList.size(); }

    static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        CheckBox checkBox;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.TestTask);
            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }
}
