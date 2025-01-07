package BinarySearchTrees;

import java.util.ArrayList;
import java.util.List;

public class Find_Inorder_Predecessor {

    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        // Constructor for an empty node.
        TreeNode() {
        }

        // Constructor to create a node with a specific value.
        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }

        // Constructor to create a node with a specific value and left and right children.
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // Method 1 : Brute Force Approach
    // Time Complexity: O(n), where n is the number of nodes in the tree
    // (since we need to traverse the entire tree to collect the inorder list).
    // Space Complexity: O(n), due to the list used for storing the inorder traversal.

    // This method performs an inorder traversal to collect all node values in a list,
    // then it finds the predecessor of the target by looking at the previous value in the list.
    static int findInorderPredecessorBrute(TreeNode root, int target) {
        // List to store inorder traversal
        List<Integer> inorderList = new ArrayList<>();
        inorderTraversal(root, inorderList);

        // Find the target and return the previous element
        for (int i = 0; i < inorderList.size(); i++) {
            if (inorderList.get(i) == target) {
                return (i > 0) ? inorderList.get(i - 1) : -1; // Return predecessor if found
            }
        }
        return -1; // Return -1 if not found
    }

    // Helper function to perform inorder traversal and populate the list
    static void inorderTraversal(TreeNode root, List<Integer> inorderList) {
        if (root == null) return;
        inorderTraversal(root.left, inorderList);
        inorderList.add(root.val);
        inorderTraversal(root.right, inorderList);
    }

    // Method 2 : Optimal Approach
    // Time Complexity: O(h), where h is the height of the tree
    // (since we only traverse from the root to the target node).
    // Space Complexity: O(1), as we only use a few pointers
    // (no additional space is required).

    // This method finds the inorder predecessor by directly navigating the BST
    // using the BST property (left subtree is smaller, right subtree is larger).
    static TreeNode findInorderPredecessorOptimal(TreeNode root, int target) {
        TreeNode predecessor = null;

        while (root != null) {
            if (root.val < target) {
                // If current node's value is smaller than target, update the predecessor
                predecessor = root;
                root = root.right; // Move to right subtree for closer predecessor
            } else {
                root = root.left; // Otherwise, move to left subtree
            }
        }

        return predecessor; // Return the predecessor node (or null if not found)
    }

    // Main Function
    public static void main(String[] args) {
        // Example tree:
        //       20
        //      /  \
        //    10    30
        //    / \
        //   5   15

        // Constructing the tree
        TreeNode root = new TreeNode(20);
        root.left = new TreeNode(10);
        root.right = new TreeNode(30);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(15);

        // Example 1: Using Brute Force
        int target = 15;
        int predecessorBrute = findInorderPredecessorBrute(root, target);
        System.out.println("Brute Force: Inorder Predecessor of " + target +
                " is " + (predecessorBrute == -1 ? "None" : predecessorBrute));

        // Example 2: Using Optimal Solution
        TreeNode predecessorOptimal = findInorderPredecessorOptimal(root, target);
        System.out.println("Optimal: Inorder Predecessor of " + target +
                " is " + (predecessorOptimal == null ? "None" : predecessorOptimal.val));
    }
}

// Output :
/*
Brute Force: Inorder Predecessor of 15 is 10
Optimal: Inorder Predecessor of 15 is 10
 */

// Algorithm / Intuition : Brute Force
/*
This method finds the inorder predecessor by first performing an inorder traversal
to get the entire list of node values in sorted order. Then, it searches for the
target in this list and returns the value just before it. This method is not optimal
because it requires the full traversal of the tree.
 */

// Algorithm / Intuition : Optimal Solution
/*
This approach takes advantage of the properties of a Binary Search Tree (BST).
Instead of performing an inorder traversal, it directly searches for the inorder
predecessor by navigating the tree. It is more efficient since it doesn't require
creating a list of all nodes and only takes O(h) time, where h is the height of the tree.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=SXKAD2svfmI