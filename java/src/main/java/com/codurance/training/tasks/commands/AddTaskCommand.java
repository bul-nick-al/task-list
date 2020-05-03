package com.codurance.training.tasks.commands;

import com.codurance.training.tasks.model.ProjectsRepository;
import com.codurance.training.tasks.utils.Formatter;
import com.codurance.training.tasks.utils.IO;

public class AddTaskCommand implements Command {

    private String projectName;
    private String taskDescription;

    public AddTaskCommand(String parameters) {
        parseParameters(parameters);
    }

    @Override
    public void run(IO io, Formatter formatter, ProjectsRepository repository) {
        if (projectName != null && taskDescription != null) {
            io.write(formatter.show(repository.addTaskToProject(taskDescription, projectName)), true);
            return;
        }

        io.write("Not enough parameters", true);

    }

    private void parseParameters(String rawParams) {
        String[] parsedParams = rawParams.split(" ");
        if (parsedParams.length < 2)
            return;
        projectName = parsedParams[0];
        taskDescription = parsedParams[1];
    }
}
