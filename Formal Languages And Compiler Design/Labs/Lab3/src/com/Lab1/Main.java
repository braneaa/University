package com.Lab1;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        //Initialize a new HashTable
        HashTable hashTable = new HashTable(128);

        MyScanner scanner = new MyScanner("D:\\Facultate\\Anul III_Sem1\\FLCD\\Labs\\Lab2\\src\\data\\token.in",
                "D:\\Facultate\\Anul III_Sem1\\FLCD\\Labs\\Lab2\\src\\data\\p1.txt",
                "D:\\Facultate\\Anul III_Sem1\\FLCD\\Labs\\Lab2\\src\\data\\pif.out",
                "D:\\Facultate\\Anul III_Sem1\\FLCD\\Labs\\Lab2\\src\\data\\st.out");
        scanner.readTokens();
        scanner.scan();
    }




}
