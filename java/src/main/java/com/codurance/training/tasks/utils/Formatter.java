package com.codurance.training.tasks.utils;

import com.codurance.training.tasks.Task;
import com.codurance.training.tasks.model.Project;
import com.codurance.training.tasks.model.ProjectsRepository;

import java.util.ArrayList;
import java.util.HashMap;

public class Formatter {
    private final String CHECKED = "[x]";
    private final String UNCHECKED = "[-]";


    public String showRepository(HashMap<String, Project> projects) {
        StringBuilder result = new StringBuilder();

        for (Project project : projects.values()) {
            result.append(project.getName()).append("\n");
            result.append(showTasks(project.getTasks()));
        }

        return result.toString();
    }

    public String showTasks(ArrayList<Task> tasks) {
        StringBuilder result = new StringBuilder();

        tasks.forEach(task -> {
            result.append("    ").append(task.isDone() ? CHECKED : UNCHECKED)
                    .append(" ").append(task.getId()).append(" ").append(task.getDescription()).append("\n");
        });

        return result.toString();
    }
}
