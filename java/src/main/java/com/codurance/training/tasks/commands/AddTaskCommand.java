package com.codurance.training.tasks.commands;

import com.codurance.training.tasks.model.ProjectsRepository;
import com.codurance.training.tasks.utils.Formatter;
import com.codurance.training.tasks.utils.IO;

import java.util.ArrayList;
import java.util.Arrays;

public class AddTaskCommand implements Command {

    private String projectName;
    private String taskDescription;

    public AddTaskCommand(String parameters) {
        parseParameters(parameters);
    }

    @Override
    public void run(IO io, Formatter formatter, ProjectsRepository repository) {
        String result = repository.addTaskToProjects(taskDescription, new ArrayList<String>(Arrays.asList(projectName)));
        io.write(result, true);
    }

    private void parseParameters(String rawParams) {
        String[] parsedParams = rawParams.split(" ");
        projectName = parsedParams[0];
        taskDescription = parsedParams[1];
    }
}
