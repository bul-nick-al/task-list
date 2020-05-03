package com.codurance.training.tasks.model;

import com.codurance.training.tasks.observer.Observable;
import com.codurance.training.tasks.observer.Observer;

import java.util.ArrayList;
import java.util.Objects;

public class Project implements Observer {
    private ArrayList<Task> checkedTasks;
    private ArrayList<Task> uncheckedTasks;
    private String name;

    public Project(String name) {
        this.name = name;
        this.checkedTasks = new ArrayList<>();
        this.uncheckedTasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        ArrayList<Task> result = new ArrayList<>(uncheckedTasks);
        result.addAll(checkedTasks);

        return result;
    }

    public void addTask(Task task) {
        if (!uncheckedTasks.contains(task) && !checkedTasks.contains(task)){
            if (task.isDone())
                checkedTasks.add(task);
            else
                uncheckedTasks.add(task);

            task.attach(this);
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
        return Objects.equals(checkedTasks, project.checkedTasks) &&
                Objects.equals(uncheckedTasks, project.uncheckedTasks) &&
                Objects.equals(name, project.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(checkedTasks, uncheckedTasks, name);
    }

    @Override
    public void update(Observable o) {
        Task task = (Task) o;

        if (task.isDone()){
            uncheckedTasks.remove(task);
            checkedTasks.add(task);
            return;
        }

        checkedTasks.remove(task);
        uncheckedTasks.add(task);

        System.out.println();
    }
}
