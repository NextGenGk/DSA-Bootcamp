package BinaryTrees;

import java.util.*;

public class Bottom_View_of_Binary_Tree {

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

    // Time Complexity: O(N) where N is the number of nodes in the Binary Tree. This complexity
    // arises from visiting each node exactly once during the BFS traversal.

    // Space Complexity: O(N/2 + N/2) where N represents the number of nodes in the Binary Tree.
    // The main space consuming data structure is the queue used for BFS traversal. It acquires
    // space proportional to the number of nodes in the level it is exploring hence in the worst
    // case of a balanced binary tree, the queue will have at most N/2 nodes which is the maximum width.
    // Additionally, the map is used to store the top view nodes based on their vertical positions
    // hence its complexity will also be proportional to the greatest width level. In the worst case,
    // it may have N/2 entries as well.

    // Function to return the
    // bottom view of the binary tree
    public static List<Integer> bottomView(TreeNode root) {
        // List to store the result
        List<Integer> ans = new ArrayList<>();

        // Check if the tree is empty
        if (root == null) {
            return ans;
        }

        // Map to store the bottom view nodes
        // based on their vertical positions
        Map<Integer, Integer> map = new TreeMap<>();

        // Queue for BFS traversal, each
        // element is a pair containing node
        // and its vertical position
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();

        // Push the root node with its vertical
        // position (0) into the queue
        queue.add(new Pair<>(root, 0));

        // BFS traversal
        while (!queue.isEmpty()) {
            // Retrieve the node and its vertical
            // position from the front of the queue
            Pair<TreeNode, Integer> pair = queue.poll();
            TreeNode node = pair.node;
            int line = pair.line;

            // Update the map with the node's data
            // for the current vertical position
            map.put(line, node.val);

            // Process left child
            if (node.left != null) {
                // Push the left child with a decreased
                // vertical position into the queue
                queue.add(new Pair<>(node.left, line - 1));
            }

            // Process right child
            if (node.right != null) {
                // Push the right child with an increased
                // vertical position into the queue
                queue.add(new Pair<>(node.right, line + 1));
            }
        }

        // Transfer values from the
        // map to the result list
        // You can this statement also : ans.addAll(map.values())
        // Transfer values from the
        // map to the result list
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            ans.add(entry.getValue());
        }

        // Finally return the answer
        return ans;
    }

    // Pair Class to Store Custom Pair of (Node, Vertical Line Number)
    static class Pair<T, I extends Number> {
        public final TreeNode node;
        public final int line;

        public Pair(TreeNode node, int line) {
            this.node = node;
            this.line = line;
        }
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

        // Get the Bottom View traversal
        List<Integer> bottomView = bottomView(root);

        // Print the result
        System.out.println("Bottom View Traversal: ");
        for (int node : bottomView) {
            System.out.print(node + " ");
        }
    }
}

// Output : 
// Bottom View Traversal:
// 4 5 6 3 10

// Algorithm / Intuition : Optimal Solution
/*
Algorithm / Intuition
To imagine the Binary Tree from above, we visualise vertical lines passing through the tree.
Each vertical line represents a unique vertical position. Nodes to the right of the tree’s
centre are assigned positive vertical indexes. As we move to the right, the vertical index
increases. Nodes to the left of the tree’s centre are assigned negative vertical indexes.
As we move to the left, the vertical index decreases.

We use a map data structure to store the nodes corresponding to each vertical level of the
tree as the map automatically sorts the elements based on their ascending value. Against
each vertical level, the node lowest in the tree at that vertical level is added by
traversing the tree level order wise (BFS).

Algorithm:

Step 1: Create a vector `ans` to store the result. Check if the tree is empty. If it is,
return an empty vector.

Step 2: Create a map to store the top view of nodes based on their vertical positions.
The key of this map is the vertical index and the value is the node’s data.

Step 3:Initialise a queue to perform breadth first traversal. Each element of this queue
is the node of the binary tree along with its vertical coordinate. Enqueue the root node
into the queue with its vertical position initialised to 0.

Step 4: While the queue is not empty, pop the front node of the queue and for this node:

    1. Get its vertical position. If this vertical position is not in the map, add the node’s data
       to the map. This means that this node is the first node encountered at this vertical position
       during the traversal.
    2. If the vertical position of this node is already a key in the map, it implies that
       a node higher in the tree with the same vertical position has already been processed.
       Overwrite this position with the current node as we want to get the lowest node of
       that vertical index.
    3. Enqueue the left child with a decreased vertical position ie. current vertical index -1.
       As when we move to the left child, we are moving towards the left column in the
       vertical order traversal.
    4. Enqueue the right child with an increased vertical position ie. current vertical index + 1.
       As when we move to the right child, we are moving towards the right column in the
       vertical order traversal.

Step 5: Iterate over the map and push the values of each node into the top view traversal.

    1. Since the keys of the map are sorted based on their keys (vertical positions), the nodes added
       to the `ans` vector will be sorted left to right.
    2. Return the ‘ans’ vector.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=0FtVY6I4pB8
