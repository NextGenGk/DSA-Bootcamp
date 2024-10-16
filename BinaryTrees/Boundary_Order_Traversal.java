package BinaryTrees;

import java.util.ArrayList;
import java.util.List;

public class Boundary_Order_Traversal {

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

    // Time Complexity: O(N) where N is the number of nodes in the Binary Tree.

    // Adding the left boundary of the Binary Tree results in the traversal of the
    // left side of the tree which is proportional to the the height of the three
    // hence O(H) ie. O(log2N). In the worst case that the tree is skewed the
    // complexity would be O(N).

    // For the bottom traversal of the Binary Tree, traversing the leaves is
    // proportional to O(N) as preorder traversal visits every node once.

    // Adding the right boundary of the Binary Tree results in the traversal of
    // the right side of the tree which is proportional to the the height of the
    // three hence O(H) ie. O(log2N). In the worst case that the tree is skewed
    // the complexity would be O(N).

    // Since all these operations are performed sequentially, the overall time
    // complexity is dominated by the most expensive operation, which is O(N).

    // Space Complexity: O(N) where N is the number of nodes in the Binary Tree
    // to store the boundary nodes of the tree. O(H) or O(log2N) Recursive stack
    // space while traversing the tree. In the worst case scenario the tree is
    // skewed and the auxiliary recursion stack space would be stacked up to
    // the maximum depth of the tree, resulting in an O(N) auxiliary space complexity.

    // Function to check
    // if a node is a leaf
    static boolean isLeaf(TreeNode root) {
        return root.left == null && root.right == null;
    }

    // Function to add the
    // left boundary of the tree
    static void addLeftBoundary(TreeNode root, List<Integer> res) {
        TreeNode curr = root.left;
        while (curr != null) {
            // If the current node is not a leaf,
            // add its value to the result
            if (!isLeaf(curr)) {
                res.add(curr.val);
            }
            // Move to the left child if it exists,
            // otherwise move to the right child
            if (curr.left != null) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
    }

    // Function to add the
    // right boundary of the tree
    static void addRightBoundary(TreeNode root, List<Integer> res) {
        TreeNode curr = root.right;
        List<Integer> temp = new ArrayList<>();
        while (curr != null) {
            // If the current node is not a leaf,
            // add its value to a temporary list
            if (!isLeaf(curr)) {
                temp.add(curr.val);
            }
            // Move to the right child if it exists,
            // otherwise move to the left child
            if (curr.right != null) {
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }
        // Reverse and add the values from
        // the temporary list to the result
        for (int i = temp.size() - 1; i >= 0; --i) {
            res.add(temp.get(i));
        }
    }

    // Function to add the
    // leaves of the tree
    static void addLeaves(TreeNode root, List<Integer> res) {
        // If the current node is a
        // leaf, add its value to the result
        if (isLeaf(root)) {
            res.add(root.val);
            return;
        }
        // Recursively add leaves of
        // the left and right subtrees
        if (root.left != null) {
            addLeaves(root.left, res);
        }
        if (root.right != null) {
            addLeaves(root.right, res);
        }
    }

    // Main function to perform the
    // boundary traversal of the binary tree
    static List<Integer> printBoundary(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        // If the root is not a leaf,
        // add its value to the result
        if (!isLeaf(root)) {
            res.add(root.val);
        }

        // Add the left boundary, leaves,
        // and right boundary in order
        addLeftBoundary(root, res);
        addLeaves(root, res);
        addRightBoundary(root, res);

        // Return the final answer
        return res;
    }

    // Helper function to
    // print the result
    static void printResult(List<Integer> result) {
        for (int val : result) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    // Main Function
    public static void main(String[] args) {
        // Creating a sample binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        // Get the boundary traversal
        List<Integer> result = printBoundary(root);

        // Print the result
        System.out.print("Boundary Traversal: ");
        printResult(result);
    }
}

// Output : Boundary Traversal: 1 2 4 5 6 7 3

// Algorithm / Intuition : Optimal Solution
/*
The boundary traversal algorithm should be divided into three main parts traversed in the
anti-clockwise direction:

Left Boundary: Traverse the left boundary of the tree. Start from the root and keep
moving to the left child; if unavailable, move to the right child. Continue this until
we reach a leaf node.

Bottom Boundary: Traverse the bottom boundary of the tree by traversing the leaf nodes
using a simple preorder traversal. We check if the current node is a lead, and if so,
its value is added to the boundary traversal array.

Right Boundary: The right boundary is traversed in the reverse direction, similar to
the left boundary traversal starting from the root node and keep moving to the right
child; if unavailable, move to the left child. Nodes that are not leaves are pushed
into the right boundary array from end to start to ensure that they are added in the
reverse direction.

Algorithm:

Step 1: Initialise an empty array to store the boundary traversal nodes.

Step 2: Create a helper function to check if a node is a leaf. This is to avoid
cases where there will be an overlap in the traversal of nodes. We exclude leaf
nodes when adding left and right boundaries as they will already be added when
in the bottom boundary.

Step 3: Initialise a recursive function `addLeftBoundary` and a vector to store
the left traversal.

    1. Start from the root of the tree.
    2. Traverse down the left side of the tree until we reach a leaf node.
       For each non-leaf node, add its value to the result list.
    3. Traverse to its left child. If unavailable, call the recursion function
       to its right child.

Step 4: Implement a recursive function `addLeafNodes` and a vector to store
the bottom traversal.

    1. If the current node is a leaf, add its value to the result list.
    2. Recursively travel to the current nodes left and right subtrees in a
       preorder fashion.

Step 5: Implement a recursive function `addRightBoundary` and a vector to store
the right traversal.

    1. Start from the root of the tree.
    2. Traverse to the right most side of the tree until we reach a leaf node.
    3. For each non-leaf node, call the recursive function to its right child;
       if unavailable, call to its left child.
    4. While the recursion backtracks, add the current node’s value to the result list.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=0ca1nvR0be4