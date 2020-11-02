package com.Lab1;

import java.io.*;
import java.util.*;

class MyScanner {

    private File tokenFile;
    private File program;
    private File pif;
    private File st;
    private List<String> tokens = new ArrayList<>();


    /**
     * Constructor
     */
    MyScanner(String tokenFile, String program, String pif, String st) throws IOException {
        this.tokenFile = new File(tokenFile);
        this.program = new File(program);
        this.pif = new File(pif);
        this.st = new File(st);
        readTokens();
    }


    /*
    * Reads each token from the tokenFile and add them to the tokens list
    *
    * @throws IOException
    * */
    private void readTokens() throws IOException {
        FileReader fileReader = new FileReader(this.tokenFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String symbol;
        while ((symbol = bufferedReader.readLine()) != null)
            tokens.add(symbol);
    }


    /**
     * Checks if a given string can be converted to an integer
     * @param str the given string
     * @returns true if str can be converted to an int
     *          false otherwise
     *
     */
    public boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }


    /*
    * The Scanner - creates PIF and ST, and for every token in the tokens list it checks and adds the token to PIF or/and ST
    *
    * @Throws Exception(for lexical errors found in the code)
    * */
    void scan() throws Exception {

        List<String> pifSymbol = new ArrayList<>();
        List<Integer> pifValue = new ArrayList<>();
        Map<String, Integer> pif = new HashMap<>();
        HashTable st = new HashTable(128);

        BufferedReader bufferedReader = new BufferedReader(new FileReader(program));
        StringBuilder stringBuilder = new StringBuilder();
        String line = bufferedReader.readLine();
        while (line!=null){
            stringBuilder.append(line);
            stringBuilder.append("\n");
            line = bufferedReader.readLine();
        }

        StringTokenizer stringTokenizer = new StringTokenizer(stringBuilder.toString(), " \n");

        while (stringTokenizer.hasMoreTokens()){
            String token = stringTokenizer.nextToken();
            boolean isInST = true;
            for (String s : tokens) {
                if (token.equals(s)) {
                    isInST = false;
                    break;
                }
            }

            String digits = "0123456789";
            if(isInST && token.charAt(0) == '0' && token.length() == 1){
                st.put(token);
            }
            else if(isInST && digits.indexOf(token.charAt(0)) != -1 && token.matches(".*[abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ].*")){
                throw new Exception("Lexical error : " + token);
            }

            else if(isInST && isInteger(token) && token.charAt(0) == '0'){
                throw new Exception("Lexical error : " + token);
            }

            if (isInST) {
                token = token.replaceAll("\\s+","");
                st.put(token);
                pifSymbol.add(token);
                pifValue.add(st.hash(token));
            }
            else {
                token = token.replaceAll("\\s+","");

                pifSymbol.add(token);
                pifValue.add(-1);
            }
        }


        StringBuilder pifPrint = new StringBuilder();
        for(int i = 0 ; i < pifSymbol.size(); i++){
            pifPrint.append(pifSymbol.get(i)).append(" : ").append(pifValue.get(i)).append('\n');
        }

        FileWriter pifWriter = new FileWriter(this.pif);
        pifWriter.write("PIF \n\n");
        pifWriter.write(pifPrint.toString());
        pifWriter.close();

        FileWriter stWriter = new FileWriter(this.st);
        stWriter.write("The Data Structure I used is a HashTable with LinkedLists \n");
        stWriter.write(st.toString());
        stWriter.close();

    }


}


