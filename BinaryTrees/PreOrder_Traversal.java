package BinaryTrees;

import java.util.ArrayList;
import java.util.List;

public class PreOrder_Traversal {

    // Node class for the binary tree
    static class Node {
        int data;
        Node left;
        Node right;

        // Constructor to initialize
        // the node with a value
        Node(int val) {
            data = val;
            left = null;
            right = null;
        }
    }

    // Function to perform preorder traversal
    // of the tree and store values in 'arr'
    static void preorder(Node root, List<Integer> arr) {
        // If the current node is NULL
        // (base case for recursion), return
        if (root == null) {
            return;
        }
        // Push the current node's value into the list
        arr.add(root.data);
        // Recursively traverse the left subtree
        preorder(root.left, arr);
        // Recursively traverse the right subtree
        preorder(root.right, arr);
    }

    // Function to initiate preorder traversal
    // and return the resulting list
    static List<Integer> preOrder(Node root) {
        // Create an empty list to
        // store preorder traversal values
        List<Integer> arr = new ArrayList<>();
        // Call the preorder traversal function
        preorder(root, arr);
        // Return the resulting list
        // containing preorder traversal values
        return arr;
    }

    // Main function
    public static void main(String[] args) {
        // Creating a sample binary tree
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);

        // Getting preorder traversal
        List<Integer> result = preOrder(root);

        // Displaying the preorder traversal result
        System.out.print("Preorder Traversal: ");
        // Output each value in the
        // preorder traversal result
        for (int val : result) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}

// Output : Preorder Traversal: 1 2 4 5 3

// Approach / Intuition
/*
Root -> Left -> Right

Pre-order Traversal is done by visiting the root node first, then recursively do a pre-order traversal
of the left subtree, followed by a recursive pre-order traversal of the right subtree.
 */

// Time & Space Complexity :
/*
Time Complexity: O(N) where N is the number of nodes in the binary tree as each node of the binary
tree is visited exactly once.

Space Complexity: O(N) where N is the number of nodes in the binary tree as an additional space
for array is allocated to store the values of all ‘N’ nodes of the binary tree.
 */