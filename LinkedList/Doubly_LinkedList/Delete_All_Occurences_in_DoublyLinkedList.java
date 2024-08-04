package LinkedList.Doubly_LinkedList;

public class Delete_All_Occurences_in_DoublyLinkedList {

    // Node class represents a node in a linked list
    static class Node {
        int data;  // Data stored in the node
        Node next; // Pointer to the next node in the list
        Node prev; // Pointer to the previous node in the list

        // Constructor with data and next node as parameters
        Node(int data, Node next) {
            this.data = data;
            this.next = next;
            this.prev = null;
        }

        // Constructor with only data as a parameter, sets next and prev to null
        Node(int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    // Method 1 : Optimal Solution
    // Time Complexity : O(N)
    // Space Complexity : O(1)

    // Method to delete all occurrences of a given key in a doubly linked list
    static Node deleteAllOccurences(Node head, int key) {
        Node temp = head; // Initialize a pointer to traverse the list

        while (temp != null) { // Traverse the list until the end
            if (temp.data == key) { // Check if the current node's data matches the key
                if (temp == head) { // Special case: if the node to delete is the head
                    head = head.next; // Move head to the next node
                    if (head != null) { // If there is a next node
                        head.prev = null; // Set its previous pointer to null
                    }
                } else { // For nodes other than the head
                    Node nextNode = temp.next; // Get the next node
                    Node prevNode = temp.prev; // Get the previous node

                    if (nextNode != null) nextNode.prev = prevNode; // Link next node's previous to current node's previous
                    if (prevNode != null) prevNode.next = nextNode; // Link previous node's next to current node's next
                }
            }
            temp = temp.next; // Move to the next node
        }
        return head; // Return the modified head of the list
    }

    // Method to print the doubly linked list
    static void printList(Node head) {
        Node temp = head; // Initialize a pointer to traverse the list
        while (temp != null) { // Traverse the list until the end
            System.out.print(temp.data + " "); // Print the data of the current node
            temp = temp.next; // Move to the next node
        }
        System.out.println(); // Print a new line at the end
    }

    // Main Function
    public static void main(String[] args) {
        // Create the doubly linked list: 2 <-> 4 <-> 4 <-> 8 <-> 10 <-> 4 <-> 12
        Node head = new Node(2);
        head.next = new Node(4);
        head.next.prev = head;
        head.next.next = new Node(4);
        head.next.next.prev = head.next;
        head.next.next.next = new Node(8);
        head.next.next.next.prev = head.next.next;
        head.next.next.next.next = new Node(10);
        head.next.next.next.next.prev = head.next.next.next;
        head.next.next.next.next.next = new Node(4);
        head.next.next.next.next.next.prev = head.next.next.next.next;
        head.next.next.next.next.next.next = new Node(12);
        head.next.next.next.next.next.next.prev = head.next.next.next.next.next;

        // Print the original list
        System.out.println("Original list:");
        printList(head);

        // Delete all occurrences of 4
        head = deleteAllOccurences(head, 4);

        // Print the modified list
        System.out.println("Modified list after deleting all occurrences of 4:");
        printList(head);
    }
}

// Output :
/*
Original list:
2 4 4 8 10 4 12
Modified list after deleting all occurrences of 4:
2 8 10 12
 */

// Approach : Optimal Solution
/*
Algorithm :

1. Initialize a pointer temp to the head of the list.
2. While temp is not null, do:
    1. If temp.data is equal to key, do:
        1. If temp is the head, do:
            1. Move head to head.next.
            2. If head is not null, set head.prev to null.
        2. Else, do:
            1. Let nextNode be temp.next.
            2. Let prevNode be temp.prev.
            3. If nextNode is not null, set nextNode.prev to prevNode.
            4. If prevNode is not null, set prevNode.next to nextNode.
    2. Move temp to temp.next.
3. Return the modified head.
 */

// Striver (Video Explanation) : https://www.youtube.com/watch?v=Mh0NH_SD92k