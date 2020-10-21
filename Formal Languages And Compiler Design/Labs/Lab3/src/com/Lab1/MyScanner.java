package com.Lab1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class MyScanner {

    private File tokenFile;
    private File program;
    private File pif;
    private File st;
    private List<String> tokens = new ArrayList<>();

    MyScanner(String tokenFile, String program, String pif, String st) throws IOException {
        this.tokenFile = new File(tokenFile);
        this.program = new File(program);
        this.pif = new File(pif);
        this.st = new File(st);
        readTokens();
    }


    void readTokens() throws IOException {
        FileReader fileReader = new FileReader(this.tokenFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String symbol;
        while ((symbol = bufferedReader.readLine()) != null)
            tokens.add(symbol);
    }

    void scan() throws IOException {

        HashTable pif = new HashTable(128);
        HashTable st = new HashTable(128);

        BufferedReader bufferedReader = new BufferedReader(new FileReader(program));
        StringBuilder stringBuilder = new StringBuilder();
        String line = bufferedReader.readLine();
        while (line!=null){
            stringBuilder.append(line);
            stringBuilder.append("\n");
            line = bufferedReader.readLine();
        }

        StringTokenizer stringTokenizer = new StringTokenizer(stringBuilder.toString(), " .,;()[]\n");

        while (stringTokenizer.hasMoreTokens()){
            String token = stringTokenizer.nextToken();
            pif.put(token);
            boolean isInST = true;
            for (String s : tokens) {
                if (token.equals(s)) {
                    isInST = false;
                    break;
                }
            }
            if (isInST) st.put(token);
        }

        for (char ch : stringBuilder.toString().toCharArray()){
            if(ch == '.' || ch == '(' || ch == ')' || ch == ',' ||ch == ';' || ch == '[' || ch == ']')
                pif.put(String.valueOf(ch));
        }

        FileWriter pifWriter = new FileWriter(this.pif);
        pifWriter.write(pif.toString());
        pifWriter.close();

        FileWriter stWriter = new FileWriter(this.st);
        stWriter.write(st.toString());
        stWriter.close();
    }


}


