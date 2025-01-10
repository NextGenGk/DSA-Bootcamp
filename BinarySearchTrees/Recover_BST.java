package BinarySearchTrees;

import java.util.ArrayList;
import java.util.List;

public class Recover_BST {

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

    // Time Complexity :
    // Inorder Traversal: Collecting node values takes (O(n)) time, where (n) is the number of nodes.
    // Sorting: Sorting the collected values takes (O(n log n)) time.
    // Reassigning Values: Reassigning sorted values back to the nodes takes (O(n)) time.
    // Overall, the time complexity is: [O(n) + O(n \log n) + O(n) = O(n log n)]
    // Space Complexity :

    // Inorder Traversal List: Storing the inorder traversal requires (O(n)) space.
    // Call Stack for Recursion: The recursion uses space proportional to the tree's height (h).
    // In the worst case (skewed tree), (h = O(n)). In the best case (balanced tree), (h = O(\log n)).
    // Overall, the space complexity is: [O(n) + O(h)]  In the worst case, this simplifies to: [O(n)]

    static class SolutionBrute {
        private static List<TreeNode> nodes = new ArrayList<>();
        private static List<Integer> values = new ArrayList<>();

        // In-order traversal to collect nodes and their values
        private static void inorder(TreeNode root) {
            if (root == null) return;
            inorder(root.left);
            nodes.add(root); // Add the current node to the list of nodes
            values.add(root.val); // Add the current node's value to the list of values
            inorder(root.right);
        }

        public static void recoverTree(TreeNode root) {
            // Step 1: Perform an in-order traversal to collect nodes and values
            inorder(root);

            // Step 2: Sort the values to determine the correct order
            List<Integer> sortedValues = new ArrayList<>(values);
            sortedValues.sort(Integer::compareTo);

            // Step 3: Replace incorrect values in the tree
            for (int i = 0; i < nodes.size(); i++) {
                if (nodes.get(i).val != sortedValues.get(i)) {
                    nodes.get(i).val = sortedValues.get(i); // Update the node's value if it is incorrect
                }
            }
        }
    }

    // Method 2 : Optimal Solution

    // Time Complexity: O(N) where N is the number of nodes in the Binary Search Tree as the
    // algorithm involves performing an inorder traversal to identify the swapped nodes.

    // Space Complexity : O(1) as only the algorithm maintains a constant number of pointers
    // to track the nodes during traversal. The algorithm doesn’t use any additional data
    // structures that scale with the input size or the number of nodes in the tree.

    static class Solution {
        private TreeNode first, middle, last, prev;

        // Helper function to perform inorder traversal
        private void inorder(TreeNode root) {
            if (root == null) return;

            // Recursive call on the left subtree
            inorder(root.left);

            // Detecting swapped nodes
            if (prev != null && root.val < prev.val) {
                // If this is the first violation
                if (first == null) {
                    first = prev;
                    middle = root;
                } else {
                    // If this is the second violation
                    last = root;
                }
            }

            // Mark this node as the previous node
            prev = root;

            // Recursive call on the right subtree
            inorder(root.right);
        }

        public void recoverTree(TreeNode root) {
            first = middle = last = null;
            prev = new TreeNode(Integer.MIN_VALUE);

            // Perform inorder traversal to find the swapped nodes
            inorder(root);

            // Fix the swapped nodes
            if (first != null && last != null) {
                // Swap first and last if two violations were found
                int temp = first.val;
                first.val = last.val;
                last.val = temp;
            } else if (first != null && middle != null) {
                // Swap first and middle if only one violation was found
                int temp = first.val;
                first.val = middle.val;
                middle.val = temp;
            }
        }
    }

    // Helper method to print the tree in-order
    private static void printInOrder(TreeNode root) {
        if (root == null) return;
        printInOrder(root.left);
        System.out.print(root.val + " ");
        printInOrder(root.right);
    }

    // Main Function
    public static void main(String[] args) {
        SolutionBrute solutionBrute = new SolutionBrute();
        Solution solution = new Solution();
        // Create a BST with swapped nodes
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);

        System.out.println("Before recovery:");
        printInOrder(root);

        // Recover the BST
        solutionBrute.recoverTree(root);
        solution.recoverTree(root);

        System.out.println("\nAfter recovery:");
        printInOrder(root);
    }
}

// Output :
/*
Before recovery:
1 3 2 4
After recovery:
1 2 3 4
 */

// Algorithm / Intuition : Brute Force
/*
A brute force approach to recover a BST with two swapped nodes would involve the following steps:

1. Perform In-Order Traversal: Collect the values of the nodes using an in-order traversal,
   which will yield a sorted sequence if the tree is correct.
2. Identify Swapped Nodes: Compare the collected values to determine which two are out of place.
3. Correct the Tree: Traverse the tree again and replace the incorrect values with the correct ones.
 */

// Algorithm / Intuition : Optimal Solution
/*
Algorithm / Intuition
The inorder traversal of a Binary Search Tree which results in a sorted sequence. However
due to the swapped elements, the sorted order is disrupted by these misplaced nodes. While
traversing the tree, we keep a track of the previous and next node to each visited node.
As we identify nodes that violate the sorted order we store them.

By tracking these violations and handling the cases where the swapped nodes could be adjacent
or non-adjacent, the algorithm can effectively pinpoint the two nodes that are out of place.

Algorithm

Step 1: Four pointers are utilized: `first`, `prev`, `middle` and `last`. `first ` and `middle`
identify the misplaced nodes in the tree. `prev` keeps track of the previous node during the
inorder traversal. `last` marks the second node of the misplaced pair in case the nodes are adjacent.

Step 2: Traversing using the inorder method and compare each node’s value with the previous
node’s value `prev` to identify any violations that the current node value is less than the
previous node value. If a violation is found, mark the nodes accordingly:

    i. The first violation indicates the first and middle nodes. The first node is the first
    element encountered that is not greater than its previous node. The middle node is temporarily
    stored in case the swapped nodes are adjacent and there's no second violation.
   ii. If a second violation (second element not greater than its previous) is found, it signifies
   that the swapped nodes are not adjacent, and the last node is identified.

If there's no second violation, implying that the swapped nodes are adjacent, the middle node
identified earlier is retained.

Step 3: Once the misplaced nodes are identified, it performs necessary swaps based on whether
the nodes are adjacent or not:

    i. If both first and last are identified, it swaps the values of the first and last nodes.
   ii. If only first and middle are identified, it swaps their values.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=ZWGW7FminDM