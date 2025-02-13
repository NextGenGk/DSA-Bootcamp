package LinkedList.Doubly_LinkedList;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Find_Pairs_with_Given_Sum_in_DoublyLinkedList {

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

    // Method 1 : Brute Force (Using the logic of two loops : Array)
    // Time Complexity : O(N^2) because traverse each and every node twice
    // Space Complexity : O(1) no extra space is needed

    // Method to find pairs with given sum in a sorted doubly linked list
    static ArrayList<List<Integer>> findPairsWithGivenSum(Node head, int sum) {
        ArrayList<List<Integer>> list = new ArrayList<>();
        Node temp1 = head;

        // Outer loop to pick first element of the pair
        while (temp1 != null) {
            Node temp2 = temp1.next;

            // Inner loop to pick second element of the pair
            while (temp2 != null) {
                // Check if the sum of the pair equals the given sum
                if (temp1.data + temp2.data == sum) {
                    // If pair is found, add it to the result list
                    List<Integer> result = Arrays.asList(temp1.data, temp2.data);
                    list.add(result);
                }
                temp2 = temp2.next;
            }
            temp1 = temp1.next;
        }
        return list;
    }

    // Method 2 : Optimal Solution (Using Two Pointers)
    // Time Complexity : O(N) because we are only moving left and right
    // Space Complexity : O(1) no extra space is needed

    // Method to find pairs with given sum in a sorted doubly linked list
    static ArrayList<List<Integer>> findPairsWithGivenSum2(Node head, int sum) {
        // Initialize an empty list to store the pairs
        ArrayList<List<Integer>> list = new ArrayList<>();
        // Initialize left pointer to the head of the list
        Node left = head;
        // Find the tail of the list and initialize the right pointer to it
        Node right = findTail(head);

        // Traverse the list with two pointers from both ends
        while (left != null && right != null && left != right && left != right.next) {
            // Check if the sum of the current pair equals the target sum
            if (left.data + right.data == sum) {
                // If a pair is found, add it to the result list
                List<Integer> result = Arrays.asList(left.data, right.data);
                list.add(result);
                // Move the left pointer to the next node
                left = left.next;
                // Move the right pointer to the previous node
                right = right.prev;
            }
            // If the sum of the current pair is greater than the target sum
            else if (left.data + right.data > sum) {
                // Move the right pointer to the previous node
                right = right.prev;
            }
            // If the sum of the current pair is less than the target sum
            else {
                // Move the left pointer to the next node
                left = left.next;
            }
        }
        // Return the list of pairs
        return list;
    }

    // Method to find the tail of the doubly linked list
    static Node findTail(Node head) {
        Node tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        return tail;
    }

    // Method to print the pairs
    static void printPairs(ArrayList<List<Integer>> pairs) {
        for (List<Integer> pair : pairs) {
            System.out.println(pair);
        }
    }

    // Main Function
    public static void main(String[] args) {
        // Creating a sorted doubly linked list
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.prev = head;
        head.next.next = new Node(3);
        head.next.next.prev = head.next;
        head.next.next.next = new Node(4);
        head.next.next.next.prev = head.next.next;
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.prev = head.next.next.next;

        int sum = 5; // Sum for which pairs need to be found

        System.out.println("Pairs with given sum " + sum + " are:");
        ArrayList<List<Integer>> pairs = findPairsWithGivenSum2(head, sum);

        // Printing the pairs
        printPairs(pairs);
    }
}



// Approach : Brute Force (Using the logic of 2 loops : Array)
/*
Intuition :
We try all the possible pair which gives the given sum

Algorithm :
1. Initialize an empty list, list to store the pairs.
2. Set temp1 to the head of the doubly linked list.
3. While temp1 is not null:
    i. Set temp2 to temp1.next.
   ii. While temp2 is not null:
        1. If temp1.data + temp2.data equals sum:
            i. Create a pair [temp1.data, temp2.data].
           ii. Add the pair to list.
        2. Move temp2 to the next node (temp2 = temp2.next).
        3. Move temp1 to the next node (temp1 = temp1.next).
4. Return list.
 */

// Approach : Optimal Solution (Using Two Pointers)
/*
Intuition :
In this approach we are using two pointers left and right, left points to 1 node of the list and right points to
the last node of list, then we traverse the list until left value is less than right value and calculate the
sum of left value and right value, if the value of left and right is equal to the given sum, then return the
left and right pointers, else if check if left value and right value is greater than the given sum then we move
the right pointer else we move the left pointer because (i.e the list is sorted).
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=YitR4dQsddE
