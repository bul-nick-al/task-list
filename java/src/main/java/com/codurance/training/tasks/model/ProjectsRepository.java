package com.codurance.training.tasks.model;

import com.codurance.training.tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class ProjectsRepository {
    private HashMap<String, Project> projects;
    private ArrayList<Task> tasks;

    public ProjectsRepository() {
        this.projects = new HashMap<>();
        this.tasks = new ArrayList<>();
    }

    public HashMap<String, Project> getProjects() {
        return projects;
    }

    public String addProject(String projectName) {
        if (projects.containsKey(projectName))
            return "Project already exists";

        Project newProject = new Project(projectName);
        projects.put(projectName, newProject);

        return "Project added";
    }

    public Project getProject(String name) {
        if (projects.containsKey(name))
            return projects.get(name);
        return null;
    }

    public String addTaskToProjects(String taskDescription, ArrayList<String> projects) {
        for (Task task : tasks) {
            if (task.getDescription().equals(taskDescription)){
                return "The task already exists";
            }
        }

        Task newTask = new Task(getNextTaskId(), taskDescription, false);

        StringBuilder result = new StringBuilder();

        boolean added = false;
        for (String projectName : projects) {
            if (!this.projects.containsKey(projectName))
                result.append("Project ").append(projectName).append(" doesn't exist. Skipping... \n");
            else {
                this.projects.get(projectName).addTask(newTask);
                added = true;
            }

        }

        if (added)
            this.tasks.add(newTask);
        result.append("Task added");
        return result.toString();
    }

    public Long getNextTaskId() {
        return (long) this.tasks.size();
    }
}
