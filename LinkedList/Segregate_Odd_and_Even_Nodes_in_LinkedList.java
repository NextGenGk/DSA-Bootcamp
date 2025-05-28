package LinkedList;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Segregate_Odd_and_Even_Nodes_in_LinkedList {

    // Node class represents a node in a linked list
    static class Node {
        // Data stored in the node
        int data;
        // Pointer to the next node in the list
        Node next;

        // Constructor with both data
        // and next node as parameters
        Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        // Constructor with only data as
        // a parameter, sets next to null
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Method 1 : Brute Force (Using ArrayList)
    // Time Complexity : O(2N) ~ O(N) for traversing
    // Space Complexity : O(N) for using ArrayList Data Structure
    static Node segregateToOddEven(Node head) {
        Node temp = head;;
        ArrayList<Integer> list = new ArrayList<Integer>();

        // If we have odd number of linked list 
        // then we will end up missing last element
        // means temp.next never goes inside the loop)
        while (temp != null && temp.next != null) {
            list.add(temp.data);
            temp = temp.next.next;
        }
        // so, there is a check
        if (temp != null) list.add(temp.data);

        temp = head.next;
        // If we have odd number of linked list 
        // then we will end up missing last element
        // means temp.next never goes inside the loop)
        while (temp != null && temp.next != null) {
            list.add(temp.data);
            temp = temp.next.next;
        }
        // so, there is a check
        if (temp != null) list.add(temp.data);

        int i=0;
        temp = head;
        while (temp != null) {
            temp.data = list.get(i);
            i++;
            temp = temp.next;
        }
        return head;
    }

    // Method 2 : Optimal Solution (Using Odd Even Solution)
    // Time Complexity : O(N/2) + O(N/2) ~ O(N) for traversing
    // Space Complexity : O(1)
    static Node segregateToOddEven1(Node head) {
        if (head == null || head.next == null) return head;

        Node odd = head;
        Node even = head.next;
        Node evenHead = even; // Save the head of the even list to connect later

        // Loop to rearrange nodes into odd and even lists
        // we use even because odd is bound to even and evey step 
        // the even is always ahead of odd so, that's why we use even
        /*
        > If even or even.next becomes null, it means there are no more even nodes left to process, 
          and the rearrangement is complete.
        > If you used odd instead, you might run into a NullPointerException or skip important checks, 
          because even is always ahead (or at the same position) compared to odd.
        > So, using even in the condition ensures the loop only proceeds while there are both odd and even
          nodes to process safely. This is explicitly mentioned in the comments of the code:
        */
        while (even != null && even.next != null) {
            odd.next = odd.next.next;
            even.next = even.next.next;

            odd = odd.next;
            even = even.next;
        }
        odd.next = evenHead; // Connect odd list with even list
        return head;
    }

    // Function to print the linked list
    static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    // Main Function
    public static void main(String[] args) {
        // Creating the linked list
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        System.out.println("Original list:");
        printList(head);

        // Segregating odd and even nodes
        head = segregateToOddEven1(head);

        System.out.println("List after segregation:");
        printList(head);
    }
}

// Output :
/*
Original list:
1 2 3 4 5 6
List after segregation:
1 3 5 2 4 6
 */

// Approach : Brute Force
/*
1. Store even and odd linked list values in arraylist
2. After that, traverse the arraylist and linked list and replacing the value of linked list data with
arraylist data
 */

// Approach : Optimal Solution
/*
1. Changing the link for the odd index
2. Changing the link for the even index
 */

// Striver (Video Explanation) : https://www.youtube.com/watch?v=qf6qp7GzD5Q
