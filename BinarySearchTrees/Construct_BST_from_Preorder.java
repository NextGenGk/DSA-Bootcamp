package BinarySearchTrees;

import java.util.*;

public class Construct_BST_from_Preorder {

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

    // Method 1 : Brute Force

    // Time Complexity : O(N^2)
    // Space Complexity : O(N)

    // Main method to construct the BST from the preorder traversal array
    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null; // If the input is empty, return null
        }

        // The first element in the preorder traversal is always the root
        TreeNode root = new TreeNode(preorder[0]);

        // Insert the remaining elements one by one into the BST
        for (int i = 1; i < preorder.length; i++) {
            insertIntoBST(root, preorder[i]);
        }

        return root; // Return the constructed BST
    }

    // Helper method to insert a value into the BST
    private void insertIntoBST(TreeNode root, int value) {
        TreeNode current = root;

        // Loop to find the correct position to insert the new value
        while (true) {
            if (value < current.val) {
                // If the value is less than the current node's value, move to the left subtree
                if (current.left == null) {
                    // If the left child is null, insert the value here
                    current.left = new TreeNode(value);
                    break; // Exit the loop after insertion
                } else {
                    // Otherwise, continue to the left child
                    current = current.left;
                }
            } else {
                // If the value is greater than or equal to the current node's value, move to the right subtree
                if (current.right == null) {
                    // If the right child is null, insert the value here
                    current.right = new TreeNode(value);
                    break; // Exit the loop after insertion
                } else {
                    // Otherwise, continue to the right child
                    current = current.right;
                }
            }
        }
    }

    // Method 2 : Better Solution

    // Time Complexity : O(NlogN) + O(N) ~ O(NlogN) [sorting + constructing the tree]
    // where N is the number of nodes in the tree
    // (the size of the preorder or inorder array).
    // Space Complexity : O(N) where space used by the recursive call stack
    // (depth of recursion could be up to O(N) in the worst case).

    public static TreeNode buildTree(Vector<Integer> preorder, Vector<Integer> inorder) {
        // Edge case check for mismatched sizes
        if (inorder.size() != preorder.size()) {
            return null;
        }

        // Map to store indices of elements in inorder traversal
        Map<Integer, Integer> inMap = new HashMap<>();

        // Populate the map with indices of elements from inorder
        for (int i = 0; i < inorder.size(); i++) {
            inMap.put(inorder.get(i), i);
        }

        // Call the helper function to build the tree recursively
        TreeNode root = buildTree(preorder, 0, preorder.size() - 1, inorder, 0,
                inorder.size() - 1, inMap);

        return root; // Return the root of the tree
    }

    private static TreeNode buildTree(Vector<Integer> preorder, int preStart, int preEnd,
                                      Vector<Integer> inorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        // Base case: If start index exceeds end index, return null
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        // Create a new TreeNode with the value from the current preorder index
        TreeNode root = new TreeNode(preorder.get(preStart));

        // Find the root node's index in the inorder array
        int inRoot = inMap.get(root.val);

        // Calculate the number of nodes in the left subtree
        int numsLeft = inRoot - inStart;

        // Recursively construct the left subtree
        root.left = buildTree(preorder, preStart + 1, preStart + numsLeft,
                inorder, inStart, inRoot - 1, inMap);

        // Recursively construct the right subtree
        root.right = buildTree(preorder, preStart + numsLeft + 1, preEnd,
                inorder, inRoot + 1, inEnd, inMap);

        // Return the current root node
        return root;
    }

    // Method 3 : Optimal Solution

    // Time Complexity : O(N), where N is the number of nodes in the tree
    // Space Complexity : O(N), recursive stack space

    // Method 3: Optimal Solution (Corrected)
    public static TreeNode bstFromPreorder1(int[] preorder) {
        // Start the recursive construction of the tree with an infinite upper bound
        return buildBST(preorder, Integer.MAX_VALUE, new int[]{0});
    }

    // Helper function to construct the BST recursively
    private static TreeNode buildBST(int[] preorder, int bound, int[] track) {
        // If all elements have been processed or the current value exceeds the bound, return null
        if (track[0] == preorder.length || preorder[track[0]] > bound) {
            return null;
        }

        // Create the root node with the current value
        TreeNode root = new TreeNode(preorder[track[0]++]);  // Increment track inside the function

        // Recursively build the left subtree (the next value must be smaller than the root's value)
        root.left = buildBST(preorder, root.val, track);

        // Recursively build the right subtree (the next value must be larger than the root's value)
        root.right = buildBST(preorder, bound, track);

        // Return the current root node after constructing its left and right subtrees
        return root;
    }

    // Print Tree in level order
    public static void printTree(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                System.out.print(node.val + " ");
                queue.add(node.left);
                queue.add(node.right);
            } else {
                System.out.print("null ");
            }
        }
        System.out.println(); // Newline after level-order output
    }

    // Main Method
    public static void main(String[] args) {
        Construct_BST_from_Preorder bstBuilder = new Construct_BST_from_Preorder();

        // Test Case 1: Using Method 1 (Brute Force)
        int[] preorder1 = {8, 5, 1, 7, 10, 12};
        TreeNode root1 = bstBuilder.bstFromPreorder(preorder1);
        System.out.println("Level-order output for Method 1:");
        bstBuilder.printTree(root1); // Expected output: 8 5 10 1 7 null 12

        // Test Case 2: Using Method 2 (Better Solution with Inorder)
        Vector<Integer> preorder2 = new Vector<>(Arrays.asList(8, 5, 1, 7, 10, 12));
        Vector<Integer> inorder2 = new Vector<>(Arrays.asList(1, 5, 7, 8, 10, 12));
        TreeNode root2 = bstBuilder.buildTree(preorder2, inorder2);
        System.out.println("Level-order output for Method 2:");
        bstBuilder.printTree(root2); // Expected output: 8 5 10 1 7 null 12

        // Test Case 3: Using Method 3 (Optimal Solution)
        int[] preorder3 = {8, 5, 1, 7, 10, 12};
        TreeNode root3 = bstBuilder.bstFromPreorder1(preorder3);
        System.out.println("Level-order output for Method 3:");
        bstBuilder.printTree(root3); // Expected output: 8 5 10 1 7 null 12
    }
}

// Output :
/*
Level-order output for Method 1:
8 5 10 1 7 null 12 null null null null null null
Level-order output for Method 2:
8 5 10 1 7 null 12 null null null null null null
Level-order output for Method 3:
8 5 10 1 7 null 12 null null null null null null
 */

// Algorithm / Intuition : Brute Force
/*
This brute-force approach is straightforward but may not be the most
efficient for large inputs. It works well for understanding and basic use cases.
 */

// Algorithm / Intuition : Better Solution
/*
This approach efficiently builds a binary tree from the given preorder and
inorder traversals by leveraging the properties of these traversals and utilizing
recursion. The hashmap (inMap) helps with quick lookups of the root node's position
in the inorder array, and the recursive function divides the problem into smaller
subproblems, constructing the tree piece by piece.

Idea : Construct Binary Tree from Preorder and Inorder Traversal
 */

// Algorithm / Intuition : Optimal Solution
/*
It works by recursively constructing the tree while maintaining valid bounds for
each subtree, ensuring the BST property is maintained.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=UmJT3j26t1I