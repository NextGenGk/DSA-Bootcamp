package LinkedList.ImplementationV3;

public class SinglyLinkedList {

    // Reference to the first node of the list
    private Node head;

    // Reference to the last node of the list (used to optimize insertLast to O(1))
    private Node tail;

    // Variable to store the current number of elements in the list
    private int size;

    // Constructor to initialize an empty list
    public SinglyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // ==================== MAIN METHOD (TESTING) ====================

    public static void main(String[] args) {

        // Create a new singly linked list
        SinglyLinkedList list = new SinglyLinkedList();

        System.out.println("=== INSERTION OPERATIONS ===");

        // Insert elements
        list.insertFirst(10);
        list.insertLast(20);
        list.insertLast(30);
        list.insertFirst(5);
        list.insertAtPosition(2, 15);

        // Display list
        list.traversal();

        System.out.println("\n=== SEARCH OPERATIONS ===");

        // Search elements
        list.search(15);
        list.search(100);

        list.searchPosition(20);
        list.searchPosition(99);

        // Add duplicate elements
        list.insertLast(20);
        list.insertLast(20);

        list.traversal();

        System.out.println("\n=== DELETION OPERATIONS ===");

        list.deleteFirst();
        list.traversal();

        list.deleteLast();
        list.traversal();

        list.deleteAtPosition(1);
        list.traversal();

        System.out.println("\nFinal Size: " + list.size());
    }

    // ==================== INSERTION OPERATIONS ====================

    // Insert element at the beginning
    // Time Complexity: O(1)
    public void insertFirst(int data) {

        // Create new node
        Node newNode = new Node(data);

        // If list is empty, both head and tail should point to new node
        if (head == null) {
            head = tail = newNode;
        } else {
            // Point new node to current head
            newNode.next = head;

            // Move head to the new node
            head = newNode;
        }

        // Increase list size
        size++;

        System.out.println("Inserted " + data + " at the beginning");
    }

    // Insert element at the end
    // Time Complexity: O(1) because we maintain tail pointer
    public void insertLast(int data) {

        // Create new node
        Node newNode = new Node(data);

        // If list is empty
        if (head == null) {
            head = tail = newNode;
        } else {

            // Link current tail to new node
            tail.next = newNode;

            // Update tail to the new node
            tail = newNode;
        }

        size++;

        System.out.println("Inserted " + data + " at the end");
    }

    // Insert element at a specific position
    // Time Complexity: O(n)
    public void insertAtPosition(int position, int data) {

        // Check if position is valid
        if (position < 0 || position > size) {
            System.out.println("Invalid position!");
            return;
        }

        // Insert at beginning
        if (position == 0) {
            insertFirst(data);
            return;
        }

        // Insert at end
        if (position == size) {
            insertLast(data);
            return;
        }

        // Create new node
        Node newNode = new Node(data);

        // Start traversal from head
        Node current = head;

        // Move to the node before insertion position
        for (int i = 0; i < position - 1; i++) {
            current = current.next;
        }

        // Adjust links
        newNode.next = current.next;
        current.next = newNode;

        size++;

        System.out.println("Inserted " + data + " at position " + position);
    }

    // ==================== DELETION OPERATIONS ====================

    // Delete first element
    // Time Complexity: O(1)
    public void deleteFirst() {

        // If list is empty
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        int deletedData = head.data;

        // Move head to the next node
        head = head.next;

        size--;

        // If list becomes empty, reset tail
        if (head == null) {
            tail = null;
        }

        System.out.println("Deleted " + deletedData + " from beginning");
    }

    // Delete last element
    // Time Complexity: O(n)
    public void deleteLast() {

        // If list is empty
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        // If only one node exists
        if (head.next == null) {
            int deletedData = head.data;
            head = tail = null;
            size--;
            System.out.println("Deleted " + deletedData + " from end");
            return;
        }

        // Traverse to second last node
        Node current = head;

        while (current.next != tail) {
            current = current.next;
        }

        int deletedData = tail.data;

        // Remove last node
        current.next = null;

        // Update tail
        tail = current;

        size--;

        System.out.println("Deleted " + deletedData + " from end");
    }

    // Delete element at specific position
    // Time Complexity: O(n)
    public void deleteAtPosition(int position) {

        // Validate position
        if (position < 0 || position >= size) {
            System.out.println("Invalid position!");
            return;
        }

        // Delete first node
        if (position == 0) {
            deleteFirst();
            return;
        }

        // Delete last node
        if (position == size - 1) {
            deleteLast();
            return;
        }

        Node current = head;

        // Traverse to node before deletion point
        for (int i = 0; i < position - 1; i++) {
            current = current.next;
        }

        int deletedData = current.next.data;

        // Bypass the node
        current.next = current.next.next;

        size--;

        System.out.println("Deleted " + deletedData + " from position " + position);
    }

    // ==================== TRAVERSAL ====================

    // Display the elements of the list
    // Time Complexity: O(n)
    public void traversal() {

        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        Node current = head;

        System.out.print("Traversal: ");

        while (current != null) {

            System.out.print(current.data);

            if (current.next != null) {
                System.out.print(" -> ");
            }

            current = current.next;
        }

        System.out.println();
    }

    // ==================== SEARCH OPERATIONS ====================

    // Search element in the list
    // Time Complexity: O(n)
    public boolean search(int data) {

        Node current = head;

        while (current != null) {

            if (current.data == data) {
                System.out.println("Element " + data + " found");
                return true;
            }

            current = current.next;
        }

        System.out.println("Element " + data + " not found");
        return false;
    }

    // Find position of element
    // Time Complexity: O(n)
    public int searchPosition(int data) {

        Node current = head;
        int position = 0;

        while (current != null) {

            if (current.data == data) {
                System.out.println("Element " + data + " found at position " + position);
                return position;
            }

            current = current.next;
            position++;
        }

        System.out.println("Element " + data + " not found");
        return -1;
    }

    // ==================== SIZE METHODS ====================

    // Return size of list
    public int size() {
        return size;
    }

    // Check if list is empty
    public boolean isEmpty() {
        return head == null;
    }

    // ==================== NODE CLASS ====================

    // Node represents a single element of the linked list
    private static class Node {

        int data;      // Data stored in the node
        Node next;     // Reference to the next node

        // Constructor
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
}