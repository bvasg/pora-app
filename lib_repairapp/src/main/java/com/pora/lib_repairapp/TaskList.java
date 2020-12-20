package com.pora.lib_repairapp;

import java.util.ArrayList;

public class TaskList implements Sizable {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    @Override
    public int size() {
        return tasks.size();
    }

    @Override
    public String toString() {
        return "TaskList{" +
                "tasks=" + tasks.toString() +
                '}';
    }

    public Task getTaskByPosition(int position) {
        return tasks.get(position);
    }
}
