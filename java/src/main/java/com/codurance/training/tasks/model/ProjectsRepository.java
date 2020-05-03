package com.codurance.training.tasks.model;

import com.codurance.training.tasks.utils.Utils;

import java.util.ArrayList;
import java.util.Date;
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

    public String addTaskToProject(String taskDescription, String projectName) {
        for (Task task : tasks) {
            if (task.getDescription().equals(taskDescription)){
                return "The task already exists";
            }
        }

        Task newTask = new Task(getNextTaskId(), taskDescription, false);

        StringBuilder result = new StringBuilder();

        boolean added = false;

        if (!this.projects.containsKey(projectName))
            result.append("Project ").append(projectName).append(" doesn't exist. Skipping... \n");
        else {
            this.projects.get(projectName).addTask(newTask);
            added = true;
        }


        if (added) {
            this.tasks.add(newTask);
            result.append("Task added");
        }

        return result.toString();
    }

    public String addTaskToProject(Integer taskId, String projectName) {
        for (Task task : tasks) {
            if (task.getId() == taskId){
                if (!this.projects.containsKey(projectName))
                    return ("Project doesn't exist.");
                else {
                    this.projects.get(projectName).addTask(task);
                    return "Task added to project";
                }
            }
        }

        return "No task with provided id found";
    }

    public String setChecked(int taskId, boolean isChecked) {
        for (Task task : tasks) {
            if (task.getId() == taskId) {
                task.setDone(isChecked);
                return "Task " + (isChecked ? "checked." : "unchecked.");
            }
        }

        return "No task with provided id found";
    }

    public String setDeadline(int taskId, Date deadline) {
        for (Task task : tasks) {
            if (task.getId() == taskId) {
                task.setDeadline(deadline);
                return "Deadline set.";
            }
        }

        return "No task with provided id found";
    }

    public ArrayList<Task> getTasksForDeadline(Date date) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (Utils.isSameDay(task.getDeadline(), date))
                result.add(task);
        }
        return result;
    }

    public ArrayList<Task> getTasksForDate(Date date) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (Utils.isSameDay(task.getDate(), date))
                result.add(task);
        }
        return result;
    }

    public String deleteTask(int taskId) {
        for (Task task : tasks) {
            if (task.getId() == taskId) {
                for (Project project : projects.values()) {
                    deleteTask(task, project);
                }
                tasks.remove(task);
                return "Task removed";
            }
        }
        return "No task found with this id";
    }

    public void deleteTask(Task task, Project project) {
        project.getTasks().remove(task);
    }

    public ArrayList<Task> getTasksForProject(String projectName) {
        if (this.projects.containsKey(projectName)) {
            return projects.get(projectName).getTasks();
        }

        return new ArrayList<>();
    }


    public int getNextTaskId() {
        return this.tasks.size();
    }
}
