package com.codurance.training.tasks.commands;

public class CommandFactory {
    private static CommandFactory instance = new CommandFactory();

    private CommandFactory(){}

    public static CommandFactory getInstance(){
        return instance;
    }

    public Command create(String commandName, String parametersString) {
        switch (commandName) {
            case "show":
                return new ShowCommand();
            case "add task":
                return new AddTaskCommand(parametersString);
            case "add project":
                return new AddProjectCommand(parametersString);
//            case "uncheck":
//                return new TaskListUnCheckExecutableCommand(projectsToTasks);
//            case "check":
//                return new TaskListCheckExecutableCommand(projectsToTasks);
//            case "help":
//                return new HelpExecutableCommand(screen);
            default:
//                return new UnknownCommand(screen);
                return null;
        }
    }

}
