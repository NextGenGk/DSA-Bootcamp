package BinaryTrees;

import java.util.ArrayList;
import java.util.List;

public class Morris_Preorder_Traversal {

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

    // Time Complexity: O(2N) where N is the number of nodes in the Binary Tree.
    // The time complexity is linear, as each node is visited at most twice
    // (once for establishing the temporary link and once for reverting it).
    // In each step, we perform constant-time operations, such as moving to the left
    // or right child and updating pointers.

    // Space Complexity: O(1) The space complexity is constant, as the algorithm
    // uses only a constant amount of extra space irrespective of the input size.
    // Morris Traversal does not use any additional data structures like stacks or
    // recursion, making it an in-place algorithm.
    // The only space utilised is for a few auxiliary variables, such as pointers to
    // current and in-order predecessor nodes.

    // Function to perform iterative Morris
    // preorder traversal of a binary tree
    public static List<Integer> getPreorder(TreeNode root) {
        // List to store the
        // preorder traversal result
        List<Integer> preorder = new ArrayList<>();

        // Pointer to the current node,
        // starting with the root
        TreeNode cur = root;

        // Iterate until the
        // current node becomes null
        while (cur != null) {
            // If the current node
            // has no left child
            if (cur.left == null) {
                // Add the value of the
                // current node to the preorder list
                preorder.add(cur.val);

                // Move to the right child
                cur = cur.right;
            } else {
                // If the current node has a left child
                // Create a pointer to traverse to the
                // rightmost node in the left subtree
                TreeNode prev = cur.left;

                // Traverse to the rightmost node in the
                // left subtree or until we find a node
                // whose right child is not yet processed
                while (prev.right != null && prev.right != cur) {
                    prev = prev.right;
                }

                // If the right child of the
                // rightmost node is null
                if (prev.right == null) {
                    // Set the right child of the
                    // rightmost node to the current node
                    prev.right = cur;

                    // Move to the left child
                    cur = cur.left;
                } else {
                    // If the right child of the
                    // rightmost node is not null
                    // Reset the right child to null
                    prev.right = null;

                    // Add the value of the
                    // current node to the preorder list
                    preorder.add(cur.val);

                    // Move to the right child
                    cur = cur.right;
                }
            }
        }

        // Return the resulting
        // preorder traversal list
        return preorder;
    }

    // Main Function
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.right.right = new TreeNode(6);

        List<Integer> preorder = getPreorder(root);

        System.out.print("Binary Tree Morris Preorder Traveral: ");
        for (int i = 0; i < preorder.size(); i++) {
            System.out.print(preorder.get(i) + " ");
        }
        System.out.println();
    }
}

// Output : Binary Tree Morris Preorder Traversal: 4 2 5 6 1 3

// Algorithm / Intuition : Optimal Solution
/*
Algorithm / Intuition
A prerequisite to this problem is Morris Inorder Traversal of Binary Tree.
We extend Morris Inorder Traversal to Preorder Morris Traversal and modify the
algorithm to print the current node’s value before moving to the left child when
the right child of the inorder predecessor is null.

This change ensures that the nodes are processed in the desired order for Preorder
Traversal. The basic structure of Morris Traversal remains intact, but the printing
step is adjusted, resulting in a Preorder Traversal that is still in-place and has
a constant space complexity.

In Morris Inorder Traversal, we are traversing the tree in the way: Left, Root, Right.
In Morris Preorder traversal we want to traverse the tree in the way: Root, Left, Right.

Therefore, the following code changes are required:
When the current node has a left child:

In Morris Inorder Traversal, a new thread is created by establishing a temporary link
between the current node and its in-order predecessor. In Morris Preorder Traversal,
we want to print the root before visiting the left child. Therefore, after setting
the thread (establishing the link), we print the current node's value before moving
it to its left child.

When the current node has no left child:

This case remains unchanged from Morris Inorder Traversal. If the current node has no
left child, there is nothing to visit on the left side. In both Inorder and Preorder
traversals, we want to print the current node's value and move to the right child.
Therefore, there is no code modification needed for this scenario.

Algorithm:
Step 1:Initialise a current to traverse the tree. Set current to the root of the Binary Tree.

Step 2: While the current is not null: If the current node has no left child, print
the current node's value and move to the right child ie. set current to its right child.

Step 3: If the current node has a left child, we find the in-order predecessor of the
current node. This in-order predecessor is the rightmost node in the left subtree or
the left subtree's rightmost node.

If the right child of the in-order predecessor is null:

1. Set the right child to the current node.
2. Print the value of the current node as preorder traverses the tree in the inorder:
   Root, Left then Right.
3. Move to the left child (i.e., set current to its left child).

If the right child of the in-order predecessor is not null:

1. Revert the changes made in the previous step by setting the right child as null.
2. Move to the right child (i.e., set current to its right child).
3. Repeat steps 2 and 3 until the end of the tree is reached.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=80Zug6D1_r4