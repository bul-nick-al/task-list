package com.codurance.training.tasks;

import com.codurance.training.tasks.utils.IO;

public class TestIO implements IO {

    public String getOut() {
        return out;
    }

    private String out;
    private String in;

    @Override
    public String read(){
        return in;
    }

    @Override
    public void write(String string, boolean newLine) {
        this.out = string;;
    }
}
