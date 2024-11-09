package BinaryTrees;

import java.util.*;

public class Print_All_Nodes_at_Distance_K {

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

    // Time Complexity: O(N) where N is the number of nodes in the tree.
    // This is because we need to traverse all nodes to mark their parents
    // and potentially traverse the entire tree during BFS.
    // O(N) (Using BFS) + O(N) (because the algorithm traverses each node exactly once)  ~ O(N)
    // (Overall Complexity)

    // Space Complexity: O(N) due to the space required for the parentTrack map,
    // the queue for BFS, and the visited map.
    // O(N) (Storing Parent for each node) + O(N) (Using Queue to store nodes) +
    // O(N) (Storing visited nodes) ~ O(N) (Overall Complexity)

    // Function to find all nodes at a distance `k` from the target node.
    public static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        // Map to keep track of each node's parent for upward traversal.
        Map<TreeNode, TreeNode> parentTrack = new HashMap<>();

        // Populate the parentTrack map by marking parents of all nodes.
        markParent(root, parentTrack, root);

        // Queue for level-order traversal to find nodes at distance `k`.
        Queue<TreeNode> queue = new LinkedList<>();
        // Map to keep track of visited nodes to avoid revisiting.
        Map<TreeNode, Boolean> isVisited = new HashMap<>();

        // Start from the target node.
        queue.offer(target);
        isVisited.put(target, true);

        int distance = 0; // Initial distance from the target node.

        // Level-order traversal until we reach the required distance.
        while (!queue.isEmpty()) {
            int size = queue.size();

            // If we've reached the required distance, break out of the loop.
            if (distance == k) break;

            distance++; // Increment the distance for each level.

            // Process nodes at the current level.
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();

                // Check the left child if it hasn't been visited.
                if (current.left != null && isVisited.get(current.left) == null) {
                    queue.offer(current.left);
                    isVisited.put(current.left, true);
                }

                // Check the right child if it hasn't been visited.
                if (current.right != null && isVisited.get(current.right) == null) {
                    queue.offer(current.right);
                    isVisited.put(current.right, true);
                }

                // Check the parent node if it hasn't been visited.
                if (parentTrack.get(current) != null && isVisited.get(parentTrack.get(current)) == null) {
                    queue.offer(parentTrack.get(current));
                    isVisited.put(parentTrack.get(current), true);
                }
            }
        }

        // Collect all nodes at the distance `k` in the result list.
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            list.add(current.val); // Add the value of the current node to the result.
        }

        return list; // Return the list of nodes at distance `k`.
    }

    // Helper function to mark parents for all nodes in the binary tree.
    private static void markParent(TreeNode root, Map<TreeNode, TreeNode> parentTrack, TreeNode target) {
        // Queue for level-order traversal to mark parent nodes.
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();

            // If the current node has a left child, set its parent and add it to the queue.
            if (current.left != null) {
                parentTrack.put(current.left, current);
                queue.offer(current.left);
            }

            // If the current node has a right child, set its parent and add it to the queue.
            if (current.right != null) {
                parentTrack.put(current.right, current);
                queue.offer(current.right);
            }
        }
    }

    // Main function to test the distanceK method
    public static void main(String[] args) {
        // Construct the binary tree
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        // Target node to find nodes at distance `k`
        TreeNode target = root.left; // Node with value 5

        // Distance `k`
        int k = 2;
        List<Integer> result = distanceK(root, target, k);

        // Print the result
        System.out.println("Nodes at distance " + k + " from target " + target.val + ": " + result);
    }
}

// Output : Nodes at distance 2 from target 5: [7, 4, 1]

// Algorithm / Intuition : Optimal Solution
/*
    Example Usage:
    Consider the following binary tree:
            3
           / \
          5   1
         / \ / \
        6  2 0  8
          / \
         7   4

    If the target node is `5` and `k = 2`, the function should return `[7, 4, 1]` because these nodes are 2 distance units away from the target node `5`.

    Explanation:
    - From node `5` at distance 0:
      - At distance 1: nodes `3` (parent), `6` (left child), `2` (right child).
      - At distance 2: nodes `1` (parent of `3`), `7`, `4` (children of `2`).
 */

// Algorithm :
/*
Intuition:
The task is to find all nodes in a binary tree that are exactly k distance away from
a given target node. This problem requires both downward and upward traversal from
the target node:

Downward traversal:
This is straightforward; move left or right from the target.

Upward traversal:
This is less direct, as you need to traverse from a child to its
parent, which typically isn't tracked in a tree node's structure.
To address this, we:

Use a map to track the parent of each node. This allows upward traversal from any node.
Perform a Breadth-First Search (BFS) starting from the target node to find all nodes
at distance k, visiting nodes in all directions (left, right, and parent) while avoiding revisits.

Algorithm:
1. Mark Parents:
Use a helper function markParent to populate a map (parentTrack) that records the parent of each node.
Traverse the tree using a queue (level-order traversal) and fill the parentTrack map for each node.

2. BFS to Find Nodes at Distance k:
    1. Start from the target node.
    2. Use a queue for BFS and a Map to keep track of visited nodes to prevent revisiting.
    3. Increment the distance variable as you explore each level.
    4. Continue the BFS until distance == k.
    5. Collect the values of all nodes at this level and return them as the result.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=i9ORlEy6EsI