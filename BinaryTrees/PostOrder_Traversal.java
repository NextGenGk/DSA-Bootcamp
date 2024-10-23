package BinaryTrees;

import java.util.ArrayList;
import java.util.List;

public class PostOrder_Traversal {

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
    static void postOrder(Node root, List<Integer> arr) {
        // If the current node is NULL
        // (base case for recursion), return
        if (root == null) {
            return;
        }

        // Recursively traverse the left subtree
        postOrder(root.left, arr);
        // Recursively traverse the right subtree
        postOrder(root.right, arr);
        // Push the current node's value into the list
        arr.add(root.data);
    }

    // Function to initiate preorder traversal
    // and return the resulting list
    static List<Integer> postOrder(Node root) {
        // Create an empty list to
        // store preorder traversal values
        List<Integer> arr = new ArrayList<>();
        // Call the preorder traversal function
        postOrder(root, arr);
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
        List<Integer> result = postOrder(root);

        // Displaying the preorder traversal result
        System.out.print("Postorder Traversal: ");
        // Output each value in the
        // preorder traversal result
        for (int val : result) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}

// Output : Postorder Traversal: 4 5 2 3 1

// Approach / Intuition : Optimal Solution
/*
Left -> Right -> Root

Post-order Traversal works by recursively doing a Post-order Traversal of the left subtree and
the right subtree, followed by a visit to the root node.
 */

// Time & Space Complexity :
/*
Time Complexity: O(N) where N is the number of nodes in the binary tree as each node of the binary
tree is visited exactly once.

Space Complexity: O(N) where N is the number of nodes in the binary tree as an additional space
for array is allocated to store the values of all ‘N’ nodes of the binary tree.
 */
