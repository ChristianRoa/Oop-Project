package utilities;

import mypackage.Provider;

public class CircularLinkedList {
    private Node head;
    private Node tail;
    private int size;

    private static class Node {
        Provider data;
        Node next;

        Node(Provider data) {
            this.data = data;
        }
    }

    public CircularLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // Add a new provider to the circular linked list
    public void add(Provider provider) {
        Node newNode = new Node(provider);
        if (head == null) {
            head = newNode;
            tail = newNode;
            tail.next = head; // Circular link
        } else {
            tail.next = newNode;
            tail = newNode;
            tail.next = head; // Maintain circular link
        }
        size++;
    }

    public int size() {
        return size;
    }

    public Node getHead() {
        return head;
    }

    public void printTechProviders() {
        if (head == null) return;

        Node current = head;
        do {
            System.out.println(current.data);
            current = current.next;
        } while (current != head);
    }

    public void sort() {
        if (head == null || head.next == head) return;
        Node current;
        Node index;
        Provider temp;

        for (current = head; current.next != head; current = current.next) {
            for (index = current.next; index != head; index = index.next) {
                if (current.data.getProfile().compareTo(index.data.getProfile()) > 0) {
                    temp = current.data;
                    current.data = index.data;
                    index.data = temp;
                }
            }
        }
    }
}