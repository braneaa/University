package com.Lab1;

public class Main {

    public static void main(String[] args) {

        //Initialize a new HashTable
        HashTable hashTable = new HashTable(128);

        //Add values to it
        hashTable.put("Mancare");
        hashTable.put("12345");
        hashTable.put("Masina");
        hashTable.put("FLCD");
        hashTable.put("CDFL");
        hashTable.put("------------");
        hashTable.put("Mancare");
        hashTable.put("Masa");
        hashTable.put("Profesor");
        hashTable.put("!@#$%^&*");

        System.out.println(hashTable.toString());

    }
}
