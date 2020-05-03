package com.codurance.training.tasks.commands;

public class CommandFactory {
    private static CommandFactory instance = new CommandFactory();

    private CommandFactory(){}

    public static CommandFactory getInstance(){
        return instance;
    }

    public Command create(String commandName, String parametersString) {
        switch (commandName) {
            case Command.CMD_SHOW:
                return new ShowCommand();
            case Command.CMD_ADD_TASK:
                return new AddTaskCommand(parametersString);
            case Command.CMD_ADD_PROJECT:
                return new AddProjectCommand(parametersString);
            case Command.CMD_UNCHECK:
                return new UncheckCommand(parametersString);
            case Command.CMD_CHECK:
                return new CheckCommand(parametersString);
            case Command.CMD_HELP:
                return new HelpCommand();
            case Command.CMD_DEADLINE:
                return new DeadlineCommand(parametersString);
            case Command.CMD_TODAY:
                return new TodayCommand();
            case Command.CMD_DELETE:
                return new DeleteCommand(parametersString);
            case Command.CMD_VIEW_BY_DEADLINE:
                return new ViewByDeadlineCommand(parametersString);
            case Command.CMD_VIEW_BY_DATE:
                return new ViewByDateCommand(parametersString);
            case Command.CMD_VIEW_BY_PROJECT:
                return new ViewByProjectCommand(parametersString);
            case Command.CMD_BELONG:
                return new BelongCommand(parametersString);
            default:
                return new UnknownCommand();
        }
    }

}
