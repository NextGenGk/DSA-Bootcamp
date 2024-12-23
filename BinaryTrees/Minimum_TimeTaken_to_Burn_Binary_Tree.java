package BinaryTrees;

import java.util.*;

public class Minimum_TimeTaken_to_Burn_Binary_Tree {

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

    // Method to calculate the minimum time to burn the entire tree starting from the target node.
    public static int minTimeToBurnTree(TreeNode root, TreeNode target) {
        // Map to keep track of each node's parent for upward traversal.
        Map<TreeNode, TreeNode> parentTrack = new HashMap<>();

        // Step 1: Populate the parentTrack map by marking parents of all nodes.
        markParent(root, parentTrack);

        // Step 2: BFS to simulate fire spreading from the target node.
        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();
        queue.offer(target);
        visited.add(target);

        // Initialize the time
        int time = 0;

        // Level-order BFS traversal to burn the tree.
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean foundNextLevel = false;

            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();

                // Spread fire to the left child.
                if (current.left != null && !visited.contains(current.left)) {
                    queue.offer(current.left);
                    visited.add(current.left);
                    foundNextLevel = true;
                }

                // Spread fire to the right child.
                if (current.right != null && !visited.contains(current.right)) {
                    queue.offer(current.right);
                    visited.add(current.right);
                    foundNextLevel = true;
                }

                // Spread fire to the parent node.
                TreeNode parent = parentTrack.get(current);
                if (parent != null && !visited.contains(parent)) {
                    queue.offer(parent);
                    visited.add(parent);
                    foundNextLevel = true;
                }
            }

            // If fire has spread to new nodes, increment the time.
            if (foundNextLevel) {
                time++;
            }
        }

        return time;
    }

    // Helper function to populate the parent pointers using level-order traversal.
    private static void markParent(TreeNode root, Map<TreeNode, TreeNode> parentTrack) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();

            // If current node has a left child, mark its parent.
            if (current.left != null) {
                parentTrack.put(current.left, current);
                queue.offer(current.left);
            }

            // If current node has a right child, mark its parent.
            if (current.right != null) {
                parentTrack.put(current.right, current);
                queue.offer(current.right);
            }
        }
    }

    // Main Function
    public static void main(String[] args) {
        // Example tree:
        //       1
        //     /   \
        //    2     3
        //   / \
        //  4   5

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        // Fire starts at node 4.
        TreeNode target = root.left.left;

        // Calculate and print the minimum time to burn the tree.
        int time = minTimeToBurnTree(root, target);
        System.out.println("Minimum time to burn the tree: " + time);
    }
}

// Output : Minimum time to burn the tree: 3

// Algorithm / Intuition : Optimal Solution
/*
Intuition:
The task is to calculate the minimum time it takes to burn a binary tree starting from a given target node.
The fire spreads both downward to children and upward to the parent node. Therefore, we need to handle both types of traversal:

- **Downward traversal**: Spread the fire to the left and right children of a node.
- **Upward traversal**: To spread fire to the parent node, we need to track the parent for each node.

We achieve upward traversal by:
1. Maintaining a map of parents for each node (using level-order traversal).
2. Using **Breadth-First Search (BFS)** starting from the target node to simulate the fire spreading level by level.

Algorithm:
1. **Mark Parents**:
   - Traverse the tree using level-order (BFS) and fill a map `parentTrack` with the parent of each node.
   - This allows us to move upward to the parent when processing a node.

2. **BFS to Simulate Fire Spread**:
   1. Start from the target node where the fire begins.
   2. Initialize a queue to perform BFS and a set `visited` to track visited nodes.
   3. For each node, spread the fire to its left child, right child, and parent (if they haven't been visited).
   4. As you process each level, increment the time.
   5. Continue until all reachable nodes are processed (the entire tree is burned).
   6. Return the time taken for the fire to spread to all nodes.

Steps:
1. **Mark Parents**:
   - Traverse the tree using BFS and create the `parentTrack` map.

2. **BFS to Spread Fire**:
   - Initialize the BFS with the target node and start spreading the fire.
   - Process each node level by level and increment the time for each new level.
   - Use the `parentTrack` map to handle upward traversal.

3. **Return the Time**:
   - After BFS completes, the time variable will contain the minimum time taken to burn the entire tree.
*/

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=2r5wLmQfD6g
