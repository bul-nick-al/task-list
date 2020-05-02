package com.codurance.training.tasks.utils;


import com.codurance.training.tasks.commands.Command;
import com.codurance.training.tasks.commands.CommandFactory;

import java.util.ArrayList;

public class CommandLine {
    private String rawValue;

    public CommandLine(String rawValue) {
        this.rawValue = rawValue;
    }

    public String getCommandOperator() {
        for (String commandName : Command.commandNames) {
            if (rawValue.startsWith(commandName))
                return commandName;
        }
        return "";
    }

    public String getCommandParameters() {
        for (String commandName : Command.commandNames) {
            if (rawValue.startsWith(commandName))
                return rawValue.replaceFirst(commandName, "").trim();
        }
        return "";
    }

    public Command getCommand() {
        return CommandFactory.getInstance().create(getCommandOperator(),getCommandParameters());
    }
}
