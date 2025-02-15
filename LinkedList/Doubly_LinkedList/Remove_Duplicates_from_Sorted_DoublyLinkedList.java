package LinkedList.Doubly_LinkedList;

public class Remove_Duplicates_from_Sorted_DoublyLinkedList {

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
    // Time Complexity : O(N) because the outer while for traverses only when there is no duplicates
    // and the inner while loop traverses only when duplicate values is present.
    // Space Complexity : O(1) no extra space is using
    // Method to remove duplicates from a sorted doubly linked list
    static Node removeDups(Node head) {
        Node temp = head; // Start from the head of the list

        // Traverse the list
        while (temp != null && temp.next != null) {
            Node nextNode = temp.next; // Get the next node

            // Skip all nodes that have the same data as the current node
            while (nextNode != null && nextNode.data == temp.data) {
                nextNode = nextNode.next;
            }

            // Link the current node to the next unique node
            temp.next = nextNode;

            // Means, if there is no matching element and, If nextNode is not null,
            // set its prev to the current node
            if (nextNode != null) nextNode.prev = temp;

            // Move to the next node in the list
            temp = temp.next;
        }

        return head; // Return the modified head
    }

    // Method to print the doubly linked list
    static void printList(Node head) {
        Node temp = head; // Start from the head of the list

        // Traverse the list
        while (temp != null) {
            System.out.print(temp.data + " "); // Print the data
            temp = temp.next; // Move to the next node
        }

        System.out.println(); // Print a new line at the end
    }

    // Main Function
    public static void main(String[] args) {
        // Creating a sorted doubly linked list with duplicates
        Node head = new Node(1);
        head.next = new Node(1);
        head.next.prev = head;
        head.next.next = new Node(2);
        head.next.next.prev = head.next;
        head.next.next.next = new Node(3);
        head.next.next.next.prev = head.next.next;
        head.next.next.next.next = new Node(3);
        head.next.next.next.next.prev = head.next.next.next;

        System.out.println("Original Doubly Linked List:");
        printList(head); // Print the original list

        // Removing duplicates
        head = removeDups(head);

        System.out.println("Doubly Linked List after removing duplicates:");
        printList(head); // Print the modified list
    }
}

// Output :
/*
Original Doubly Linked List:
1 1 2 3 3
Doubly Linked List after removing duplicates:
1 2 3
 */

// Approach : Optimal Solution
/*
1. Initialize Temporary Node:
Start from the head of the list.

2. Traverse the List:
Continue traversing until you reach the end of the list (i.e., temp becomes null).

3. Skip Duplicates:
For each node (temp), identify and skip all subsequent nodes (nextNode) that have the same data as temp.

4. Update Pointers:
Link the current node (temp) to the next unique node (nextNode).
If nextNode is not null, update its prev pointer to point back to temp.

5. Move to the Next Node:
Continue to the next node in the list by updating temp to temp.next.

6. Return Modified List:
After processing all nodes, return the head of the modified list.
 */

// Striver (Video Explanation) :https://www.youtube.com/watch?v=YJKVTnOJXSY
