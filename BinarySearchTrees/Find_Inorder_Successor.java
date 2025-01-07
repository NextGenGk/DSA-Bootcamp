package BinarySearchTrees;

import java.util.ArrayList;
import java.util.List;

public class Find_Inorder_Successor {

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
    // then it finds the successor of the target by looking at the next value in the list.
    static int findInorderSuccessorBrute(TreeNode root, int target) {
        // List to store inorder traversal
        List<Integer> inorderList = new ArrayList<>();
        inorderTraversal(root, inorderList);

        // Find the target and return the next element
        for (int i = 0; i < inorderList.size(); i++) {
            if (inorderList.get(i) == target) {
                return (i < inorderList.size() - 1) ? inorderList.get(i + 1) : -1; // Return successor if found
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

    // This method finds the inorder successor by directly navigating the BST
    // using the BST property (left subtree is smaller, right subtree is larger).
    static TreeNode findInorderSuccessorOptimal(TreeNode root, int target) {
        TreeNode successor = null;

        while (root != null) {
            if (root.val > target) {
                // If current node's value is greater than target, update the successor
                successor = root;
                root = root.left; // Move to left subtree for closer successor
            } else {
                root = root.right; // Otherwise, move to right subtree
            }
        }

        return successor; // Return the successor node (or null if not found)
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
        int successorBrute = findInorderSuccessorBrute(root, target);
        System.out.println("Brute Force: Inorder Successor of " + target +
                " is " + (successorBrute == -1 ? "None" : successorBrute));

        // Example 2: Using Optimal Solution
        TreeNode successorOptimal = findInorderSuccessorOptimal(root, target);
        System.out.println("Optimal: Inorder Successor of " + target +
                " is " + (successorOptimal == null ? "None" : successorOptimal.val));
    }
}

// Output:
/*
Brute Force: Inorder Successor of 15 is 20
Optimal: Inorder Successor of 15 is 20
 */

// Algorithm / Intuition : Brute Force
/*
This method finds the inorder successor by first performing an inorder traversal
to get the entire list of node values in sorted order. Then, it searches for the
target in this list and returns the value just after it. This method is not optimal
because it requires the full traversal of the tree.
 */

// Algorithm / Intuition : Optimal Solution
/*
This approach takes advantage of the properties of a Binary Search Tree (BST).
Instead of performing an inorder traversal, it directly searches for the inorder
successor by navigating the tree. It is more efficient since it doesn't require
creating a list of all nodes and only takes O(h) time, where h is the height of the tree.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=SXKAD2svfmI