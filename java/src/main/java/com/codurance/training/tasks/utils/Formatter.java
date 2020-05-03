package com.codurance.training.tasks.utils;

import com.codurance.training.tasks.model.Task;
import com.codurance.training.tasks.model.Project;

import java.util.ArrayList;
import java.util.HashMap;

public class Formatter {
    private final String CHECKED = "[x]";
    private final String UNCHECKED = "[-]";


    public String showRepository(HashMap<String, Project> projects) {
        StringBuilder result = new StringBuilder();

        for (Project project : projects.values()) {
            result.append(project.getName()).append("\n");
            result.append(showTasks(project.getTasks(), "    "));
        }

        return result.toString();
    }

    public String showTasks(ArrayList<Task> tasks, String shift) {
        if (tasks.isEmpty())
            return shift + "No tasks";
        StringBuilder result = new StringBuilder();

        tasks.forEach(task -> {
            result.append(shift)
                    .append(task.isDone() ? CHECKED : UNCHECKED)
                    .append(" ")
                    .append(task.getId())
                    .append(" ")
                    .append(task.getDescription())
                    .append(" ")
                    .append(task.getDate() == null ? "" : "created " + Utils.dateToString(task.getDate()))
                    .append(" ")
                    .append(task.getDeadline() == null ? "" : "due " + Utils.dateToString(task.getDeadline()))
                    .append("\n");
        });

        return result.toString();
    }

    public String showTasks(ArrayList<Task> tasks) {
        return showTasks(tasks, "");
    }

    public String showHelp() {

        String result = "Commands:\n" +
                "  show\n" +
                "  add project <project name>\n" +
                "  add task <project name> <task description>\n" +
                "  check <task ID>\n" +
                "  uncheck <task ID>\n" +
                "  deadline <task ID> <date in format YYYY-MM-DD>\n" +
                "  delete <task ID>\n" +
                "  view by date <date in format YYYY-MM-DD>\n" +
                "  view by deadline <date in format YYYY-MM-DD>\n" +
                "  view by project <project name>\n" +
                "  belong <task ID> <project name>\n";
        return result;
    }
}
