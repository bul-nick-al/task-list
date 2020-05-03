package com.codurance.training.tasks;

import com.codurance.training.tasks.commands.Command;
import com.codurance.training.tasks.utils.CommandLine;
import com.codurance.training.tasks.utils.Formatter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TaskListSpec {
    private TaskList taskList;
    private TestIO testIO;

    public TaskListSpec() {
        this.testIO = new TestIO();
        this.taskList = new TaskList(testIO, new Formatter());
    }

    @Test public void
    return_task_list_show_command_when_show_command_is_typed() {
        String addProjectCommand = "add project test";
        CommandLine commandLine = new CommandLine(addProjectCommand);
        Command command = commandLine.getCommand();

        taskList.execute(command);

        assertEquals(testIO.getOut(), "Project added");
    }
}