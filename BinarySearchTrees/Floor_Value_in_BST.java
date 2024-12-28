package BinarySearchTrees;

public class Floor_Value_in_BST {

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

    // Function to find the floor of a key
    // in a Binary Search Tree (BST)
    public static int floorInBST(TreeNode root, int key) {
        // Initialize the floor variable
        // to store the floor value
        int floor = -1;

        // Traverse the BST until reaching
        // the end or finding the key
        while (root != null) {
            // If the key is found, assign it
            // as the floor value and return
            if (root.val == key) {
                floor = root.val;
                return floor;
            }

            // If the key is greater than the current
            // node's value, move to the right subtree
            if (key > root.val) {
                // Update the floor with the current node's
                // value and move to the right subtree
                floor = root.val;
                root = root.right;
            } else {
                // If the key is smaller than the current
                // node's value, move to the left subtree
                root = root.left;
            }
        }
        // Return the computed floor value
        return floor;
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

        System.out.println("Binary Search Tree:");
        printInOrder(root);
        System.out.println();

        // Searching for a value in the BST
        int target = 8;
        int ciel = floorInBST(root, target);

        if (ciel != -1) {
            System.out.println("Floor of " + target + " is: " + ciel);
        } else {
            System.out.println("No floor found!");
        }
    }
}

// Output :
/*
Binary Search Tree:
2 3 4 5 6 9 10 11 13 14
Floor of 8 is: 6
 */

// Algorithm / Intuition : Optimal Solution
/*
Algorithm / Intuition
To find the `floor` value, the approach involves tracking the largest node value encountered
that is smaller than or equal to the key. It traverses the tree, either finding the exact
key or reaching the nodes that approach the given key’s value. During the traversal,
at every node, if the key matches the node’s values, it directly assigns the node’s value
as the floor and concludes the search. If the key is smaller than the current node’s value,
the algorithm navigates to the left subtree to potentially find a smaller value and if the
key is larger the algorithm updates the floor value with the current node’s values and
explores the right subtree for potentially larger values..

Algorithm:
Step 1:Initialise a variable `floor` to -1 to store the ceiling value initially.
Step 2: Traverse the Binary Search Tree by starting from the root and continue
until reaching the end of the tree or finding the key. At every node:

    i. If the key value is equal to the node value, assign it as the floor value and return.
   ii. If the key value is greater than the current node’s value, update the `floor`
   with the current node’s value, and move to the right subtree.
If the key is smaller than the current node’s value, move to the left subtree since the
potential floor value should be smaller.
Step 3: Return the computed `floor` value if the key is not found in the tree. This `floor`
value would represent the largest node value smaller than the key, or -1 if no such value
exists in the BST.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=xm_W1ub-K-w
