package com.codurance.training.tasks;

import com.codurance.training.tasks.utils.ConsoleIO;
import com.codurance.training.tasks.utils.Formatter;

public class TaskApp {
    public static void main(String[] args) throws Exception {
        ConsoleIO io = new ConsoleIO();
        Formatter formatter = new Formatter();
        new TaskList(io, formatter).run();
    }
}
