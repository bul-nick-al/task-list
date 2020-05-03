package com.codurance.training.tasks;

import com.codurance.training.tasks.commands.*;
import com.codurance.training.tasks.utils.CommandLine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class CommandLineParserShould {
    private static final String DUMMY_STRING = "any";

    private final String commandName;
    private final Command expectedCommand;

    @Parameters(name="The parser returns a command of type {1} for the command-line command {0}.")
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[][] {
                        {"show", new ShowCommand()},
                        {"add project " + DUMMY_STRING, new AddProjectCommand(DUMMY_STRING)},
                        {"add task " + DUMMY_STRING + " " + DUMMY_STRING, new AddTaskCommand(DUMMY_STRING)},
                        {"check " + DUMMY_STRING, new CheckCommand(DUMMY_STRING)},
                        {"uncheck " + DUMMY_STRING, new UncheckCommand(DUMMY_STRING)},
                        {"help " + DUMMY_STRING, new HelpCommand()},
                        {"unknowncommand ", new UnknownCommand()},
                        {"deadline " + DUMMY_STRING, new DeadlineCommand(DUMMY_STRING)},
                        {"delete " + DUMMY_STRING, new DeleteCommand(DUMMY_STRING)},
                        {"view by date " + DUMMY_STRING, new ViewByDateCommand(DUMMY_STRING)},
                        {"view by deadline " + DUMMY_STRING, new ViewByDeadlineCommand(DUMMY_STRING)},
                        {"view by project " + DUMMY_STRING, new ViewByProjectCommand(DUMMY_STRING)},
                        {"belong " + DUMMY_STRING, new BelongCommand(DUMMY_STRING)},
                }
        );
    }

    public CommandLineParserShould(String commandName, Command expectedeCommand) {
        this.commandName = commandName;
        this.expectedCommand = expectedeCommand;
    }

    @Test public void
    return_task_list_show_command_when_show_command_is_typed() {
        CommandLine commandLine = new CommandLine(commandName);
        Command command = commandLine.getCommand();

        assertThat(command, instanceOf(expectedCommand.getClass()));
    }
}
