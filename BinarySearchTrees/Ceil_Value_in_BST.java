package BinarySearchTrees;

public class Ceil_Value_in_BST {

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

    // Method 1 : Optimal Solution

    // Time Complexity : O(H), where H is the height of the tree
    // For balanced tree O(H) = O(log2N)
    // For skewed tree O(H)) = O(N)

    // Function to find the ceiling of
    // a key in a Binary Search Tree (BST)
    public static int findCeil(TreeNode root, int key) {
        // Initialize the variable to store the ceiling value
        int ceil = -1;

        // Traverse the BST until reaching
        // the end or finding the key
        while (root != null) {
            // If the key is found, assign it
            // as the ceiling and return
            if (root.val == key) {
                ceil = root.val;
                return ceil;
            }

            // If the key is greater,
            // move to the right subtree
            if (key > root.val) {
                root = root.right;
            } else {
                // If the key is smaller, update ceil
                // and move to the left subtree
                ceil = root.val;
                root = root.left;
            }
        }
        // Return the computed ceiling value
        return ceil;
    }

    // Function to perform an in-order traversal
    // of a binary tree and print its nodes
    public static void printInOrder(TreeNode root) {
        // Check if the current node
        // is null (base case for recursion)
        if (root == null) {
            // If null, return and
            // terminate the function
            return;
        }

        // Recursively call printInOrder
        // for the left subtree
        printInOrder(root.left);

        // Print the value of the current node
        System.out.print(root.val + " ");

        // Recursively call printInOrder
        // for the right subtree
        printInOrder(root.right);
    }

    // Main Function
    public static void main(String[] args) {
        // Creating a BST
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(13);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(2);
        root.left.left.right = new TreeNode(4);
        root.left.right = new TreeNode(6);
        root.left.right.right = new TreeNode(9);
        root.right.left = new TreeNode(11);
        root.right.right = new TreeNode(14);

        System.out.println("Binary Search Tree: ");
        printInOrder(root);
        System.out.println();

        // Searching for a value in the BST
        int target = 8;
        int ceil = findCeil(root, target);

        if (ceil != -1) {
            System.out.println("Ceiling of " + target + " is: " + ceil);
        } else {
            System.out.println("No ceiling found!");
        }
    }
}

// Output :
/*
Binary Search Tree:
2 3 4 5 6 9 10 11 13 14
Ceiling of 8 is: 9
 */

// Algorithm / Intuition : Optimal Solution
/*
Algorithm / Intuition
The strategy to find the `ceil` value is to keep track of the smallest node value
encountered that is greater than or equal to the key. Traverse the tree recursively
and move through it until it reaches the end or locates the key. During the traversal,
at every node, if the key matches the node’s values, it directly assigns the node’s
value as the ceiling and concludes the search. If the key is greater than the current
node’s value, the algorithm navigates to the right subtree to potentially find a
larger value and if the key is smaller the algorithm updates the ceil value with
the current node’s values and explores the left subtree for potentially smaller values.

Algorithm:
Step 1:Initialise a variable `ceil` to -1 to store the ceiling value initially.
Step 2: Traverse the Binary Search Tree by starting from the root and continue until
reaching the end of the tree or finding the key. At every node:
    i. If the key value is equal to the node value, assign it as the ceiling value and return.
   ii. If the key value is greater than the current node’s value, move to the right subtree.
If the key is smaller than the current node’s value, update the `ceil` with the current node’s
value, and move to the left subtree.
Step 3: Return the computed `ceil` value if the key is not found in the tree. This `ceil` value would represent the smallest node value greater than the key, or -1 if no such value exists in the BST.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=KSsk8AhdOZA