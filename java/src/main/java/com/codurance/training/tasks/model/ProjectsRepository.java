package com.codurance.training.tasks.model;

import com.codurance.training.tasks.utils.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ProjectsRepository {

    // All projects present in the system mapped with their names
    private HashMap<String, Project> projects;

    // all tasks present in the system
    private ArrayList<Task> tasks;

    public ProjectsRepository() {
        this.projects = new HashMap<>();
        this.tasks = new ArrayList<>();
    }

    public enum AddProjectResult {
        PROJECT_ADDED,
        PROJECT_EXISTS
    }

    public enum AddTaskResult {
        TASK_ADDED,
        TASK_EXISTS,
        PROJECT_NOT_EXISTS,
        NO_TASK_FOUND
    }

    public enum SetCheckedResult {
        CHECKED,
        UNCHECKED,
        NO_TASK_FOUND
    }

    public enum SetDeadlineResult {
        DEADLINE_SET,
        NO_TASK_FOUND
    }

    public enum DeleteTaskResult {
        DELETED,
        NO_TASK_FOUND
    }

    public HashMap<String, Project> getProjects() {
        return projects;
    }

    public AddProjectResult addProject(String projectName) {
        if (projects.containsKey(projectName))
            return AddProjectResult.PROJECT_EXISTS;

        Project newProject = new Project(projectName);
        projects.put(projectName, newProject);

        return AddProjectResult.PROJECT_ADDED;
    }

    public Project getProject(String name) {
        if (projects.containsKey(name))
            return projects.get(name);
        return null;
    }

    public AddTaskResult addTaskToProject(String taskDescription, String projectName) {
        for (Task task : tasks) {
            if (task.getDescription().equals(taskDescription)){
                return AddTaskResult.TASK_EXISTS;
            }
        }

        Task newTask = new Task(getNextTaskId(), taskDescription, false);

        if (!this.projects.containsKey(projectName))
            return AddTaskResult.PROJECT_NOT_EXISTS;
        else {
            this.projects.get(projectName).addTask(newTask);
            this.tasks.add(newTask);
        }


        return AddTaskResult.TASK_ADDED;
    }

    public AddTaskResult addTaskToProject(Integer taskId, String projectName) {
        for (Task task : tasks) {
            if (task.getId() == taskId){
                if (!this.projects.containsKey(projectName))
                    return AddTaskResult.PROJECT_NOT_EXISTS;
                else {
                    this.projects.get(projectName).addTask(task);
                    return AddTaskResult.TASK_ADDED;
                }
            }
        }

        return AddTaskResult.NO_TASK_FOUND;
    }

    public SetCheckedResult setChecked(int taskId, boolean isChecked) {
        for (Task task : tasks) {
            if (task.getId() == taskId) {
                task.setDone(isChecked);
                return isChecked ? SetCheckedResult.CHECKED : SetCheckedResult.UNCHECKED;
            }
        }

        return SetCheckedResult.NO_TASK_FOUND;
    }

    public SetDeadlineResult setDeadline(int taskId, Date deadline) {
        for (Task task : tasks) {
            if (task.getId() == taskId) {
                task.setDeadline(deadline);
                return SetDeadlineResult.DEADLINE_SET;
            }
        }

        return SetDeadlineResult.NO_TASK_FOUND;
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

    public DeleteTaskResult deleteTask(int taskId) {
        for (Task task : tasks) {
            if (task.getId() == taskId) {
                for (Project project : projects.values()) {
                    deleteTask(task, project);
                }
                tasks.remove(task);
                return DeleteTaskResult.DELETED;
            }
        }
        return DeleteTaskResult.NO_TASK_FOUND;
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
