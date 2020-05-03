package com.codurance.training.tasks.commands;

import com.codurance.training.tasks.model.ProjectsRepository;
import com.codurance.training.tasks.utils.Formatter;
import com.codurance.training.tasks.utils.IO;
import com.codurance.training.tasks.utils.Utils;

import java.util.Date;

public class ViewByDateCommand implements Command {

    private Date date;

    public ViewByDateCommand(String parameters) {
        parseParameters(parameters);
    }

    private void parseParameters(String parameters) {
        date = Utils.tryParseDate(parameters);
    }

    @Override
    public void run(IO io, Formatter formatter, ProjectsRepository repository) {
        if (date == null) {
            io.write("Date should be in format YYYY-MM-DD.", true);
            return;
        }

        io.write(formatter.showTasks(repository.getTasksForDate(date)), true);
    }
}