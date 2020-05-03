package com.codurance.training.tasks.commands;

import com.codurance.training.tasks.model.ProjectsRepository;
import com.codurance.training.tasks.utils.Formatter;
import com.codurance.training.tasks.utils.IO;

public class AddProjectCommand implements Command{

    private String name;

    public AddProjectCommand(String name) {
        this.name = name;
    }

    @Override
    public void run(IO io, Formatter formatter, ProjectsRepository repository) {
        io.write(formatter.show(repository.addProject(name)), true);
    }
}
