package BinaryTrees;

import java.util.LinkedList;
import java.util.Queue;

public class Height_of_Binary_Tree {

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

    // Recursive Approach

    // Time Complexity : The time complexity of this algorithm is O(N),
    // where N is the total number of nodes in the binary tree. This is because
    // the function visits each node exactly once to compute the depth of both
    // the left and right subtrees.

    // Space Complexity : The space complexity is O(H), where H is the height of the
    // tree. This is the maximum depth of the recursion stack, which depends on the
    // height of the tree. In the worst case, if the tree is skewed (like a linked list),
    // the height is N, so the space complexity would be O(N). In the best case, for a
    // balanced tree, the height is log(N), making the space complexity O(log N).

    // Computes the maximum depth (or height) of a binary tree.
    // The depth is the number of nodes along the longest path from the root
    // node down to the farthest leaf node.
    static int maxDepthRecursive(TreeNode root) {
        // Base case: if the tree is empty, return 0
        if (root == null) return 0;

        // Recursively calculate the depth of the left subtree
        int leftHeight = maxDepthRecursive(root.left);

        // Recursively calculate the depth of the right subtree
        int rightHeight = maxDepthRecursive(root.right);

        // Return the greater of the two depths plus 1 (for the current root node)
        return 1 + Math.max(leftHeight, rightHeight);
    }

    // Iterative Approach

    // Time Complexity: O(N) where N is the number of nodes in the Binary Tree.
    // This complexity arises from visiting each node exactly once during the traversal
    // to determine the maximum depth.

    // Space Complexity: O(N) where N is the number of nodes in the Binary Tree because
    // in the worst case scenario the tree is balanced and has N/2 nodes in its last level
    // which will have to be stored in the queue.

    // Function to find the maximum depth of a binary tree
    // using level order traversal
    static int maxDepthIterative(TreeNode root) {
        // If the root is NULL
        // (empty tree), depth is 0
        if (root == null) {
            return 0;
        }

        // Create a queue for
        // level order traversal
        Queue<TreeNode> queue = new LinkedList<>();
        int level = 0;

        // Push the root node into the queue
        queue.add(root);

        // While there are nodes in the queue
        while (!queue.isEmpty()) {
            // Get the number of nodes
            // at the current level
            int size = queue.size();

            // Process all nodes
            // at the current level
            for (int i = 0; i < size; i++) {
                // Get the front node in the queue
                TreeNode front = queue.poll();

                // Enqueue left child if exists
                if (front.left != null) {
                    queue.add(front.left);
                }

                // Enqueue right child if exists
                if (front.right != null) {
                    queue.add(front.right);
                }
            }
            // Increment level to
            // move to the next level
            level++;
        }
        // Return the level, which represents
        // the maximum depth of the tree
        return level;
    }

    // Main Function
    public static void main(String[] args) {
        // Creating a sample binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.right.right = new TreeNode(6);
        root.left.right.right.right = new TreeNode(7);

//        int depth = maxDepthRecursive(root);
        int depth = maxDepthIterative(root);

        System.out.println("Maximum depth of the binary tree: " + depth);
    }
}

// Output : Maximum depth of the binary tree: 5

// Approach / Intuition : Recursive Approach
/*
The intuition behind this code is to calculate the maximum depth of a binary tree by breaking
it down recursively. If the tree is empty, the depth is zero. Otherwise, for each node, the
function calculates the maximum depth of its left and right subtrees. The depth of the current
node is then one (for the node itself) plus the greater of the two subtree depths. This way, the
function traverses the entire tree and builds up the maximum depth from the bottom-up, returning
the length of the longest path from the root to any leaf.
 */

// Approach / Intuition : Iterative (Using Level Order Traversal)
/*
To find the maximum depth of the binary tree using level order traversal, we employ a breadth-first
approach. Initialise a queue and push the root node. Traverse through the levels and track the depth
by counting the number of levels traversed. At each level pop the nodes and push their left and right
children, incrementing the depth counter as we explore.This process continues until all levels are
traversed at which point the depth variable holds the maximum depth of the tree.

Algorithm:

Step 1: Initialise a queue for level order traversal and a variable `level` to track the depth.
Check if the root is null, if so return the answer as 0 indicating an empty tree.

Step 2: Insert the root of the Binary Tree into the queue and set the level as 0.

Step 3: Begin a loop that continues until the queue becomes empty where at each level:

  i. Increment `level` by 1, indicating we are moving to the next level.
 ii. Determine the number of nodes at the current level by storing the size of the queue.
iii. Iterate over the number of nodes equal to the size of the queue and at each node,
     Pop it from front of the queue and push its left and right children (if they exist).

Step 4: After the queue loop gets over, return the `level` variable representing the maximum
depth of the tree calculated during the level order traversal.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=eD3tmO66aBA
// Article : https://takeuforward.org/data-structure/maximum-depth-of-a-binary-tree/