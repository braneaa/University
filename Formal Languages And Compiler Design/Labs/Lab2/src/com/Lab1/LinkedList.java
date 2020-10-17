package com.Lab1;

public class LinkedList {

    //Properties params
    private Node head;

    /*
    input parameters:
        value: String

    This function iterates through the linked list. If it finds the given value, the node is returned.
    Otherwise, the function returns null.

    output parameters:
        current : Node
     */
    Node search(String value){
        Node current = head;

        while (!current.value.equals(value) ){
            current = current.next;
            if(current == null){
                return null;
            }
        }
        return current;
    }

    /*
    input parameters:
        value: String

    Creates a new node with the given string as value, then inserts it in the linked list.
    If there is no node in the list yet, the new node becomes the head of the list.
     */
    void insert(String value){
        Node newNode = new Node(value);
        if(head == null){
            head = newNode;
        }
        else {
            Node current = head;
            while (current.next != null)
                current = current.next;
            current.next = newNode;
        }
    }

    @Override
    public String toString() {
        Node current = head;
        StringBuilder s = new StringBuilder(current.value);
        while (current.next != null){
            current = current.next;
            s.append(" - > ").append(current.value);
        }
        return s.toString();
    }
}
