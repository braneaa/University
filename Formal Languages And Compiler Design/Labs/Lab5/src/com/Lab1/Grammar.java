package com.Lab1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Grammar {

    private File file;
    private Set<String> nonterminals;
    private Set<String> terminals;
    private String startSymbol;
    private Map<String, ArrayList<String>> production;


    Grammar(File file){
        this.file = file;
        nonterminals = new HashSet<>();
        terminals = new HashSet<>();
        production = new HashMap<>();
        this.readFile();
    }

    private void readFile(){
        try {
            Scanner scanner = new Scanner(this.file);

            String data = scanner.nextLine();
            StringTokenizer tokenizerNonterminals = new StringTokenizer(data, ",");
            while (tokenizerNonterminals.hasMoreTokens()){
                String token = tokenizerNonterminals.nextToken();
                nonterminals.add(token);
            }

            data = scanner.nextLine();
            StringTokenizer tokenizerTerminals = new StringTokenizer(data, ",");
            while (tokenizerTerminals.hasMoreTokens()){
                String token = tokenizerTerminals.nextToken();
                terminals.add(token);
            }

            data = scanner.nextLine();
            startSymbol = data;

            while(scanner.hasNextLine()) {
                data = scanner.nextLine();
                StringTokenizer production = new StringTokenizer(data, "~");
                String leftValue = production.nextToken();
                String rightValues = production.nextToken();
                StringTokenizer secondTokenizer = new StringTokenizer(rightValues, "|");
                ArrayList<String> rightProductionSide = new ArrayList<>();
                while (secondTokenizer.hasMoreTokens()) {
                    String token = secondTokenizer.nextToken();
                    rightProductionSide.add(token);
                }
                this.production.put(leftValue, rightProductionSide);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occured");
            e.printStackTrace();
        }
    }

    void printNonterminals(){
        System.out.println("Non-Terminals : ");
        System.out.println(nonterminals.toString());
    }

    void printTerminals(){
        System.out.println("Terminals : ");
        System.out.println(terminals.toString());
    }

    void printProductions(){
        System.out.println("Productions : ");
        for(Map.Entry<String, ArrayList<String>> entry: production.entrySet()){
            System.out.print(entry.getKey() + " -> ");
            System.out.print(entry.getValue().toString());
            System.out.println();
        }
    }
    void printStartingSymbol(){
        System.out.println("Starting Symbol : ");
        System.out.println(startSymbol);
    }

    void printProduction(String nonterminal){
        System.out.println("Production for : " + nonterminal);
        for(Map.Entry<String, ArrayList<String>> entry : production.entrySet()){
            if(entry.getKey().equals(nonterminal)){
                System.out.print(entry.getKey() + " -> ");
                System.out.print(entry.getValue().toString());
                System.out.println();
            }
        }
    }

    void menu(){
        System.out.println("1. Print the set of nonterminals");
        System.out.println("2. Print the set of terminals");
        System.out.println("3. Print the starting symbol");
        System.out.println("4. Print all productions");
        System.out.println("5. Print the production for a certain nonterminal");
        System.out.println("0. Exit");
    }

    void run(){
        this.menu();
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        while (x!=0){
            if(x == 1){
                printNonterminals();
            }
            if(x == 2){
                printTerminals();
            }
            if (x == 3){
                printStartingSymbol();
            }
            if(x == 4){
                printProductions();
            }
            if (x == 5){
                printProduction(scanner.next());
            }

            this.menu();
            x = scanner.nextInt();
        }
    }
}
