package BinaryTrees;

import java.util.ArrayList;
import java.util.List;

public class Left_Side_View_of_a_Binary_Tree {

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

    // Time Complexity: O(log2N) where N is the number of nodes in the Binary Tree.
    // This complexity arises as we travel along the height of the Binary Tree. For
    // a balanced binary tree, the height is log2N but in the worst case when the tree
    // is skewed, the complexity becomes O(N).
    //
    // Space Complexity : O(log2N) where N is the number of nodes in the Binary Tree.
    // This complexity arises because we store the leftmost and rightmost nodes in an
    // additional vector. The size of this result vector is proportional to the height
    // of the Binary Tree which will be log2N when the tree is balanced and O(N) in the
    // worst case of a skewed tree.
    //
    // 1. O(H): Recursive Stack Space is used to calculate the height of the tree at each
    // node which is proportional to the height of the tree.

    // 2. The recursive nature of the getHeight function, which incurs space on the call
    // stack for each recursive call until it reaches the leaf nodes or the height of the tree.

    // Recursive function to traverse the
    // binary tree and populate the left-side view
    private static void recursionLeft(TreeNode root, int level, List<Integer> res) {
        // Check if the current node is null
        if (root == null) {
            return;
        }

        // Check if the size of the result list
        // is equal to the current level
        if (res.size() == level) {
            // If equal, add the value of the
            // current node to the result list
            res.add(root.val);
        }

        // Recursively call the function for the
        // left child with an increased level
        recursionLeft(root.left, level + 1, res);

        // Recursively call the function for the
        // right child with an increased level
        recursionLeft(root.right, level + 1, res);
    }

    // Function to return the Left view of the binary tree
    public static List<Integer> leftsideView(TreeNode root) {
        // List to store the result
        List<Integer> res = new ArrayList<>();

        // Call the recursive function
        // to populate the left-side view
        recursionLeft(root, 0, res);

        return res;
    }

    // Main Function
    public static void main(String[] args) {
        // Creating a sample binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(10);
        root.left.left.right = new TreeNode(5);
        root.left.left.right.right = new TreeNode(6);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(10);
        root.right.left = new TreeNode(9);

        // Get the Left View traversal
        List<Integer> leftView = leftsideView(root);

        // Print the result for Left View
        System.out.print("Left View Traversal: ");
        for (int node : leftView) {
            System.out.print(node + " ");
        }
        System.out.println();
    }
}

// Output : Left View Traversal: 1 2 4 5 6

// Algorithm / Intuition : Optimal Solution
/*
Algorithm / Intuition
To get the left and right view of a Binary Tree, we perform a depth-first
traversal of the Binary Tree while keeping track of the level of each node.
For both the left and right view, we’ll ensure that only the first node
encountered at each level is added to the result vector.

Algorithm for Left View

Step 1: Initialise an empty vector `res` to store the left view nodes.

Step 2: Implement a recursive depth-first traversal of the binary tree.

Base Case: Check if the current node is null, if true, return the function
as we have reached the end of that particular vertical level.

Recursive Function: The recursive function takes in arguments the current node
of the Binary Tree, its current level and the result vector.

1. We check if the size of the result vector is equal to the current level.
2. If true, it means that we have not yet encountered any node at this level
   in the result vector. Add the value of the current node to the result vector.
3. Recursively call the function for the current nodes left then right child
   with an increased level ie. level + 1.
4. We call the left child first as we want to traverse the left most nodes.
   In cases where there is no left child, the recursion function backtracks and
   explores the right child.

Step 3: The recursion continues until it reaches the base case. Return the result
vector at the end.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=KV4mRzTjlAk