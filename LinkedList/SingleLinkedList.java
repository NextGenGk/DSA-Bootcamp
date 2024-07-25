package LinkedList;

public class SingleLinkedList {

    // Head Pointer
    Node head;

    // Node Class
    static class Node {
        int data;      // the data value
        Node next;      // the reference to the next Node in the linked list

        // Constructors
        Node(int data, Node next) {
            this.data = data;  // Initialize data with the provided value
            this.next = next;  // Initialize next with the provided reference
        }
        Node(int data) {
            this.data = data;  // Initialize data with the provided value
            this.next = null;   // Initialize next as null since it's the end of the list
        }
    }

    // Insertion (Insert a value at a specified location)
    void insert(int data, int location) {
        Node node = new Node(data);

        if (location == 0) {
            node.next = head;
            head = node;
            return;
        }

        Node prev = head;
        for (int i=0; i<location-1; i++) {
            prev = prev.next;
        }
        node.next = prev.next;
        prev.next = node;
    }

    // Traversal
    public void printList() {
        if (head == null) {
            System.out.println("List is Empty");
        }

        Node currNode = head;
        while (currNode != null) {
            System.out.print(currNode.data+" -> ");
            currNode = currNode.next;
        }
        System.out.println("null");
    }

    // Deletion (Delete a value at a specified position)
    void deletion(int location) {
        if (location == 0) {
            head = head.next;
            return;
        }

        Node prev = head;
        for (int i=0; i<location-1; i++) {
            prev = prev.next;
        }
        prev.next = prev.next.next;
    }

    // Main Function
    public static void main(String[] args) {
        // Creating a new Node with the value from the array
//        Node node1 = new Node(10);
//        Node node2 = new Node(20);
//        Node node3 = new Node(30);
//
//        Node head = node1;
//        head.next = node2;
//        node2.next = node3;
//        node3.next = null;

        // Creating a object of Linked List Class
        SingleLinkedList list = new SingleLinkedList();
        list.insert(80, 0);
        list.insert(90, 1);
        list.insert(100, 2);
        list.insert(70, 3);
        list.printList();
        list.deletion(1);
        list.printList();
    }
}
