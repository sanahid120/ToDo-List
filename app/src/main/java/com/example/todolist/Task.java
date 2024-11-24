package com.example.todolist;

public class Task {
    private int id;
    private String taskText;
    private boolean isChecked;

    public Task(int id, String taskText, boolean isChecked) {
        this.id = id;
        this.taskText = taskText;
        this.isChecked = isChecked;
    }

    public int getId() { return id; }
    public String getTaskText() { return taskText; }
    public boolean isChecked() { return isChecked; }
}
