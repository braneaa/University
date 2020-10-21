package com.Lab1;

import java.util.ArrayList;
import java.util.Arrays;

public class HashTable {

    //Properties params
    private int capacity;
    private LinkedList[] linkedLists;


    //constructor
    HashTable(int capacity){
        this.capacity = capacity;
        this.linkedLists = new LinkedList[capacity];
    }

    /*
    input parameters:
        string : String

    Calculates the hash function for a given string by summing up its ASCII characters then getting the remain after doing the modulo with the capacity of the linked list

    output parameters:
        hash : int
     */
    private int hash(String string){
        int hash = 0;
        for (Character character : string.toCharArray())
            hash += character;

        hash = hash % capacity;

        return hash;
    }


    /*
    input parameters:
        string : String

    First, it calculates the position of the given string bt calling the "hash" function, then it searches for the according linked list.
    After that, we add the string to that linked list
     */
    void put(String string){
        int hash = hash(string);
        LinkedList linkedList = this.linkedLists[hash];

        if(linkedList == null){
            linkedList = new LinkedList();
            linkedList.insert(string);
            linkedLists[hash] = linkedList;
        }
        else if(linkedList.search(string) == null){
            linkedList.insert(string);
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for(int i = 0; i< capacity; i++){
            str.append(linkedLists[i] == null ?
                    "At position " + i + ": " + "\n" :
                    "At position " + i + ": " + linkedLists[i] + "\n");
        }
        return str.toString();
    }
}
