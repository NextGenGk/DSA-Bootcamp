package LinkedList.ImplementationV3;

public class DoublyLinkedList {
    // Head pointer to the first node in the list
    private Node head;
    // Tail pointer to the last node in the list
    private Node tail;
    // Keep track of the number of elements in the list
    private int size;

    // Constructor to initialize an empty doubly linked list
    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // Example usage and comprehensive testing
    public static void main(String[] args) {
        // Create a new doubly linked list
        DoublyLinkedList list = new DoublyLinkedList();

        System.out.println("=== TESTING INSERTION OPERATIONS ===");
        // Test insertions
        list.insertFirst(10);
        list.insertLast(20);
        list.insertLast(30);
        list.insertFirst(5);
        list.insertAtPosition(2, 15);
        list.forwardTraversal(); // Output: 5 <-> 10 <-> 15 <-> 20 <-> 30

        System.out.println("\n=== TESTING SIZE OPERATIONS ===");
        list.displaySize();

        System.out.println("\n=== TESTING TRAVERSAL OPERATIONS ===");
        list.forwardTraversal();
        list.backwardTraversal();

        System.out.println("\n=== TESTING SEARCHING OPERATIONS ===");
        list.search(15);
        list.search(100);
        list.searchPosition(20);
        list.searchPosition(99);

        // Add duplicate elements for count testing
        list.insertLast(20);
        list.insertLast(20);
        list.forwardTraversal();

        System.out.println("\n=== TESTING DELETION OPERATIONS ===");
        list.deleteFirst();
        list.forwardTraversal();

        list.deleteLast();
        list.forwardTraversal();

        list.deleteAtPosition(1);
        list.forwardTraversal();

        System.out.println("\n=== FINAL STATE ===");
        list.displaySize();
        list.forwardTraversal();
        list.backwardTraversal();

        System.out.println("\n=== TESTING GET ELEMENT ===");
        System.out.println("Element at position 0: " + list.getElementAt(0));
        System.out.println("Element at position 2: " + list.getElementAt(2));
    }

    // ==================== INSERTION OPERATIONS ====================

    // Insert a new element at the beginning of the list
    // Time Complexity: O(1)
    public void insertFirst(int data) {
        // Create a new node
        Node newNode = new Node(data);

        // If list is empty
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            // Link new node to current head
            newNode.next = head;
            head.prev = newNode;
            // Update head to point to new node
            head = newNode;
        }
        // Increment size
        size++;
        System.out.println("Inserted " + data + " at the beginning");
    }

    // Insert a new element at the end of the list
    // Time Complexity: O(1) - due to tail pointer
    public void insertLast(int data) {
        // Create a new node
        Node newNode = new Node(data);

        // If list is empty
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            // Link new node to current tail
            tail.next = newNode;
            newNode.prev = tail;
            // Update tail to point to new node
            tail = newNode;
        }
        // Increment size
        size++;
        System.out.println("Inserted " + data + " at the end");
    }

    // ==================== DELETION OPERATIONS ====================

    // Insert a new element at a specified position (0-based indexing)
    // Time Complexity: O(n)
    public void insertAtPosition(int position, int data) {
        // Check if position is valid
        if (position < 0 || position > size) {
            System.out.println("Invalid position! Position should be between 0 and " + size);
            return;
        }

        // If inserting at beginning
        if (position == 0) {
            insertFirst(data);
            return;
        }

        // If inserting at end
        if (position == size) {
            insertLast(data);
            return;
        }

        // Create new node
        Node newNode = new Node(data);
        Node current = head;

        // Traverse to the insertion position
        for (int i = 0; i < position; i++) {
            current = current.next;
        }

        // Insert the new node
        newNode.next = current;
        newNode.prev = current.prev;
        current.prev.next = newNode;
        current.prev = newNode;

        size++;
        System.out.println("Inserted " + data + " at position " + position);
    }

    // Delete the first element from the list
    // Time Complexity: O(1)
    public void deleteFirst() {
        // Check if list is empty
        if (head == null) {
            System.out.println("List is empty. Cannot delete from beginning.");
            return;
        }

        // Store data to be deleted
        int deletedData = head.data;

        // If only one element exists
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            // Move head to the next node
            head = head.next;
            head.prev = null;
        }

        // Decrement size
        size--;
        System.out.println("Deleted " + deletedData + " from the beginning");
    }

    // Delete the last element from the list
    // Time Complexity: O(1) - due to tail pointer
    public void deleteLast() {
        // Check if list is empty
        if (tail == null) {
            System.out.println("List is empty. Cannot delete from end.");
            return;
        }

        // Store data to be deleted
        int deletedData = tail.data;

        // If only one element exists
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            // Move tail to the previous node
            tail = tail.prev;
            tail.next = null;
        }

        // Decrement size
        size--;
        System.out.println("Deleted " + deletedData + " from the end");
    }

    // ==================== TRAVERSAL OPERATIONS ====================

    // Delete element at a specified position (0-based indexing)
    // Time Complexity: O(n)
    public void deleteAtPosition(int position) {
        // Check if position is valid
        if (position < 0 || position >= size) {
            System.out.println("Invalid position! Position should be between 0 and " + (size - 1));
            return;
        }

        // If deleting first element
        if (position == 0) {
            deleteFirst();
            return;
        }

        // If deleting last element
        if (position == size - 1) {
            deleteLast();
            return;
        }

        // Traverse to the deletion position
        Node current = head;
        for (int i = 0; i < position; i++) {
            current = current.next;
        }

        // Store data to be deleted and remove the node
        int deletedData = current.data;
        current.prev.next = current.next;
        current.next.prev = current.prev;

        size--;
        System.out.println("Deleted " + deletedData + " from position " + position);
    }

    // Display all elements in the list (Forward Traversal)
    // Time Complexity: O(n)
    public void forwardTraversal() {
        // Check if list is empty
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        // Start from head and print each element
        Node current = head;
        System.out.print("Forward Traversal: ");
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(" <-> ");
            }
            current = current.next;
        }
        System.out.println();
    }

    // ==================== SEARCHING OPERATIONS ====================

    // Display all elements in reverse order (Backward Traversal)
    // Time Complexity: O(n)
    public void backwardTraversal() {
        // Check if list is empty
        if (tail == null) {
            System.out.println("List is empty");
            return;
        }

        // Start from tail and print each element
        Node current = tail;
        System.out.print("Backward Traversal: ");
        while (current != null) {
            System.out.print(current.data);
            if (current.prev != null) {
                System.out.print(" <-> ");
            }
            current = current.prev;
        }
        System.out.println();
    }

    // Search for an element and return true/false
    // Time Complexity: O(n)
    public boolean search(int data) {
        // Start from head
        Node current = head;

        // Traverse the list
        while (current != null) {
            // If element found
            if (current.data == data) {
                System.out.println("Element " + data + " found in the list");
                return true;
            }
            current = current.next;
        }
        // Element not found
        System.out.println("Element " + data + " not found in the list");
        return false;
    }

    // ==================== SIZE OPERATIONS ====================

    // Search for an element and return its position (first occurrence)
    // Time Complexity: O(n)
    public int searchPosition(int data) {
        // Start from head
        Node current = head;
        int position = 0;

        // Traverse the list
        while (current != null) {
            // If element found
            if (current.data == data) {
                System.out.println("Element " + data + " found at position " + position);
                return position;
            }
            current = current.next;
            position++;
        }
        // Element not found
        System.out.println("Element " + data + " not found in the list");
        return -1;
    }

    // Get the size of the list (using stored size variable)
    // Time Complexity: O(1)
    public int size() {
        return size;
    }

    // Check if the list is empty
    // Time Complexity: O(1)
    public boolean isEmpty() {
        return head == null;
    }

    // Display size information
    public void displaySize() {
        System.out.println("List size: " + size());
        System.out.println("Is empty: " + isEmpty());
    }

    // Get element at specific position
    // Time Complexity: O(n)
    public int getElementAt(int position) {
        if (position < 0 || position >= size) {
            System.out.println("Invalid position! Position should be between 0 and " + (size - 1));
            return -1;
        }

        Node current = head;
        for (int i = 0; i < position; i++) {
            current = current.next;
        }

        return current.data;
    }

    // Inner Node class to represent each element in the doubly linked list
    private static class Node {
        int data;     // Data stored in the node
        Node next;    // Reference to the next node
        Node prev;    // Reference to the previous node

        // Constructor to create a new node with given data
        Node(int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

}
