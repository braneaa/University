package com.Lab1;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        Grammar grammar = new Grammar(new File("data/g1.txt"));
        grammar.printProductions();
        grammar.printNonterminals();
        grammar.printTerminals();
        grammar.printStartingSymbol();
        grammar.printProduction("S");
    }
}
