package com.codurance.training.tasks.commands;

import com.codurance.training.tasks.model.ProjectsRepository;
import com.codurance.training.tasks.utils.Formatter;
import com.codurance.training.tasks.utils.IO;

public class HelpCommand implements Command {
    @Override
    public void run(IO io, Formatter formatter, ProjectsRepository repository) {
        io.write(formatter.showHelp(), false);
    }
}
