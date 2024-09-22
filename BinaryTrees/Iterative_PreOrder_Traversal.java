package BinaryTrees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Iterative_PreOrder_Traversal {

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

    // Method 1 : Optimal Solution
    // Time Complexity: O(N) where N is the number of nodes in the binary tree. Every node of the binary
    // tree is visited exactly once, and for each node, , the operations performed (pushing and popping
    // from the stack, accessing node values, etc.) are constant time operations.
    // Space Complexity: O(N) where N is the number of nodes in the binary tree. This is because the
    // stack can potentially hold all nodes in the tree when dealing with a skewed tree (all nodes
    // have only one child), consuming space proportional to the number of nodes.

    // Function to perform preorder traversal
    // of a binary tree iteratively
    public static List<Integer> preorderTraversal(Node root) {
        // Initialize list to store
        // the preorder traversal result
        List<Integer> preorder = new ArrayList<>();

        // If the root is null, return
        // an empty traversal result
        if (root == null) {
            return preorder;
        }

        // Create a stack to store
        // nodes during traversal
        Stack<Node> stack = new Stack<>();
        // Push the root node
        // onto the stack
        stack.push(root);

        // Perform iterative preorder traversal
        while (!stack.empty()) {
            // Get the current node
            // from the top of the stack
            root = stack.pop();

            // Add the node's value to
            // the preorder traversal result
            preorder.add(root.data);

            // Push the right child
            // onto the stack if exists
            if (root.right != null) {
                stack.push(root.right);
            }

            // Push the left child onto
            // the stack if exists
            if (root.left != null) {
                stack.push(root.left);
            }
        }

        // Return the preorder traversal result
        return preorder;
    }

    // Main function
    public static void main(String[] args) {
        // Creating a sample binary tree
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        // Perform level-order traversal
        List<Integer> result = preorderTraversal(root);

        System.out.println("Level Order Traversal of Tree : " + result);
    }
}

// Output : Level Order Traversal of Tree : [1, 2, 4, 5, 3, 6, 7]

// Approach / Intuition
/*
This code performs an iterative **preorder traversal** of a binary tree using a stack. Preorder
traversal follows the order: root, left subtree, then right subtree. The algorithm starts by pushing
the root node onto the stack, then repeatedly pops a node, adds its value to the result list, and pushes
its right child first (if it exists), followed by the left child. This ensures that the left child is
processed before the right child, maintaining the correct traversal order. The process continues until
all nodes are visited, and the final list of node values is returned.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=Bfqd8BsPVuw