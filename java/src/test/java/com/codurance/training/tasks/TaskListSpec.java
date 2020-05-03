package com.codurance.training.tasks;

import com.codurance.training.tasks.commands.Command;
import com.codurance.training.tasks.utils.CommandLine;
import com.codurance.training.tasks.utils.Formatter;
import com.codurance.training.tasks.utils.Utils;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class TaskListSpec {
    private TaskList taskList;
    private TestIO testIO;

    public TaskListSpec() {
        this.testIO = new TestIO();
        this.taskList = new TaskList(testIO, new Formatter());
    }

    @Test public void
    assert_project_added() {
        String addProjectCommand = "add project test";
        CommandLine commandLine = new CommandLine(addProjectCommand);
        Command command = commandLine.getCommand();

        taskList.execute(command);

        assertEquals(testIO.getOut(), "Project added");
    }

    @Test public void
    assert_task_added() {
        String addProjectCommand = "add project test";
        String addTaskCommand = "add task test task one";

        CommandLine commandLineForProject = new CommandLine(addProjectCommand);
        Command command1= commandLineForProject.getCommand();
        taskList.execute(command1);

        CommandLine commandLineForTask = new CommandLine(addTaskCommand);
        Command command2 = commandLineForTask.getCommand();
        taskList.execute(command2);

        assertEquals(testIO.getOut(), "Task added");
    }

    @Test public void
    assert_task_belong() {
        String addProject1Command = "add project test";
        String addProject2Command = "add project second";

        String addTaskCommand = "add task test task one";
        String belongCommand = "belong 0 second";

        CommandLine commandLineForProject = new CommandLine(addProject1Command);
        Command command1= commandLineForProject.getCommand();
        taskList.execute(command1);

        CommandLine commandLineForTask = new CommandLine(addTaskCommand);
        Command command2 = commandLineForTask.getCommand();
        taskList.execute(command2);

        CommandLine commandLineForProject2 = new CommandLine(addProject2Command);
        Command command3 = commandLineForProject2.getCommand();
        taskList.execute(command3);

        CommandLine commandLineForBelong = new CommandLine(belongCommand);
        Command command4 = commandLineForBelong.getCommand();
        taskList.execute(command4);

        assertEquals(testIO.getOut(), "Task added to project");
    }

    @Test public void
    assert_task_checked_unchecked() {
        String addProject1Command = "add project test";
        String addTaskCommand = "add task test task one";
        String checkTask = "check 0";
        String uncheckTask = "uncheck 0";

        CommandLine commandLineForProject = new CommandLine(addProject1Command);
        Command command1= commandLineForProject.getCommand();
        taskList.execute(command1);

        CommandLine commandLineForTask = new CommandLine(addTaskCommand);
        Command command2 = commandLineForTask.getCommand();
        taskList.execute(command2);

        CommandLine commandLineForCheck = new CommandLine(checkTask);
        Command command3 = commandLineForCheck.getCommand();
        taskList.execute(command3);

        assertEquals(testIO.getOut(), "Task checked.");

        CommandLine commandLineForUncheck = new CommandLine(uncheckTask);
        Command command4 = commandLineForUncheck.getCommand();
        taskList.execute(command4);

        assertEquals(testIO.getOut(), "Task unchecked.");
    }

    @Test public void
    assert_task_deadline_set() {
        String addProject1Command = "add project test";
        String addTaskCommand = "add task test task one";
        String addTaskDeadline = "deadline 0 2020-05-07";

        CommandLine commandLineForProject = new CommandLine(addProject1Command);
        Command command1= commandLineForProject.getCommand();
        taskList.execute(command1);

        CommandLine commandLineForTask = new CommandLine(addTaskCommand);
        Command command2 = commandLineForTask.getCommand();
        taskList.execute(command2);

        CommandLine commandLineForDeadline = new CommandLine(addTaskDeadline);
        Command command3 = commandLineForDeadline.getCommand();
        taskList.execute(command3);

        assertEquals(testIO.getOut(), "Deadline set.");
    }

    @Test public void
    assert_task_deleted() {
        String addProject1Command = "add project test";
        String addTaskCommand = "add task test task one";
        String addTaskDelete = "delete 0";

        CommandLine commandLineForProject = new CommandLine(addProject1Command);
        Command command1= commandLineForProject.getCommand();
        taskList.execute(command1);

        CommandLine commandLineForTask = new CommandLine(addTaskCommand);
        Command command2 = commandLineForTask.getCommand();
        taskList.execute(command2);

        CommandLine commandLineForDelete = new CommandLine(addTaskDelete);
        Command command3 = commandLineForDelete.getCommand();
        taskList.execute(command3);

        assertEquals(testIO.getOut(), "Task removed");
    }

    @Test public void
    assert_help_printed() {
        CommandLine commandLineForHelp = new CommandLine("help");
        Command command1= commandLineForHelp.getCommand();
        taskList.execute(command1);

        assertEquals(testIO.getOut(), "Commands:\n" +
                "  show\n" +
                "  add project <project name>\n" +
                "  add task <project name> <task description>\n" +
                "  check <task ID>\n" +
                "  uncheck <task ID>\n" +
                "  deadline <task ID> <date in format YYYY-MM-DD>\n" +
                "  delete <task ID>\n" +
                "  view by date <date in format YYYY-MM-DD>\n" +
                "  view by deadline <date in format YYYY-MM-DD>\n" +
                "  view by project <project name>\n" +
                "  belong <task ID> <project name>\n");
    }

    @Test public void
    assert_show_printed() {
        String addProject1Command = "add project test";
        String addTaskCommand1 = "add task test task one";
        String addTaskCommand2 = "add task test task two";
        String checkCommand = "check 0";
        String showCommand = "show";

        CommandLine commandLineForProject = new CommandLine(addProject1Command);
        Command command1= commandLineForProject.getCommand();
        taskList.execute(command1);

        CommandLine commandLineForTask = new CommandLine(addTaskCommand1);
        Command command2 = commandLineForTask.getCommand();
        taskList.execute(command2);

        CommandLine commandLineForTask2 = new CommandLine(addTaskCommand2);
        Command command3 = commandLineForTask2.getCommand();
        taskList.execute(command3);

        CommandLine commandLineForCheck = new CommandLine(checkCommand);
        Command command4 = commandLineForCheck.getCommand();
        taskList.execute(command4);

        CommandLine commandLineForShow = new CommandLine(showCommand);
        Command command5 = commandLineForShow.getCommand();
        taskList.execute(command5);

        assertEquals(testIO.getOut(), "test\n" +
                "    [x] 0 task created " + Utils.dateToString(new Date()) + " \n");
    }

    @Test public void
    assert_today_tasks_printed() {
        String addProject1Command = "add project test";
        String addTaskCommand = "add task test task1";
        String addTaskDeadline = "deadline 0 2020-05-07";
        String addTaskCommand2 = "add task test task2";
        String addTaskDeadline2 = "deadline 1 " + Utils.dateToString(new Date());
        String today = "today";

        CommandLine commandLineForProject = new CommandLine(addProject1Command);
        Command command1= commandLineForProject.getCommand();
        taskList.execute(command1);

        CommandLine commandLineForTask = new CommandLine(addTaskCommand);
        Command command2 = commandLineForTask.getCommand();
        taskList.execute(command2);

        CommandLine commandLineForDeadline = new CommandLine(addTaskDeadline);
        Command command3 = commandLineForDeadline.getCommand();
        taskList.execute(command3);

        CommandLine commandLineForTask2 = new CommandLine(addTaskCommand2);
        Command command4 = commandLineForTask2.getCommand();
        taskList.execute(command4);

        CommandLine commandLineForDeadline3 = new CommandLine(addTaskDeadline2);
        Command command5 = commandLineForDeadline3.getCommand();
        taskList.execute(command5);

        CommandLine commandLineForToday= new CommandLine(today);
        Command command6 = commandLineForToday.getCommand();
        taskList.execute(command6);

        assertEquals(testIO.getOut(), "[-] 1 task2 created " + Utils.dateToString(new Date()) + " due 2020-05-03\n");
    }

    @Test public void
    assert_uncorrect_command_handled() {
        String command = "gibberish";

        CommandLine commandLine = new CommandLine(command);
        Command command1 = commandLine.getCommand();
        taskList.execute(command1);

        assertEquals(testIO.getOut(), "The command is incorrect. Type \"help\" to see the list of available commands.");
    }

    @Test public void
    assert_view_by_date() {
        String addProject1Command = "add project test";
        String addTaskCommand = "add task test task one";
        String addTaskCommand2 = "add task test task two";
        String viewByDate = "view by date " + Utils.dateToString(new Date());

        CommandLine commandLineForProject = new CommandLine(addProject1Command);
        Command command1= commandLineForProject.getCommand();
        taskList.execute(command1);

        CommandLine commandLineForTask = new CommandLine(addTaskCommand);
        Command command2 = commandLineForTask.getCommand();
        taskList.execute(command2);

        CommandLine commandLineForTask2 = new CommandLine(addTaskCommand2);
        Command command4 = commandLineForTask2.getCommand();
        taskList.execute(command4);

        CommandLine commandLineForToday = new CommandLine(viewByDate);
        Command command6 = commandLineForToday.getCommand();
        taskList.execute(command6);

        assertEquals(testIO.getOut(), "[-] 0 task created " + Utils.dateToString(new Date()) + " \n");
    }

    @Test public void
    assert_view_by_deadline() {
        String addProject1Command = "add project test";
        String addTaskCommand = "add task test task one";
        String addTaskDeadline = "deadline 0 2020-05-07";
        String addTaskCommand2 = "add task test task two";
        String addTaskDeadline2 = "deadline 1 " + Utils.dateToString(new Date());
        String today = "view by deadline 2020-05-07";

        CommandLine commandLineForProject = new CommandLine(addProject1Command);
        Command command1= commandLineForProject.getCommand();
        taskList.execute(command1);

        CommandLine commandLineForTask = new CommandLine(addTaskCommand);
        Command command2 = commandLineForTask.getCommand();
        taskList.execute(command2);

        CommandLine commandLineForDeadline = new CommandLine(addTaskDeadline);
        Command command3 = commandLineForDeadline.getCommand();
        taskList.execute(command3);

        CommandLine commandLineForTask2 = new CommandLine(addTaskCommand2);
        Command command4 = commandLineForTask2.getCommand();
        taskList.execute(command4);

        CommandLine commandLineForDeadline3 = new CommandLine(addTaskDeadline2);
        Command command5 = commandLineForDeadline3.getCommand();
        taskList.execute(command5);

        CommandLine commandLineForToday= new CommandLine(today);
        Command command6 = commandLineForToday.getCommand();
        taskList.execute(command6);

        assertEquals(testIO.getOut(), "[-] 0 task created " + Utils.dateToString(new Date()) + " due 2020-05-07\n");
    }

    @Test public void
    assert_view_by_project() {
        String addProject1Command = "add project test";
        String addTaskCommand = "add task test task1";
        String addTaskDeadline = "add project other";
        String addTaskCommand2 = "add task test task2";
        String addTaskDeadline2 = "add task other task3";
        String today = "view by project test";

        CommandLine commandLineForProject = new CommandLine(addProject1Command);
        Command command1= commandLineForProject.getCommand();
        taskList.execute(command1);

        CommandLine commandLineForTask = new CommandLine(addTaskCommand);
        Command command2 = commandLineForTask.getCommand();
        taskList.execute(command2);

        CommandLine commandLineForDeadline = new CommandLine(addTaskDeadline);
        Command command3 = commandLineForDeadline.getCommand();
        taskList.execute(command3);

        CommandLine commandLineForTask2 = new CommandLine(addTaskCommand2);
        Command command4 = commandLineForTask2.getCommand();
        taskList.execute(command4);

        CommandLine commandLineForDeadline3 = new CommandLine(addTaskDeadline2);
        Command command5 = commandLineForDeadline3.getCommand();
        taskList.execute(command5);

        CommandLine commandLineForToday= new CommandLine(today);
        Command command6 = commandLineForToday.getCommand();
        taskList.execute(command6);

        assertEquals(testIO.getOut(), "[-] 0 task1 created " + Utils.dateToString(new Date()) + " \n" +
                "[-] 1 task2 created " + Utils.dateToString(new Date()) + " \n");
    }
}