package com.codurance.training.tasks.utils;

import java.util.Scanner;

public class ConsoleIO implements IO {
    private Scanner in = new Scanner(System.in);

    @Override
    public String read() {
        return in.nextLine();
    }

    @Override
    public void write(String string, boolean newLine) {
        if (newLine)
            System.out.println(string);
        else
            System.out.print(string);
    }

    public void close() {
        in.close();
    }
}
