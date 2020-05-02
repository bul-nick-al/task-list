package com.codurance.training.tasks;

import com.codurance.training.tasks.commands.Command;
import com.codurance.training.tasks.model.ProjectsRepository;
import com.codurance.training.tasks.utils.CommandLine;
import com.codurance.training.tasks.utils.Formatter;
import com.codurance.training.tasks.utils.IO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public final class TaskList implements Runnable {
    private static final String QUIT = "quit";

    private final Map<String, List<Task>> tasks = new LinkedHashMap<>();
    private IO io;
    private Formatter formatter;
    private ProjectsRepository repository;

    private long lastId = 0;

    public TaskList(IO io, Formatter formatter) {
        this.io = io;
        this.formatter = formatter;
        this.repository = new ProjectsRepository();
    }

    public void run() {
        while (true) {
            String input;
            Command command;

            io.write("> ", false);

            try {
                input = io.read();
                CommandLine commandLine = new CommandLine(input);
                command = commandLine.getCommand();
            } catch (NoSuchElementException e) {
                throw new RuntimeException(e);
            }

            if (input.equals(QUIT)) {
                break;
            }

            execute(command);
        }
    }

    private void execute(Command command) {
        command.run(io, formatter, repository);
    }

//    private void show() {
//        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
//            out.println(project.getKey());
//            for (Task task : project.getValue()) {
//                out.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
//            }
//            out.println();
//        }
//    }
//
//    private void add(String commandLine) {
//        String[] subcommandRest = commandLine.split(" ", 2);
//        String subcommand = subcommandRest[0];
//        if (subcommand.equals("project")) {
//            addProject(subcommandRest[1]);
//        } else if (subcommand.equals("task")) {
//            String[] projectTask = subcommandRest[1].split(" ", 2);
//            addTask(projectTask[0], projectTask[1]);
//        }
//    }
//
//    private void addProject(String name) {
//        tasks.put(name, new ArrayList<Task>());
//    }
//
//    private void addTask(String project, String description) {
//        List<Task> projectTasks = tasks.get(project);
//        if (projectTasks == null) {
//            out.printf("Could not find a project with the name \"%s\".", project);
//            out.println();
//            return;
//        }
//        projectTasks.add(new Task(nextId(), description, false));
//    }
//
//    private void check(String idString) {
//        setDone(idString, true);
//    }
//
//    private void uncheck(String idString) {
//        setDone(idString, false);
//    }
//
//    private void setDone(String idString, boolean done) {
//        int id = Integer.parseInt(idString);
//        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
//            for (Task task : project.getValue()) {
//                if (task.getId() == id) {
//                    task.setDone(done);
//                    return;
//                }
//            }
//        }
//        out.printf("Could not find a task with an ID of %d.", id);
//        out.println();
//    }
//
//    private void help() {
//        out.println("Commands:");
//        out.println("  show");
//        out.println("  add project <project name>");
//        out.println("  add task <project name> <task description>");
//        out.println("  check <task ID>");
//        out.println("  uncheck <task ID>");
//        out.println();
//    }
//
//    private void error(String command) {
//        out.printf("I don't know what the command \"%s\" is.", command);
//        out.println();
//    }
//
//    private long nextId() {
//        return ++lastId;
//    }
}
