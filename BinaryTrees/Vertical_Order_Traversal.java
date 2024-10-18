package BinaryTrees;

import java.util.*;

public class Vertical_Order_Traversal {

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

    // Helper class to store node and its vertical and level info
    static class NodeInfo {
        TreeNode node; // The tree node
        int vertical; // Vertical position (x-coordinate)
        int level;    // Level (y-coordinate)

        public NodeInfo(TreeNode node, int vertical, int level) {
            this.node = node;         // Initialize node
            this.vertical = vertical; // Initialize vertical
            this.level = level;       // Initialize level
        }
    }

    // Function to perform vertical order traversal and return a 2D list of node values
    public static List<List<Integer>> findVertical(TreeNode root) {
        // Map to store nodes based on vertical (x) and level (y)
        // Outer map keys are verticals, inner map keys are levels
        Map<Integer, Map<Integer, List<Integer>>> nodes = new TreeMap<>();

        // Queue for BFS traversal; each element contains a node and its vertical and level
        Queue<NodeInfo> queue = new LinkedList<>();
        // Start with the root node, vertical = 0, level = 0
        queue.add(new NodeInfo(root, 0, 0));

        // BFS traversal loop
        while (!queue.isEmpty()) {
            // Dequeue the front element from the queue
            NodeInfo info = queue.poll();
            TreeNode node = info.node;
            int vertical = info.vertical; // x-coordinate (vertical)
            int level = info.level;       // y-coordinate (level)

            // Add the node's value to the corresponding vertical and level in the map
            nodes.putIfAbsent(vertical, new TreeMap<>()); // Create map for vertical if not present
            nodes.get(vertical).putIfAbsent(level, new ArrayList<>()); // Create list for level if not present
            nodes.get(vertical).get(level).add(node.val); // Add node value to the list

            // Add the left child to the queue if it exists, with updated vertical and level
            if (node.left != null) {
                queue.add(new NodeInfo(node.left, vertical - 1, level + 1)); // Move left (vertical - 1)
            }

            // Add the right child to the queue if it exists, with updated vertical and level
            if (node.right != null) {
                queue.add(new NodeInfo(node.right, vertical + 1, level + 1)); // Move right (vertical + 1)
            }
        }

        // Prepare the final result list
        List<List<Integer>> result = new ArrayList<>();
        // Traverse the map to build the result list
        for (Map<Integer, List<Integer>> levelMap : nodes.values()) {
            List<Integer> col = new ArrayList<>(); // To store values at each vertical
            for (List<Integer> levelList : levelMap.values()) {
                col.addAll(levelList); // Add all node values at the same level
            }
            result.add(col); // Add the vertical (column) to the final result
        }
        return result; // Return the result 2D list
    }

    // Helper function to print the result
    private static void printResult(List<List<Integer>> result) {
        // Loop through the 2D list and print each vertical (column) of nodes
        for (List<Integer> level : result) {
            for (int node : level) {
                System.out.print(node + " "); // Print node values in a single line
            }
            System.out.println(); // Move to the next line after each vertical
        }
        System.out.println(); // Extra newline for better formatting
    }

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

        // Get the Vertical traversal
        List<List<Integer>> verticalTraversal = findVertical(root);

        // Print the result
        System.out.print("Vertical Traversal: ");
        printResult(verticalTraversal); // Call helper function to print
    }
}

// Output :
/*
Vertical Traversal:
4
2 5
1 10 9 6
3
10
 */

// Algorithm / Intuition : Optimal Solution
/*
The structure breaks down as follows:

First Integer (the outer Map<Integer, ...>): Represents the vertical position
(or x-coordinate) of the node in the tree.

This groups nodes based on their vertical alignment. Negative values represent
nodes to the left, 0 represents nodes directly vertical under the root, and
positive values represent nodes to the right.

Second Integer (the inner Map<Integer, List<Integer>>): Represents the level
(or y-coordinate) of the node in the tree.

This organizes nodes within the same vertical based on their level (height from the root).
List<Integer>: Represents the node values at the same vertical and level.

This stores the actual values of the nodes that share the same vertical and level.

We can assign a vertical and level to every node. This will help us in categorising
nodes based on their position in the binary tree. Vertical Coordinates (x): The vertical
coordinate, denoted as 'x', represents the vertical column in the tree. It essentially
signifies the horizontal position of a node in relation to its parent. Nodes with the
same 'x' value are aligned vertically, forming a column. Level Coordinates (y): The
level coordinate, denoted as 'y', represents the depth or level of a node in the tree.
It signifies the vertical position of a node within the hierarchy of levels. As we
traverse down the tree, the 'y' value increases, indicating a deeper level.


We create a map that serves as our organisational structure. The map is based on the
vertical and level information of each node. The vertical information, represented by
'x', signifies the vertical column, while the level information, denoted as 'y', acts
as the key within the nested map. This nested map utilises a multiset to ensure that
node values are stored in a unique and sorted order. With our map structure in place,
we initiate a level order BFS traversal using a queue. Each element in the queue is a
pair containing the current node and its corresponding vertical and level coordinates.
Starting with the root node, we enqueue it with initial vertical and level values (0, 0).
During traversal, for each dequeued node, we update the map by inserting the node value
at its corresponding coordinates and enqueue its left and right children with adjusted
vertical and level information. When traversing to the left child, the vertical value
decreases by 1 and the level increases by 1, while traversal to the right child leads
to an increase in both vertical and level by 1.

After completing the BFS traversal, we prepare the final result vector. We iterate
through the map, creating a column vector for each vertical column. This involves
gathering node values from the multiset and inserting them into the column vector.
These column vectors are then added to the final result vector, resulting in a 2D
representation of the vertical order traversal of the binary tree.

Algorithm:

Step 1: Create an empty map to store the nodes based on their vertical and horizontal
levels.The key of the map ‘x’ represents the vertical column, and the nested map uses
‘y’ as the key for the level. Initialise a ‘multiset’ to store node values at a specific
vertical and level to ensure unique and sorted order of nodes.

Step 2: Initialise a queue for level order BFS traversal. Each element in the queue
should be a pair containing the current node and its vertical and level order information
as x and coordinates. Enqueue the root node into the queue with its initial vertical and
level order values as (0, 0)

Step 3: While the queue is not empty, pop the front node of the queue:

    1. Get this nodes vertical ie. ‘x’ and level order ‘y’ information.
    2. Insert this node into the map at its corresponding coordinate.
    3. Push the left and right child of the node with their updated horizontal distance and level order.

For the left child, decrement the vertical value ‘x’ by 1 to indicate a move towards the
left. Increment the level value ‘y’ by 1 to indicate a move down to the next level. For
the right child, increment the vertical value ‘x’ by 1 to indicate a move towards the
right. Increment the level value ‘y’ by 1 to indicate a move down to the next level.

Enqueue both the left and right children along with their updated vertical and level
information into the queue.

Step 4: After the BFS traversal using the queue is complete, initialise a final result
2D vector ‘ans’.

Iterate through the map, creating a column vector for each vertical column. Gather the
node values from the multiset and insert them into the column vector.
Add these column vectors to the final result vector ‘ans’.

Step 5: Return the 2D vector `ans` representing the vertical order traversal of the binary tree.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=q_a6lpbKJdw