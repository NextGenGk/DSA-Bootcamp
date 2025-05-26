package LinkedList.ImplementationV3;

public class SinglyLinkedList {
    // Head pointer to the first node in the list
    private Node head;
    // Keep track of the number of elements in the list
    private int size;

    // Constructor to initialize an empty linked list
    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    // Example usage and comprehensive testing
    public static void main(String[] args) {
        // Create a new linked list
        SinglyLinkedList list = new SinglyLinkedList();

        System.out.println("=== TESTING INSERTION OPERATIONS ===");
        // Test insertions
        list.insertFirst(10);
        list.insertLast(20);
        list.insertLast(30);
        list.insertFirst(5);
        list.insertAtPosition(2, 15);
        list.traversal(); // Output: 5 -> 10 -> 15 -> 20 -> 30

        System.out.println("\n=== TESTING SIZE OPERATIONS ===");

        System.out.println("\n=== TESTING TRAVERSAL OPERATIONS ===");
        list.traversal();

        System.out.println("\n=== TESTING SEARCHING OPERATIONS ===");
        list.search(15);
        list.search(100);
        list.searchPosition(20);
        list.searchPosition(99);

        // Add duplicate elements for count testing
        list.insertLast(20);
        list.insertLast(20);
        list.traversal();

        System.out.println("\n=== TESTING DELETION OPERATIONS ===");
        list.deleteFirst();
        list.traversal();

        list.deleteLast();
        list.traversal();

        list.deleteAtPosition(1);
        list.traversal();

        System.out.println("\n=== FINAL STATE ===");
        list.traversal();
    }

    // ==================== INSERTION OPERATIONS ====================

    // Insert a new element at the beginning of the list
    // Time Complexity: O(1)
    public void insertFirst(int data) {
        // Create a new node
        Node newNode = new Node(data);
        // Point new node to current head
        newNode.next = head;
        // Update head to point to new node
        head = newNode;
        // Increment size
        size++;
        System.out.println("Inserted " + data + " at the beginning");
    }

    // Insert a new element at the end of the list
    // Time Complexity: O(n)
    public void insertLast(int data) {
        // Create a new node
        Node newNode = new Node(data);

        // If list is empty, make new node the head
        if (head == null) {
            head = newNode;
        } else {
            // Traverse to the last node
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            // Link the last node to new node
            current.next = newNode;
        }
        // Increment size
        size++;
        System.out.println("Inserted " + data + " at the end");
    }

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

        // Traverse to the position before insertion point
        for (int i = 0; i < position - 1; i++) {
            current = current.next;
        }

        // Insert the new node
        newNode.next = current.next;
        current.next = newNode;
        size++;
        System.out.println("Inserted " + data + " at position " + position);
    }

    // ==================== DELETION OPERATIONS ====================

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
        // Move head to the next node
        head = head.next;
        // Decrement size
        size--;
        System.out.println("Deleted " + deletedData + " from the beginning");
    }

    // Delete the last element from the list
    // Time Complexity: O(n)
    public void deleteLast() {
        // Check if list is empty
        if (head == null) {
            System.out.println("List is empty. Cannot delete from end.");
            return;
        }

        // If only one element exists
        if (head.next == null) {
            int deletedData = head.data;
            head = null;
            size--;
            System.out.println("Deleted " + deletedData + " from the end");
            return;
        }

        // Traverse to the second last node
        Node current = head;
        while (current.next.next != null) {
            current = current.next;
        }

        // Store data to be deleted and remove the last node
        int deletedData = current.next.data;
        current.next = null;
        size--;
        System.out.println("Deleted " + deletedData + " from the end");
    }

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

        // Traverse to the position before deletion point
        Node current = head;
        for (int i = 0; i < position - 1; i++) {
            current = current.next;
        }

        // Store data to be deleted and remove the node
        int deletedData = current.next.data;
        current.next = current.next.next;
        size--;
        System.out.println("Deleted " + deletedData + " from position " + position);
    }

    // ==================== TRAVERSAL OPERATIONS ====================

    // Display all elements in the list (Forward Traversal)
    // Time Complexity: O(n)
    public void traversal() {
        // Check if list is empty
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        // Start from head and print each element
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

    // ==================== SEARCHING OPERATIONS ====================

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

    // ==================== SIZE OPERATIONS ====================

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

    // Inner Node class to represent each element in the linked list
    private static class Node {
        int data;    // Data stored in the node
        Node next;   // Reference to the next node

        // Constructor to create a new node with given data
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
}
