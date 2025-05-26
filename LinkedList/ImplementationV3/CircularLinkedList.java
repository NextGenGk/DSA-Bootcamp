package LinkedList.ImplementationV3;

public class CircularLinkedList {
    // Tail pointer to the last node in the list (points to last node, not first)
    private Node tail;
    // Keep track of the number of elements in the list
    private int size;

    // Constructor to initialize an empty circular linked list
    public CircularLinkedList() {
        this.tail = null;
        this.size = 0;
    }

    // Example usage and comprehensive testing
    public static void main(String[] args) {
        // Create a new circular linked list
        CircularLinkedList list = new CircularLinkedList();

        System.out.println("=== TESTING INSERTION OPERATIONS ===");
        // Test insertions
        list.insertFirst(10);
        list.insertLast(20);
        list.insertLast(30);
        list.insertFirst(5);
        list.insertAtPosition(2, 15);
        list.traversal(); // Output: 5 -> 10 -> 15 -> 20 -> 30 -> [CIRCULAR]

        System.out.println("\n=== TESTING SIZE OPERATIONS ===");
        list.displaySize();
        list.verifyCircularStructure();

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
        list.searchCount(20);
        list.traversal();

        System.out.println("\n=== TESTING DELETION OPERATIONS ===");
        list.deleteFirst();
        list.traversal();

        list.deleteLast();
        list.traversal();

        list.deleteAtPosition(1);
        list.traversal();

        System.out.println("\n=== FINAL STATE ===");
        list.displaySize();
        list.traversal();
        list.verifyCircularStructure();
    }

    // ==================== INSERTION OPERATIONS ====================

    // Insert a new element at the beginning of the list
    // Time Complexity: O(1)
    public void insertFirst(int data) {
        // Create a new node
        Node newNode = new Node(data);

        // If list is empty
        if (tail == null) {
            tail = newNode;
            newNode.next = newNode; // Point to itself (circular)
        } else {
            // Insert at beginning (after tail, before current first)
            newNode.next = tail.next; // Point to current first node
            tail.next = newNode;      // Tail points to new first node
        }

        // Increment size
        size++;
        System.out.println("Inserted " + data + " at the beginning");
    }

    // Insert a new element at the end of the list
    // Time Complexity: O(1)
    public void insertLast(int data) {
        // Create a new node
        Node newNode = new Node(data);

        // If list is empty
        if (tail == null) {
            tail = newNode;
            newNode.next = newNode; // Point to itself (circular)
        } else {
            // Insert at end
            newNode.next = tail.next; // New node points to first node
            tail.next = newNode;      // Current tail points to new node
            tail = newNode;           // Update tail to new node
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
        Node current = tail.next; // Start from first node (head)

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
        if (tail == null) {
            System.out.println("List is empty. Cannot delete from beginning.");
            return;
        }

        // Store data to be deleted
        Node head = tail.next; // First node
        int deletedData = head.data;

        // If only one element exists
        if (tail == head) {
            tail = null;
        } else {
            // Remove first node
            tail.next = head.next; // Tail points to second node (new first)
        }

        // Decrement size
        size--;
        System.out.println("Deleted " + deletedData + " from the beginning");
    }

    // Delete the last element from the list
    // Time Complexity: O(n) - need to find second last node
    public void deleteLast() {
        // Check if list is empty
        if (tail == null) {
            System.out.println("List is empty. Cannot delete from end.");
            return;
        }

        // Store data to be deleted
        int deletedData = tail.data;

        // If only one element exists
        if (tail.next == tail) {
            tail = null;
        } else {
            // Find the second last node
            Node current = tail.next; // Start from first node
            while (current.next != tail) {
                current = current.next;
            }

            // Remove last node
            current.next = tail.next; // Second last points to first
            tail = current;           // Update tail to second last
        }

        // Decrement size
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
        Node current = tail.next; // Start from first node
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

    // Display all elements in the list (Circular Traversal)
    // Time Complexity: O(n)
    public void traversal() {
        // Check if list is empty
        if (tail == null) {
            System.out.println("List is empty");
            return;
        }

        // Start from first node (head) and print each element
        Node current = tail.next; // First node
        System.out.print("Circular Traversal: ");

        do {
            System.out.print(current.data);
            current = current.next;
            if (current != tail.next) { // Not back to first node yet
                System.out.print(" -> ");
            }
        } while (current != tail.next); // Stop when we complete the circle

        System.out.println(" -> [CIRCULAR]");
    }

    // ==================== SEARCHING OPERATIONS ====================

    // Search for an element and return true/false
    // Time Complexity: O(n)
    public boolean search(int data) {
        // Check if list is empty
        if (tail == null) {
            System.out.println("Element " + data + " not found in the list (list is empty)");
            return false;
        }

        // Start from first node
        Node current = tail.next;

        // Traverse the circular list
        do {
            // If element found
            if (current.data == data) {
                System.out.println("Element " + data + " found in the list");
                return true;
            }
            current = current.next;
        } while (current != tail.next); // Complete the circle

        // Element not found
        System.out.println("Element " + data + " not found in the list");
        return false;
    }

    // Search for an element and return its position (first occurrence)
    // Time Complexity: O(n)
    public int searchPosition(int data) {
        // Check if list is empty
        if (tail == null) {
            System.out.println("Element " + data + " not found in the list (list is empty)");
            return -1;
        }

        // Start from first node
        Node current = tail.next;
        int position = 0;

        // Traverse the circular list
        do {
            // If element found
            if (current.data == data) {
                System.out.println("Element " + data + " found at position " + position);
                return position;
            }
            current = current.next;
            position++;
        } while (current != tail.next); // Complete the circle

        // Element not found
        System.out.println("Element " + data + " not found in the list");
        return -1;
    }

    // Search and count occurrences of an element
    // Time Complexity: O(n)
    public int searchCount(int data) {
        // Check if list is empty
        if (tail == null) {
            System.out.println("Element " + data + " appears 0 times (list is empty)");
            return 0;
        }

        // Start from first node
        Node current = tail.next;
        int count = 0;

        // Traverse the circular list and count occurrences
        do {
            if (current.data == data) {
                count++;
            }
            current = current.next;
        } while (current != tail.next); // Complete the circle

        System.out.println("Element " + data + " appears " + count + " times");
        return count;
    }

    // ==================== SIZE OPERATIONS ====================

    // Get the size of the list (using stored size variable)
    // Time Complexity: O(1)
    public int size() {
        return size;
    }

    // Calculate size by traversing the list (alternative method)
    // Time Complexity: O(n)
    public int sizeByTraversal() {
        if (tail == null) {
            return 0;
        }

        int count = 0;
        Node current = tail.next; // Start from first node

        // Count nodes by traversing the circle
        do {
            count++;
            current = current.next;
        } while (current != tail.next);

        return count;
    }

    // Check if the list is empty
    // Time Complexity: O(1)
    public boolean isEmpty() {
        return tail == null;
    }

    // Display size information
    public void displaySize() {
        System.out.println("List size: " + size());
        System.out.println("Is empty: " + isEmpty());
    }

    // Check if the circular structure is intact
    // Time Complexity: O(n)
    public boolean verifyCircularStructure() {
        if (tail == null) {
            return true; // Empty list is technically circular
        }

        Node current = tail.next; // Start from first node
        int count = 0;

        // Traverse and count nodes
        do {
            count++;
            current = current.next;
            // Safety check to prevent infinite loop in case of corruption
            if (count > size + 1) {
                System.out.println("Circular structure is corrupted!");
                return false;
            }
        } while (current != tail.next);

        boolean isValid = (count == size);
        System.out.println("Circular structure is " + (isValid ? "intact" : "corrupted"));
        return isValid;
    }

    // Inner Node class to represent each element in the circular linked list
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
