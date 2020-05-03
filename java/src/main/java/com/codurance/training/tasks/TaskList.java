package com.codurance.training.tasks;

import com.codurance.training.tasks.commands.Command;
import com.codurance.training.tasks.model.ProjectsRepository;
import com.codurance.training.tasks.utils.CommandLine;
import com.codurance.training.tasks.utils.Formatter;
import com.codurance.training.tasks.utils.IO;

import java.util.*;

public final class TaskList implements Runnable {
    private static final String QUIT = "quit";

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

    protected void execute(Command command) {
        command.run(io, formatter, repository);
    }
}
