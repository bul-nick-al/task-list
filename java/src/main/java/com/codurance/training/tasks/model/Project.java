package com.codurance.training.tasks.model;

import com.codurance.training.tasks.Task;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class Project {
    private ArrayList<Task> tasks;
    private String name;

    public Project(String name) {
        this.name = name;
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        if (!tasks.contains(task)){
            tasks.add(task);
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return tasks.equals(project.tasks) &&
                name.equals(project.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tasks, name);
    }
}
