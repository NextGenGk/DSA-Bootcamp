package BinarySearchTrees;

public class Find_Max_Value_in_BST {

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

    // Function to perform an in-order traversal
    // of a binary tree and print its nodes.
    public static void printInOrder(TreeNode root) {
        // Check if the current node is null (base case for recursion).
        if (root == null) {
            return;
        }

        // Recursively call printInOrder for the left subtree.
        printInOrder(root.left);

        // Print the value of the current node.
        System.out.print(root.val + " ");

        // Recursively call printInOrder for the right subtree.
        printInOrder(root.right);
    }

    // Method 1 : Optimal Solution

    // Time Complexity : O(H) (height of the tree, for a skewed tree H = N)
    // Space Complexity : O(1) No extra memory is used, except for a few variables.

    // Function to find the maximum value in a BST.
    public static int maxValue(TreeNode root) {
        // If the tree is empty, return -1.
        if (root == null) return -1;

        // Traverse to the rightmost node.
        while (root.right != null) {
            root = root.right;
        }

        // Return the value of the rightmost node.
        return root.val;
    }

    // Main Function
    public static void main(String[] args) {
        // Creating a BST.
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(10);

        System.out.println("Binary Search Tree (In-Order Traversal):");
        printInOrder(root);
        System.out.println();

        // Finding the minimum value in the BST.
        int max = maxValue(root);
        System.out.println("Maximum value in the BST: " + max);
    }
}

// Algorithm / Intuition : Optimal Solution
/*
Intuition:
In a BST, larger values are always stored in the left subtree. By following the right
child pointers, you reach the larger value in the tree.

Algorithm
Finding the Maximum Value
Steps:
1. Start at the root of the BST.
2. While the right child of the current node exists, move to the right child.
3. When no right child exists, the current node contains the maximum value.
 */