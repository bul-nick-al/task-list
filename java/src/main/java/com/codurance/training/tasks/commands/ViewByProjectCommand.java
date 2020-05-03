package com.codurance.training.tasks.commands;

import com.codurance.training.tasks.model.ProjectsRepository;
import com.codurance.training.tasks.utils.Formatter;
import com.codurance.training.tasks.utils.IO;

public class ViewByProjectCommand implements Command {
    private String project;

    public ViewByProjectCommand(String project) {
        this.project = project.trim();
    }

    @Override
    public void run(IO io, Formatter formatter, ProjectsRepository repository) {
        io.write(formatter.showTasks(repository.getTasksForProject(project)), true);
    }
}
