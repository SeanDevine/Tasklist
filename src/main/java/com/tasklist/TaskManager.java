package com.tasklist;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final List<Task> taskList;

    public TaskManager() {
        this.taskList = new ArrayList<>();
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void removeTask(Task task) {
        taskList.remove(task);
    }

    public List<Task> getTasks() {
        return taskList;
    }

    public void updateTask(Task task) {
        // Retrieve the task from the list of tasks
        int index = taskList.indexOf(task);
        if (index >= 0) {
            taskList.set(index, task);
        } else {
            throw new IllegalArgumentException("Task not found: " + task);
        }
    }

}
