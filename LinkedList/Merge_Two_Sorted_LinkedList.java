package LinkedList;

import java.util.ArrayList;
import java.util.Collections;

public class Merge_Two_Sorted_LinkedList {

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
    // Time Complexity : O(N1) + O(N2) + O(NLogN) +O(N);
    // Space Complexity : O(N) for ArrayList
    static Node mergeTwoLists(Node l1, Node l2) {
        ArrayList<Integer> list = new ArrayList<>();

        // Traverse the first linked list and add its elements to the ArrayList
        while (l1 != null) {
            list.add(l1.data);
            l1 = l1.next;
        }

        // Traverse the second linked list and add its elements to the ArrayList
        while (l2 != null) {
            list.add(l2.data);
            l2 = l2.next;
        }

        // Sort the ArrayList
        Collections.sort(list);

        // Convert the ArrayList back to a linked list
        Node dummy = new Node(0);
        Node current = dummy;
        for (int val : list) {
            current.next = new Node(val);
            current = current.next;
        }

        return dummy.next;
    }

    // Method 2 : Optimal Solution (Using Merge Sort)
    // Time Complexity : O(N1 + N2), where N1 is the number of nodes in 1st List and
    // N2 is the number of nodes in 2nd List
    // Space Complexity : O(1)

    static Node mergeTwoLists1(Node l1, Node l2) {
        // Create a dummy node to act as the start of the merged list
        Node dummy = new Node(0);
        Node current = dummy;

        // Traverse both lists and merge them in sorted order
        while (l1 != null && l2 != null) {
            if (l1.data < l2.data) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }

        // Append any remaining nodes from either list
        if (l1 != null) {
            current.next = l1;
        } else {
            current.next = l2;
        }

        // Return the merged list, skipping the dummy node
        return dummy.next;
    }

    // Main Function
    public static void main(String[] args) {
        // Example usage
        Merge_Two_Sorted_LinkedList result = new Merge_Two_Sorted_LinkedList();

        // Create first sorted list: 1 -> 3 -> 5
        Node l1 = new Node(1);
        l1.next = new Node(3);
        l1.next.next = new Node(5);

        // Create second sorted list: 2 -> 4 -> 6
        Node l2 = new Node(2);
        l2.next = new Node(4);
        l2.next.next = new Node(6);

        // Merge lists using ArrayList
        Node mergedList = result.mergeTwoLists1(l1, l2);

        // Print merged list
        while (mergedList != null) {
            System.out.print(mergedList.data + " ");
            mergedList = mergedList.next;
        }
    }
}

// Output : 1 2 3 4 5 6

// Approach : Brute Force
/*
1. We traverse both linked lists and add their values to an ArrayList.
2. We sort the ArrayList.
3. We create a new linked list from the sorted ArrayList.
 */

// Approach : Optimal Solution
/*
Algorithm Explanation
1. Initialize Dummy Node: Create a dummy node to serve as the starting point of the merged list. This simplifies
the logic by avoiding special cases for the head of the list.
2. Merge Nodes: Use a current pointer to build the new list by comparing the data of nodes from l1 and l2. Append
the smaller node to the merged list and move the corresponding list pointer forward.
3. Append Remaining Nodes: After exiting the loop, one of the lists might still have remaining nodes. Append these
remaining nodes to the merged list.
4. Return Merged List: Return the merged list starting from dummy.next to skip the dummy node.
 */

// Striver (Video Explanation) : https://www.youtube.com/watch?v=jXu-H7XuClE