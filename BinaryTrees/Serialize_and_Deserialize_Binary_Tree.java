package BinaryTrees;

import java.util.LinkedList;
import java.util.Queue;

public class Serialize_and_Deserialize_Binary_Tree {

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

    // Time Complexity: O(N)
    // serialize function: O(N), where N is the number of nodes in the tree. This is
    // because the function performs a level-order traversal of the tree, visiting each node once.
    // deserialize function: O(N), where N is the number of nodes in the tree. Similar
    // to the serialize function, it processes each node once while reconstructing the tree.

    // Space Complexity: O(N)
    // serialize function: O(N), where N is the maximum number of nodes at any level in
    // the tree. In the worst case, the queue can hold all nodes at the last level of the tree.
    // deserialize function: O(N), where N is the maximum number of nodes at any level
    // in the tree. The queue is used to store nodes during the reconstruction process,
    // and in the worst case, it may hold all nodes at the last level.

    // Encodes a tree to a single string in level-order traversal.
    public static String serialize(TreeNode root) {
        // Handle edge case where root is null
        if (root == null) return " ";

        Queue<TreeNode> queue = new LinkedList<>(); // Queue for level-order traversal
        StringBuilder sb = new StringBuilder(); // StringBuilder for the serialized string

        queue.add(root); // Add the root node to the queue

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll(); // Process the current node

            if (node == null) {
                sb.append("# "); // Append "#" for null nodes
                continue; // Skip adding children of null nodes
            }

            sb.append(node.val + " "); // Append the value of the current node

            // Add left and right children (even if null) to the queue
            queue.add(node.left);
            queue.add(node.right);
        }
        return sb.toString(); // Return the serialized string
    }

    // Decodes the serialized string back into a tree structure.
    public static TreeNode deserialize(String data) {
        // Handle edge case where input data is null or empty
        if (data == null || data.trim().equals("")) return null;

        Queue<TreeNode> queue = new LinkedList<>(); // Queue for reconstructing the tree
        String[] values = data.split(" "); // Split the serialized string by spaces

        TreeNode root = new TreeNode(Integer.parseInt(values[0])); // Create the root node
        queue.add(root); // Add the root node to the queue

        // Traverse the remaining serialized data to reconstruct the tree
        for (int i = 1; i < values.length; i++) {
            TreeNode parent = queue.poll(); // Get the next parent node from the queue

            // Process the left child if it's not "#"
            if (!values[i].equals("#")) {
                TreeNode left = new TreeNode(Integer.parseInt(values[i]));
                parent.left = left; // Link the left child
                queue.add(left); // Add the left child to the queue
            }

            // Move to the next value for the right child
            i++;
            if (i < values.length && !values[i].equals("#")) {
                TreeNode right = new TreeNode(Integer.parseInt(values[i]));
                parent.right = right; // Link the right child
                queue.add(right); // Add the right child to the queue
            }
        }
        return root; // Return the reconstructed tree
    }

    // Helper function to perform in-order traversal of the tree.
    public static void inorder(TreeNode root) {
        if (root == null) {
            return; // Base case: return if the node is null
        }
        inorder(root.left); // Visit left subtree
        System.out.print(root.val + " "); // Print the value of the current node
        inorder(root.right); // Visit right subtree
    }

    // Main Method
    public static void main(String[] args) {
        // Construct a sample tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        // Print the original tree using in-order traversal
        System.out.print("Original Tree: ");
        inorder(root);
        System.out.println();

        // Serialize the tree to a string
        String serialized = serialize(root);
        System.out.println("Serialized: " + serialized);

        // Deserialize the string back into a tree
        TreeNode deserialized = deserialize(serialized);

        // Print the tree after deserialization to verify
        System.out.print("Tree after deserialization: ");
        inorder(deserialized);
        System.out.println();
    }
}

// Output :
/*
Original Tree: 2 1 4 3 5
Serialized: 1 2 3 # # 4 5 # # # #
Tree after deserialization: 2 1 4 3 5
 */

// Algorithm / Intuition : Optimal Solution
/*
Algorithm / Intuition
Serialisation:
Step 1: Check if the tree is empty: If the root is null, return an empty string.

Step 2: Initialise an empty string: This string will store the serialised binary tree.

Step 3: Use a queue for level-order traversal: Initialise a queue and enqueue the root.

Step 4: Within the level-order traversal loop:

1. Dequeue a node from the queue.
2. If the node is null, append "#" to the string.
3. If the node is not null, append its data value along with a ‘,’ (comma) to the string.
This comma acts as a delimiter that separates the different node values in the string.
Enqueue its left and right children.

Step 5: Return the final string containing the serialised representation of the tree.

Deserialization:
Step 1:Check if the serialised data is empty: If it is, return null.

Step 2: Tokenize the serialised data: Use a stringstream to tokenize the input
string using the comma as a delimiter.

Step 3: Read the root value: Read the first token and create the root node with this value.

Step 4: Use a queue for level-order traversal: Initialise a queue and enqueue the root.

Step 5: Within the level-order traversal loop:

1. Dequeue a node from the queue.
2. Read the value for the left child from the stringstream.
3. If it is "#", set the left child to null. If it's not "#", create a new node
with the value and set it as the left child.
4. Read the next value in the stringstream for the right child.
5. If it is "#", set the right child to null. If it's not "#", create a new node with
the value and set it as the right child.
6. Enqueue the left and right children into the queue for further traversal.

Step 6: Return the reconstructed root: The final result is the root of the reconstructed tree.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=-YbXySKJsX8