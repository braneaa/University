package com.Lab1;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception {

        MyScanner scanner = new MyScanner("D:\\Facultate\\Anul III_Sem1\\FLCD\\Labs\\Lab3\\src\\data\\token.in",
                "D:\\Facultate\\Anul III_Sem1\\FLCD\\Labs\\Lab3\\src\\data\\p3.txt",
                "D:\\Facultate\\Anul III_Sem1\\FLCD\\Labs\\Lab3\\src\\data\\pif.out",
                "D:\\Facultate\\Anul III_Sem1\\FLCD\\Labs\\Lab3\\src\\data\\st.out");
        scanner.scan();
        System.out.println("LEXICALLY CORRECT!");
    }
}
