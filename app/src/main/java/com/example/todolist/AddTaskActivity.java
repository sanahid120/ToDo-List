package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddTaskActivity extends AppCompatActivity {
    private EditText editTextTask;
    private Button btnSaveTask;
    private DatabaseHelper dbHelper;
    private ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        // Initialize views
        editTextTask = findViewById(R.id.et_add_task);
        btnSaveTask = findViewById(R.id.btnSaveTask);
        back = findViewById(R.id.back_btn);
        dbHelper = new DatabaseHelper(this);
        back.setOnClickListener(v->{
            finish();
        });

        // Handle Save Button Click
        btnSaveTask.setOnClickListener(v -> {
            String taskText = editTextTask.getText().toString().trim();

            // Check if task text is empty
            if (taskText.isEmpty()) {
                Toast.makeText(AddTaskActivity.this, "Please enter a task", Toast.LENGTH_SHORT).show();
            } else {
                // Add task to database
                boolean isInserted = dbHelper.addTask(taskText);

                if (isInserted) {
                    Toast.makeText(AddTaskActivity.this, "Task added successfully", Toast.LENGTH_SHORT).show();
                    // Go back to MainActivity
                    Intent intent = new Intent(AddTaskActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(AddTaskActivity.this, "Failed to add task", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
