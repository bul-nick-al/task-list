package com.codurance.training.tasks.commands;

import com.codurance.training.tasks.model.ProjectsRepository;
import com.codurance.training.tasks.utils.Formatter;
import com.codurance.training.tasks.utils.IO;
import com.codurance.training.tasks.utils.Utils;

import java.util.Date;

public class DeadlineCommand implements Command{
    private Integer taskId;
    private Date date;

    public DeadlineCommand(String parameters) {
        parseParameters(parameters);
    }

    @Override
    public void run(IO io, Formatter formatter, ProjectsRepository repository) {
        if (taskId == null || date == null){
            io.write("Incorrect parameters. Id should be an integer number, date should be in format YYYY-MM-DD.", true);
            return;
        }

        io.write(repository.setDeadline(taskId, date), true);
    }

    private void parseParameters(String parameters) {
        String[] splitParameters = parameters.split(" ");

        taskId = Utils.tryParseInt(splitParameters[0]);
        date = Utils.tryParseDate(splitParameters[1]);
    }
}
