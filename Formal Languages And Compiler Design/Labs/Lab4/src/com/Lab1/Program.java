package com.Lab1;

import util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Program {

    private File file;
    private Set<String> states = new HashSet<>();
    private Set<String> alphabet = new HashSet<>();
    private Map<Pair<String, String>, String> transitions = new HashMap<>();
    private Set<String> finalStates = new HashSet<>();

    Program(File file){
        this.file = file;
        this.readFA();
    }

    private void readFA() {
        int count = 1;
        try{
            Scanner scanner = new Scanner(this.file);
            while (scanner.hasNextLine())
            {
                String data = scanner.nextLine();
                if(data.isEmpty()){
                    count ++;
                }
                if(count == 1){
                    StringTokenizer tokenizer = new StringTokenizer(data, ",");
                    while (tokenizer.hasMoreTokens()) {
                        String token = tokenizer.nextToken();
                        states.add(token);
                    }
                }
                if(count == 2){
                    StringTokenizer tokenizer = new StringTokenizer(data, ",");
                    while (tokenizer.hasMoreTokens())
                        alphabet.add(tokenizer.nextToken());
                }
                if(count == 3){
                    if (!data.isEmpty())
                    {
                        StringTokenizer tokenizer = new StringTokenizer(data, ",");
                        String x = tokenizer.nextToken();
                        String y = tokenizer.nextToken();
                        String z = tokenizer.nextToken();
                        Pair<String, String> pair = new Pair<String, String>(x, y);
                        transitions.put(pair, z);

                    }
                }
                if (count == 4){
                    StringTokenizer tokenizer = new StringTokenizer(data, ",");
                    while (tokenizer.hasMoreTokens())
                        finalStates.add(tokenizer.nextToken());
                }
            }
            scanner.close();
        }
        catch (FileNotFoundException e){
            System.out.println("An error occured");
            e.printStackTrace();
        }
    }

    private void menu(){
        System.out.println("Press 1 for the set of states");
        System.out.println("Press 2 for the alphabet");
        System.out.println("Press 3 for the transitions");
        System.out.println("Press 4 for the set of final states");
        System.out.println("Press 0 to close the program");

    }

    void run(){
        this.menu();
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        while (x != 0){
            if(x == 1)
                System.out.println(states.toString());
            if(x == 2)
                System.out.println(alphabet.toString());
            if(x == 3)
                for(Object o : transitions.entrySet()){
                    System.out.println(o.toString());
                }
            if(x == 4)
                System.out.println(finalStates.toString());
            this.menu();
            x = scanner.nextInt();

        }
    }
}
