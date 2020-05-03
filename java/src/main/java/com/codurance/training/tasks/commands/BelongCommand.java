package com.codurance.training.tasks.commands;

import com.codurance.training.tasks.model.ProjectsRepository;
import com.codurance.training.tasks.utils.Formatter;
import com.codurance.training.tasks.utils.IO;
import com.codurance.training.tasks.utils.Utils;

public class BelongCommand implements Command {
    private String projectName;
    private Integer taskId;

    public BelongCommand(String parameters) {
        parseParameters(parameters);
    }

    @Override
    public void run(IO io, Formatter formatter, ProjectsRepository repository) {
        io.write(repository.addTaskToProject(taskId, projectName), true);
    }

    private void parseParameters(String parameters) {
        String[] params = parameters.split(" ");

        this.projectName = params[1];
        this.taskId = Utils.tryParseInt(params[0]);
    }
}
