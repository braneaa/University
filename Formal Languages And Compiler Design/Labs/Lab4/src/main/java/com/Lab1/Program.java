package com.Lab1;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

class Program {

    private File file;
    private String initialState;
    private Set<String> states = new HashSet<>();
    private Set<String> alphabet = new HashSet<>();
    private Multimap<Pair<String, String>, String> transitions = ArrayListMultimap.create();
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
                    initialState = data;
                }

                if(count == 3){
                    StringTokenizer tokenizer = new StringTokenizer(data, ",");
                    while (tokenizer.hasMoreTokens())
                        alphabet.add(tokenizer.nextToken());
                }
                if(count == 4){
                    if (!data.isEmpty())
                    {
                        StringTokenizer tokenizer = new StringTokenizer(data, ",");
                        String x = tokenizer.nextToken();
                        String y = tokenizer.nextToken();
                        String z = tokenizer.nextToken();
                        Pair<String, String> pair = new Pair<>(x, y);
                        transitions.put(pair, z);

                    }
                }
                if (count == 5){
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
        System.out.println("Press 2 for the initial state");
        System.out.println("Press 3 for the alphabet");
        System.out.println("Press 4 for the transitions");
        System.out.println("Press 5 for the set of final states");
        System.out.println("Press 6 to check if a sequence is accepted by th FA");
        System.out.println("Press 0 to close the program");

    }

    private boolean checkIfDeterministic(){
        for (Pair<String, String> pair: transitions.keySet()){
            if(transitions.get(pair).size() > 1)
                return false;
        }
        return true;
    }

    private boolean checkIfSequenceIsAccepted(String sequence){
        String currentState = initialState;
        while (!sequence.isEmpty()){
            boolean ok = false;
            System.out.println(sequence + "   " + currentState);
            for(Pair<String , String> pair: transitions.keySet()){
                if(pair.getLeft().equals(currentState) && pair.getRight().equals(String.valueOf(sequence.charAt(0)))){
                    ok = true;
                    currentState = String.join("" ,transitions.get(pair));
                    sequence = sequence.substring(1);
                    break;
                }
            }
            if(!ok)
                return false;
        }
        boolean finalStateCheck = false;
        for(String finalState : finalStates){
            if (finalState.equals(currentState)) {
                finalStateCheck = true;
                break;
            }
        }

        return finalStateCheck;
    }

    void run(){
        this.menu();
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        while (x != 0){
            if(x == 1)
                System.out.println(states.toString());
            if (x == 2)
                System.out.println(initialState);
            if(x == 3)
                System.out.println(alphabet.toString());
            if(x == 4)
                for(Pair<String, String> pair: transitions.keySet()){
                    System.out.println(pair + " = " + transitions.get(pair).toString());
                }
            if(x == 5)
                System.out.println(finalStates.toString());
            if(x == 6){
                if(!checkIfDeterministic()){
                    System.out.println("The FA is not deterministic !!");
                }
                else {
                    System.out.println("Give a sequence");
                    String seq = scanner.next();
                    if(checkIfSequenceIsAccepted(seq))
                        System.out.println(seq + " is accepted");
                    else System.out.println(seq + " is not accepted");
                }
            }

            this.menu();
            x = scanner.nextInt();

        }
    }
}
