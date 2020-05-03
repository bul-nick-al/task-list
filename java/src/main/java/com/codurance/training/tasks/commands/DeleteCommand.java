package com.codurance.training.tasks.commands;

import com.codurance.training.tasks.model.ProjectsRepository;
import com.codurance.training.tasks.utils.Formatter;
import com.codurance.training.tasks.utils.IO;
import com.codurance.training.tasks.utils.Utils;

public class DeleteCommand implements Command {
    private Integer taskId;

    public DeleteCommand(String parameter) {
        parseParameters(parameter);
    }

    @Override
    public void run(IO io, Formatter formatter, ProjectsRepository repository) {
        if (taskId == null) {
            io.write("The provided task id must be an integer number.", true);
            return;
        }
        io.write(repository.deleteTask(taskId), true);
    }

    private void parseParameters(String rawParams) {
        taskId = Utils.tryParseInt(rawParams);
    }
}
