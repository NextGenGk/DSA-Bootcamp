package LinkedList;

import java.util.HashMap;

public class Find_Intersection_Point_in_Y_LinkedList {

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

    // Method 1 : Brute Force
    // Time Complexity : O(N1 + N2)
    // Space Complexity : o(N) for Map Data Structure
    static Node findIntersectionPoint(Node head1, Node head2) {
        // If either of the heads is null, there can't be an intersection
        if (head1 == null || head2 == null) return null;

        // Create a HashMap to store nodes of the first linked list
        HashMap<Node, Integer> map = new HashMap<Node, Integer>();
        Node temp = head1;

        // Traverse the first linked list and add each node to the HashMap
        while (temp != null) {
            map.put(temp, 1);
            temp = temp.next;
        }

        // Traverse the second linked list
        temp = head2;
        while (temp != null) {
            // If the current node of the second list is found in the HashMap,
            // it means there is an intersection at this node
            if (map.containsKey(temp)) {
                return temp;
            }
            temp = temp.next;
        }

        // If no intersection is found, return null
        return null;
    }

    // Method 2 : Better Solution
    // Time Complexity : O(N1) + O(N2) + O(N2 - N1) + O(N1) ~ O(N1 + 2N2)
    // Space Complexity : O(1)
    static Node findIntersectionPoint1(Node head1, Node head2) {
        // If either of the heads is null, there can't be an intersection
        if (head1 == null || head2 == null) return null;

        Node temp1 = head1;
        Node temp2 = head2;
        int N1 = 0;
        int N2 = 0;

        // Calculate the length of the first linked list
        while (temp1 != null) {
            N1++;
            temp1 = temp1.next;
        }

        // Calculate the length of the second linked list
        while (temp2 != null) {
            N2++;
            temp2 = temp2.next;
        }

        // Determine which list is longer and find the collision point
        // If the first list is shorter, swap the parameters to make head1 always the shorter list
        if (N1 < N2) {
            return collisionPoint(head1, head2, N2 - N1);
        } else {
            return collisionPoint(head2, head1, N1 - N2);
        }
    }

    // Find the collision point fn
    private static Node collisionPoint(Node head1, Node head2, int distance) {
        Node temp1 = head1;
        Node temp2 = head2;

        // Traverse the longer list by the distance difference
        while (distance > 0) {
            distance--;
            temp2 = temp2.next;
        }

        // Traverse both lists together until the collision point is found
        while (temp1 != null && temp2 != null) {
            // If both nodes are the same, we found the intersection
            if (temp1 == temp2) {
                return temp1;
            }
            temp1 = temp1.next;
            temp2 = temp2.next;
        }

        // If no collision point is found, return null
        return null;
    }

    // Method 3 : Optimal Solution
    // Time Complexity : O(N1 + N2) for traversing the nodes of N1 and N2
    // Space Complexity : O(1)
    static Node findIntersectionPoint2(Node head1, Node head2) {
        // If either of the heads is null, there can't be an intersection
        if (head1 == null || head2 == null) return null;

        Node temp1 = head1;
        Node temp2 = head2;

        // Traverse both lists
        // means both the list are not same then perform this whils condition
        // (means in both the list the first element is the intersection point)
        while (temp1 != temp2) {
            // Move to the next node in the list
            temp1 = temp1.next;
            temp2 = temp2.next;

            // If both pointers are equal after moving, that's the intersection
            if (temp1 == temp2) return temp1;

            // If temp1 reaches the end of its list, start from head2
            if (temp1 == null) temp1 = head2;

            // If temp2 reaches the end of its list, start from head1
            if (temp2 == null) temp2 = head1;
        }

        // Return the intersection node (could be null if no intersection)
        return temp1;
    }

    // Main Function
    public static void main(String[] args) {
        // Creating two linked lists
        Node common = new Node(15);
        common.next = new Node(30);

        Node head1 = new Node(3);
        head1.next = new Node(6);
        head1.next.next = new Node(9);
        head1.next.next.next = common; // Intersection starts here

        Node head2 = new Node(10);
        head2.next = common; // Intersection starts here

        // Finding intersection point
        Node intersection = findIntersectionPoint1(head1, head2);

        // Printing the intersection point
        if (intersection != null) {
            System.out.println("The intersection point is: " + intersection.data);
        } else {
            System.out.println("There is no intersection point.");
        }
    }
}

// Output : The intersection point is: 15

// Approach : Brute Force
/*
1. In this approach we are using hashing technique to memorize the node, if you see any node which has been
visited twice it means this is our first intersection point.
 */

// Approach : Better Solution
/*
We will reduce the search length. This can be done by searching the length of the shorter linked list. How? Let’s see the process.

1. Find the length of both lists.
2. Find the positive difference between these lengths.
3. Move the dummy pointer of the larger list by the difference achieved. This makes our search length reduced
to a smaller list length.
4. Move both pointers, each pointing two lists, ahead simultaneously if both do not collide.
 */

// Approach : Optimal Solution
/*
Algorithm :

1. Take two dummy nodes for each list point each to the head of the list.
2. Iterate over the list, if any of the list becomes null, then point to the opposite list and continue
iterating until they collide.
 */

// Striver (Video Explanation) : https://www.youtube.com/watch?v=0DYoPz2Tpt4
