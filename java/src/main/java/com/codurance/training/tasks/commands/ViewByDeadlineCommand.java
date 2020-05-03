package com.codurance.training.tasks.commands;

import com.codurance.training.tasks.model.ProjectsRepository;
import com.codurance.training.tasks.utils.Formatter;
import com.codurance.training.tasks.utils.IO;
import com.codurance.training.tasks.utils.Utils;

import java.util.Date;

public class ViewByDeadlineCommand implements Command {

    private Date deadline;

    public ViewByDeadlineCommand(String parameters) {
        parseParameters(parameters);
    }

    private void parseParameters(String parameters) {
        deadline = Utils.tryParseDate(parameters);
    }

    @Override
    public void run(IO io, Formatter formatter, ProjectsRepository repository) {
        if (deadline == null) {
            io.write("Date should be in format YYYY-MM-DD.", true);
            return;
        }

        io.write(formatter.showTasks(repository.getTasksForDeadline(deadline)), true);
    }
}
