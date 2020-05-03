package com.codurance.training.tasks.commands;

import com.codurance.training.tasks.model.ProjectsRepository;
import com.codurance.training.tasks.utils.Formatter;
import com.codurance.training.tasks.utils.IO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface Command {

    public void run(IO io, Formatter formatter, ProjectsRepository repository);

    String CMD_QUIT = "quit";
    String CMD_SHOW = "show";
    String CMD_ADD_PROJECT = "add project";
    String CMD_ADD_TASK = "add task";
    String CMD_CHECK = "check";
    String CMD_UNCHECK = "uncheck";
    String CMD_HELP = "help";
    String CMD_DEADLINE = "deadline";
    String CMD_TODAY = "today";
    String CMD_DELETE = "delete";
    String CMD_VIEW_BY_DEADLINE = "view by deadline";
    String CMD_VIEW_BY_DATE = "view by date";
    String CMD_VIEW_BY_PROJECT = "view by project";
    String CMD_BELONG = "belong";

    ArrayList<String> commandNames = new ArrayList<>(Arrays.asList(CMD_ADD_PROJECT,
            CMD_ADD_TASK,
            CMD_CHECK,
            CMD_HELP,
            CMD_QUIT,
            CMD_SHOW,
            CMD_UNCHECK,
            CMD_DEADLINE,
            CMD_TODAY,
            CMD_DELETE,
            CMD_VIEW_BY_DEADLINE,
            CMD_VIEW_BY_DATE,
            CMD_VIEW_BY_PROJECT,
            CMD_BELONG));


}
