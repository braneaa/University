package com.Lab1;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        Program program = new Program(new File("data/FA.in"));
        program.run();
    }
}
