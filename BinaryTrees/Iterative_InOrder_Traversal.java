package BinaryTrees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Iterative_InOrder_Traversal {

    // Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // Method 1 : Optimal Solution
    // Time Complexity: O(N) where N is the number of nodes in the binary tree. Every node of the binary
    // tree is visited exactly once, and for each node, , the operations performed (pushing and popping
    // from the stack, accessing node values, etc.) are constant time operations.
    // Space Complexity: O(N) where N is the number of nodes in the binary tree. This is because the
    // stack can potentially hold all nodes in the tree when dealing with a skewed tree (all nodes
    // have only one child), consuming space proportional to the number of nodes.

    // Function to perform inorder traversal
    // of a binary tree iteratively
    public static List<Integer> inOrder(TreeNode root) {
        // Initialize a list to store the inorder traversal result
        List<Integer> inorder = new ArrayList<Integer>();
        // Stack to track nodes for visiting
        Stack<TreeNode> stack = new Stack<TreeNode>();
        // Start with the root node
        TreeNode node = root;

        // Loop until all nodes are visited
        while (true) {
            // Traverse the left subtree
            if (node != null) {
                stack.push(node);  // Push current node and move left
                node = node.left;
            } else {
                // If left subtree is done and stack is empty, traversal is complete
                if (stack.isEmpty()) break;

                // Visit the node and move to the right subtree
                node = stack.pop();  // Pop the node from the stack
                inorder.add(node.val);  // Add node's value to the result list
                node = node.right;  // Move to right child
            }
        }
        // Return the inorder traversal result
        return inorder;
    }

    // Main function
    public static void main(String[] args) {
        // Creating a sample binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        // Getting inorder traversal
        List<Integer> result = inOrder(root);

        // Displaying the inorder traversal result
        System.out.print("Inorder Traversal: ");
        // Output each value in the
        // inorder traversal result
        for (int val : result) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}

// Output : Inorder Traversal: 4 2 5 1 3

// Approach / Intuition
/*
This code performs an **iterative inorder traversal** of a binary tree using a stack. The inorder
sequence visits nodes in the order: left subtree, root, then right subtree. The algorithm starts by
pushing nodes from the current node down to its leftmost descendant onto the stack. Once the left
subtree is exhausted, the node is popped from the stack, added to the result list, and the traversal
shifts to the right subtree. This process continues until all nodes are visited. The stack ensures
that nodes are visited in the correct inorder sequence without recursion.

Patience and persistence overcome the greatest obstacles, just like trees reveal their beauty,
one leaf at a time.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=lxTGsVXjwvM