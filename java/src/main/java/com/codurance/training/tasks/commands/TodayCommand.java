package com.codurance.training.tasks.commands;

import com.codurance.training.tasks.model.ProjectsRepository;
import com.codurance.training.tasks.utils.Formatter;
import com.codurance.training.tasks.utils.IO;

import java.util.Date;

public class TodayCommand implements Command{
    @Override
    public void run(IO io, Formatter formatter, ProjectsRepository repository) {
        io.write(formatter.showTasks(repository.getTasksForDeadline(new Date())), true);
    }
}
