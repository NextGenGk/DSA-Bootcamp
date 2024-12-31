package BinarySearchTrees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Kth_Smallest_Element_in_BST {

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

    // Main method to find k-th smallest element
    public int kthSmallestBrute(TreeNode root, int k) {
        List<Integer> elements = new ArrayList<>(); // To store all elements of the tree
        preOrderTraversal(root, elements); // Collect all elements in preorder
        Collections.sort(elements); // Sort the collected elements
        return elements.get(k - 1); // Return the k-th smallest element
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

    public int kthSmallestBetter(TreeNode root, int k) {
        List<Integer> sortedElements = new ArrayList<>();
        inorder(root, sortedElements); // Get sorted elements
        return sortedElements.get(k - 1); // Return the k-th smallest (0-indexed)
    }

    // Method 3 : Optimal Solution

    // Time Complexity : O(H + K)  where H is the height of the tree. In the worst case,
    // we may visit up to H nodes to find the k-th largest element, and we stop early if
    // we find the element before the traversal is complete.
    // Space Complexity : O(H) due to the recursion stack
    // In the case of a balanced tree, the space complexity is O(logN), where n is the no. of nodes
    // n the worst case (unbalanced tree), it could be O(N)

    class Solution {
        private int count = 0; // To keep track of the number of nodes visited
        private int result = -1; // To store the k-th smallest element

        private void inorder(TreeNode root, int k) {
            if (root == null) return;

            // Traverse left subtree
            inorder(root.left, k);

            // Increment counter and check if current node is the k-th smallest
            count++;
            if (count == k) {
                result = root.val;
                return;
            }

            // Traverse right subtree
            inorder(root.right, k);
        }

        public int kthSmallest(TreeNode root, int k) {
            inorder(root, k);
            return result; // Return the k-th smallest element
        }
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
2. Sort the list in ascending order
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
2. Return the kth smallest element
 */

// Algorithm / Intuition : Optimal Solution
/*
Intuition :
Intuition:
We need the kth smallest element. And, we know that our inorder traversal will
first give the 1st smallest element, then 2nd smallest element ... and so on.
So, instead of storing the node values inside a vector, we can maintain a 'cnt'
variable to keep track if we have reached kth smallest value or not in the inorder
traversal. And then, we can return the value once cnt reaches k value.

Algorithm :
1. Do an Inorder traversal and instead of saving root.val in list do a cnt++, denoting
that we have encountered cnt th smallest element.
2. Return root.val once cnt == k.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=9TJYWh0adfk