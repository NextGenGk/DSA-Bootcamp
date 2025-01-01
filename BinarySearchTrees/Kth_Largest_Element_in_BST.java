package BinarySearchTrees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Kth_Largest_Element_in_BST {

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

    // Time Complexity : O(N) + O(NlogN) ~ O(NlogN)
    // Space Complexity : O(N)

    // Helper method for preorder traversal
    private void preOrderTraversal(TreeNode root, List<Integer> elements) {
        if (root == null) {
            return;
        }

        // Visit root, then left, then right
        elements.add(root.val);
        preOrderTraversal(root.left, elements);
        preOrderTraversal(root.right, elements);
    }

    // Main method to find k-th largest element
    public int kthLargestBrute(TreeNode root, int k) {
        List<Integer> elements = new ArrayList<>(); // To store all elements of the tree
        preOrderTraversal(root, elements); // Collect all elements in preorder
        Collections.sort(elements, Collections.reverseOrder()); // Sort in descending order
        return elements.get(k - 1); // Return the k-th largest element
    }

    // Method 2 : Better Solution

    // Time Complexity: O(N) for inorder traversal.
    // Space Complexity: O(N) for the list storing all elements.

    // Inorder traversal to collect all elements in sorted order
    private void inorder(TreeNode root, List<Integer> result) {
        if (root == null) return;
        inorder(root.left, result);
        result.add(root.val);
        inorder(root.right, result);
    }

    public int kthLargestBetter(TreeNode root, int k) {
        List<Integer> elements = new ArrayList<>();
        inorder(root, elements); // Get sorted elements
        return elements.get(elements.size() - k); // Return the k-th largest (0-indexed)
    }

    // Method 3 : Optimal Solution

    // Time Complexity : O(H + K)  where H is the height of the tree. In the worst case,
    // we may visit up to H nodes to find the k-th largest element, and we stop early if
    // we find the element before the traversal is complete.
    // Space Complexity : O(H) due to the recursion stack
    // In the case of a balanced tree, the space complexity is O(logN), where n is the no. of nodes
    // n the worst case (unbalanced tree), it could be O(N)

    public class Solution {
        private static int count = 0; // Counter to track visited nodes
        private static int result = -1; // Variable to store the k-th largest value

        // Helper method for reverse inorder traversal
        private static void reverseInorder(TreeNode root, int k) {
            if (root == null || count >= k) {
                return;
            }

            // Traverse the right subtree first (for descending order)
            reverseInorder(root.right, k);

            // Increment the counter
            count++;

            // Check if the current node is the k-th largest
            if (count == k) {
                result = root.val;
                return; // Stop further traversal
            }

            // Traverse the left subtree
            reverseInorder(root.left, k);
        }

        // Main method to find the k-th largest element
        public static int kthLargestOptimal(TreeNode root, int k) {
            reverseInorder(root, k); // Perform reverse inorder traversal
            return result; // Return the k-th largest element
        }
    }

    // Main Function
    public static void main(String[] args) {
        // Construct BST
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);

        int k = 3;

        // Find the k-th largest element
        System.out.println("K-th Largest Element: " + Solution.kthLargestOptimal(root, k)); // Output: 7
    }
}

// Algorithm / Intuition : Brute Force
/*
Intuition :
We can do traversal of the given tree using any traversal technique and store the
node values in an array/vector. Then we can sort the array in ascending order such
that the 1st smallest element comes at 0th index, 2nd smallest element at 1st index ...
kth smallest element at k-1th index.

Algorithm :
1. Perform any of the traversal technique (like Pre, In, Post, Level) and store
all the node values in a list/array.
2. Sort the list in descending order
3. Return the kth smallest element in the sorted list.
 */

// Algorithm / Intuition : Better Solution
/*
Intuition :
We know that the tree given to us is a Binary Search Tree, therefore, all the
nodes at the left subtree of a given node will be less than the current node
value and it will be less then all the nodes at the right subtree of that node.
i.e.

           N
          / \
         L   R

   L<N<R in case of BST
So, we can take advantage of this and do an INORDER TRAVERSAL. The inorder
traversal will always result in a sorted array and the extra NlogN that we were
using for sorting will be omitted.

Algorithm :
1. Perform Inorder Traversal
2. Return the kth largest element
 */

// Algorithm / Intuition : Optimal Solution
/*
Intuition :
During the reverse in-order traversal, we visit the nodes in descending order, so
the k-th largest element will be encountered after visiting the k-th node.

Algorithm :
1. Do an Inorder traversal and instead of saving root.val in list do a cnt++, denoting
that we have encountered cnt the largest element.
2. Return root.val once cnt == k.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=9TJYWh0adfk (Smallest Element)