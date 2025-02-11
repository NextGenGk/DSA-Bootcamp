package LinkedList;

import java.util.ArrayList;
import java.util.Collections;

public class Flattening_a_LinkedList {

    // Node class
    static class Node {
        int data;
        Node next;
        Node child;

        // Constructors to initialize the
        // data, next, and child pointers
        Node() {
            data = 0;
            next = null;
            child = null;
        }

        Node(int x) {
            data = x;
            next = null;
            child = null;
        }

        Node(int x, Node nextNode, Node childNode) {
            data = x;
            next = nextNode;
            child = childNode;
        }
    }

    // Method 1 : Brute Force
    // Time Complexity: O(N*M) + O(N*M log(N*M)) + O(N*M)where N is the length of the linked list along
    // the next pointer and M is the length of the linked list along the child pointer.
    // O(N*M) as we traverse through all the elements, iterating through ‘N’ nodes along the next pointer
    // and ‘M’ nodes along the child pointer.
    // O(N*M log(N*M)) as we sort the array containing N*M (total) elements.
    // O(N*M) as we reconstruct the linked list from the sorted array by iterating over the N*M elements of the array.
    // Space Complexity : O(N*M) + O(N*M)where N is the length of the linked list along the next pointer and M is
    // the length of the linked list along the child pointer.
    // O(N*M) for storing all the elements in an additional array for sorting.
    // O(N*M) to reconstruct the linked list from the array after sorting

    // Function to flatten a linked list with child pointers
    static Node flattenLinkedList(Node head) {
        ArrayList<Integer> arr = new ArrayList<>();

        // Traverse through the linked list
        while (head != null) {
            // Traverse through the child
            // nodes of each head node
            Node t2 = head;
            while (t2 != null) {
                // Store each node's data in the ArrayList
                arr.add(t2.data);
                // Move to the next child node
                t2 = t2.child;
            }
            // Move to the next head node
            head = head.next;
        }

        // Sort the ArrayList containing
        // node values in ascending order
        Collections.sort(arr);

        // Convert the sorted ArrayList
        // back to a linked list
        return convertArrToLinkedList(arr);
    }

    // Function to convert an ArrayList to a linked list
    static Node convertArrToLinkedList(ArrayList<Integer> arr) {
        // Create a dummy node to serve as
        // the head of the linked list
        Node dummyNode = new Node(-1);
        Node temp = dummyNode;

        // Iterate through the ArrayList and
        // create nodes with elements
        for (int i = 0; i < arr.size(); i++) {
            // Create a new node with the element
            temp.child = new Node(arr.get(i));
            // Move the temporary pointer
            // to the newly created node
            temp = temp.child;
        }
        // Return the linked list starting
        // from the next of the dummy node
        return dummyNode.child;
    }

    // Method 2 : Optimal Solution
    // Time Complexity: O( N*(2M) ) ~ O(2 N*M)where N is the length of the linked list along the next pointer
    // and M is the length of the linked list along the child pointers.
    // 1. The merge operation in each recursive call takes time complexity proportional to the length of the
    // linked lists being merged as they have to iterate over the entire lists. Since the vertical depth
    // of the linked lists is assume to be M, the time complexity for a single merge operation is
    // proportional to O(2*M).
    // 2. This operation operation is performed N number of times (to each and every node along the next
    // pointer list) hence the resultant time complexity becomes: O(N* 2M).
    // Space Complexity : O(1) as this algorithm uses no external space or additional data structures
    // to store values. But a recursive stack uses O(N) space to build the recursive calls for each node
    // along the next pointer list.

    // Merges two linked lists in a particular
    // order based on the data value
    public static Node merge(Node list1, Node list2) {
        // Create a dummy node as a
        // placeholder for the result
        Node dummyNode = new Node(-1);
        Node res = dummyNode;

        // Merge the lists based on data values
        while (list1 != null && list2 != null) {
            if (list1.data < list2.data) {
                res.child = list1;
                res = list1;
                list1 = list1.child;
            } else {
                res.child = list2;
                res = list2;
                list2 = list2.child;
            }
            res.next = null;
        }

        // Connect the remaining
        // elements if any
        if (list1 != null) {
            res.child = list1;
        } else {
            res.child = list2;
        }

        // Break the last node's
        // link to prevent cycles
        if (dummyNode.child != null) {
            dummyNode.child.next = null;
        }

        return dummyNode.child;
    }

    // Flattens a linked list with child pointers
    public static Node flattenLinkedList2(Node head) {
        // If head is null or there
        // is no next node, return head
        if (head == null || head.next == null) {
            return head;
        }

        // Recursively flatten the
        // rest of the linked list
        Node mergedHead = flattenLinkedList2(head.next);
        head = merge(head, mergedHead);
        return head;
    }

    // Print the linked list by
    // traversing through child pointers
    static void printLinkedList(Node head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.child;
        }
        System.out.println();
    }

    // Print the linked list
    // in a grid-like structure
    static void printOriginalLinkedList(Node head, int depth) {
        while (head != null) {
            System.out.print(head.data);

            // If child exists, recursively
            // print it with indentation
            if (head.child != null) {
                System.out.print(" -> ");
                printOriginalLinkedList(head.child, depth + 1);
            }

            // Add vertical bars
            // for each level in the grid
            if (head.next != null) {
                System.out.println();
                for (int i = 0; i < depth; ++i) {
                    System.out.print("| ");
                }
            }
            head = head.next;
        }
    }

    // Main Function
    public static void main(String[] args) {
        // Create a linked list with child pointers
        Node head = new Node(5);
        head.child = new Node(14);

        head.next = new Node(10);
        head.next.child = new Node(4);

        head.next.next = new Node(12);
        head.next.next.child = new Node(20);
        head.next.next.child.child = new Node(13);

        head.next.next.next = new Node(7);
        head.next.next.next.child = new Node(17);

        // Print the original
        // linked list structure
        System.out.println("Original linked list:");
        printOriginalLinkedList(head, 0);

        // Flatten the linked list
        // and print the flattened list
        Node flattened = flattenLinkedList2(head);
        System.out.println("\nFlattened linked list: ");
        printLinkedList(flattened);
    }
}

// Output :
/*
Original linked list:
5 -> 14
10 -> 4
12 -> 20 -> 13
7 -> 17
Flattened linked list:
5 7 10 4 12 14 17 20 13
 */

// Approach : Brute Force
/*
Algorithm / Intuition
To transform the given linked list into a single level sorted list ensuring that the nodes are arranged in
an ascending order, we initialise an array to temporarily hold the extracted nodes during the traversal.

We iterate over the array by first going over the top-level next pointers of the linked list then
accessing each node within its child pointers adding all to the array. Then the array is sorted to
arrange all values sequentially and a new linked list from that array is created and returned.

Algorithm :

Step 1:Initialise an empty array to store the data extracted during the traversal.

Step 2: Start traversing through the top-level ‘next’ pointers of the linked list and for each node accessed by
the ‘next’ pointer, traverse its ‘child’ nodes.

    1. Iterate all the nodes until reaching the end of the child pointer list appending each node’s value to
       the array. Move to the next primary node and repeat the process of traversing the child nodes.

Step 3: Sort the array to arrange its collected node data in ascending order.

Step 4: Create a new linked list from the sorted array and return the flattened linked list.
 */

// Approach : Optimal Solution
/*
The time and space complexity of the previous approach can be optimised as we have not yet leveraged
the given property that the child linked lists are sorted. We can eliminate the additional space and
time complexity generated by sorting by using these sorted vertical linked lists.

Instead of collecting all node values into an array and then sorting them, we can merge these
pre-sorted lists directly during the traversal, eliminating the need for additional sorting steps.
This merge operation can be performed efficiently in place without allocating extra space for the
combined linked list.

Read more about Merging Sorted Linked Lists. The base case ensures the termination of the recursion
when there's either no list or only a single node remaining. The recursive function then handles the
merging of the remaining lists after recursive flattening, creating a sorted flattened linked list.

Algorithm :

Recursive Function: The core of the algorithm lies in implementing a recursive function responsible
for flattening the linked list. The function operates based on the principle that:

If the base conditions are not met, the function invokes itself recursively. This recursion
continues until it reaches the base case, gradually flattening the linked list and merging
the resultant with the previous node.

Return:Following the recursion, the function returns the merged head of the new flattened linked list. This head
marks the new head of the merged list starting from the end, which will now be merged with the present head.

Base Case:

1. If the head is null, indicating the end of the list, it is already flattened or there are no
further nodes. Return the head as it is.
2. Similarly, if there's no next node, meaning there's only one node left in the list, return the
head as it is since it's already flattened.

Step 1: Establish Base Case Conditions Check if the base case conditions are met, return the head if it is null
or has no next pointer to head as there’s no further flattening or merging required.

Step 2: Recursively Merge the List:

1. Initiate the recursive flattening process by calling `flattenLinkedList` on the next node (`head -> next`).
2. The result of this recursive call is the head of the flattened and merged linked list.

Step 3: Merge Operations:

1. Inside the recursive call, call the merge function which takes care of the merging of these two lists
   based on their data values.
2. Read more about merging two linked lists here.
3. The merged list is updated in the head, which is then returned as the result of the flattening process.
 */

// Striver (Video Explanation) : https://www.youtube.com/watch?v=ykelywHJWLg
