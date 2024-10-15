package utilities;

import mypackage.Provider;

public class CircularLinkedList {
    private CircularNode head;
    private CircularNode tail;
    private int size;

    public static class CircularNode {
        Provider data;
        CircularNode next;

        CircularNode(Provider data) {
            this.data = data;
        }

        public CircularNode getNext() {
            return next;
        }
        public Provider getData() {
            return data;
        }
    }

    public CircularLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // Add a new provider to the circular linked list
    public void add(Provider provider) {
        CircularNode newNode = new CircularNode(provider);
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

    public CircularNode getHead() {
        return head;
    }

    public void printTechProviders() {
        System.out.println("Rotation list for the technicians");
        if (head == null) return;

        CircularNode current = head;
        do {
            System.out.print(current.data.getProfile().getFname() + " " + current.data.getProfile().getLname() + " (" + current.data.getLocation() + ")");
            current = current.next;
            // Print the arrow only if the current node is not the head
            if (current != head) {
                System.out.print(" --> ");
            }
        } while (current != head);
        System.out.println(); // Print a new line after the list
    }

    public void sort() {
        if (head == null || head.next == head) return; // Check if list is empty or has only one element
        CircularNode current;
        CircularNode index;
        Provider temp;

        for (current = head; current.next != head; current = current.next) {
            for (index = current.next; index != head; index = index.next) {
                // Compare by location first
                int locationComparison = current.data.getLocation().compareTo(index.data.getLocation());
                if (locationComparison > 0 ||
                        (locationComparison == 0 && current.data.getProfile().getLname().compareTo(index.data.getProfile().getLname()) < 0)) {
                    // Swap if current's location is greater than index's or if locations are equal and current's last name is greater
                    temp = current.data;
                    current.data = index.data;
                    index.data = temp;
                }
            }
        }
    }
}