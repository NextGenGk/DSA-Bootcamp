package BinaryTrees;

public class Count_Total_Nodes_in_Complete_Binary_Tree {

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

    // Time Complexity: O(N) where N is the number of nodes in the binary tree
    // as each node of the binary tree is visited exactly once.

    // Space Complexity : O(N) where N is the number of nodes in the binary tree.
    // This is because the recursive stack uses an auxiliary space which can
    // potentially hold all nodes in the tree when dealing with a skewed tree
    // (all nodes have only one child), consuming space proportional to the number
    // of nodes. In the average case or for a balanced tree, the maximum number
    // of nodes that could be in the stack at any given time would be roughly
    // the height of the tree hence O(log2N).

    // Function to perform inorder
    // traversal and count nodes
    public void inorder(TreeNode root, int[] count) {
        // Base case: If the current
        // node is null, return
        if (root == null) {
            return;
        }

        // Increment count
        // for the current node
        count[0]++;

        // Recursively call inorder
        // on the left subtree
        inorder(root.left, count);

        // Recursively call inorder
        // on the right subtree
        inorder(root.right, count);
    }

    // Function to count nodes in the binary tree
    public int countNodes(TreeNode root) {
        // Base case: If the root is null,
        // the tree is empty, return 0
        if (root == null) {
            return 0;
        }

        // Initialize count variable to
        // store the number of nodes
        int[] count = {0};

        // Call the inorder traversal
        // function to count nodes
        inorder(root, count);

        // Return the final count of
        // nodes in the binary tree
        return count[0];
    }

    // Method 2 : Optimal Solution

    // Time Complexity: O(log N * log N) where N is the number of nodes in the Binary Tree.
    // The calculation of leftHeight and rightHeight takes O(log N) time.
    // In the worst case, when encountering the second case (leftHeight != rightHeight),
    // the recursive calls are made at most log N times (the height of the tree).
    // Therefore, the total time complexity is O(log N * log N).

    // Space Complexity : O(H) ~ O(N) where N is the number of nodes in the Binary Tree.
    // The space complexity is determined by the maximum depth of the recursion stack,
    // which is equal to the height of the binary tree.
    // Since the given tree is a complete binary tree, the height will always be log N.
    // Therefore, the space complexity is O(log N).

    // Function to count the total number
    // of nodes in a Complete Binary Tree
    public static int countNodes1(TreeNode root) {
        // Check if the tree is empty
        if (root == null) {
            return 0;
        }

        // Find the height of the left subtree
        int lh = findHeightLeft(root);
        // Find the height of the right subtree
        int rh = findHeightRight(root);

        // If the heights are equal, the tree
        // is a full binary tree, and we can
        // calculate the total nodes
        if (lh == rh) {
            return (1 << lh) - 1;
        }

        // If the heights are not equal,
        // recursively count nodes in the
        // left and right subtrees
        return 1 + countNodes1(root.left) + countNodes1(root.right);
    }

    // Function to find the
    // height of the left subtree
    private static int findHeightLeft(TreeNode node) {
        int height = 0;
        while (node != null) {
            height++;
            node = node.left;
        }
        return height;
    }

    // Function to find the
    // height of the right subtree
    private static int findHeightRight(TreeNode node) {
        int height = 0;
        while (node != null) {
            height++;
            node = node.right;
        }
        return height;
    }

    // Main Function
    public static void main(String[] args) {
        // Create the binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);

        // Call the countNodes function
        int totalNodes = countNodes1(root);

        // Print the result
        System.out.println("Total number of nodes in the Complete Binary Tree: " + totalNodes);
    }
}

// Output : Total number of nodes in the Complete Binary Tree: 6

// Algorithm / Intuition : Brute Force
/*
A brute force approach would be to traverse the tree using inorder (or any)
traversal and count the number of nodes as we are traversing the tree.
In Inorder traversal, we visit the left subtree first, then the current node,
and finally the right subtree. By incrementing the counter for each visited
node, we effectively count all nodes in the binary tree.
 */

// Algorithm / Intuition : Optimal Solution
/*
Given that the binary is a complete binary tree, we can exploit its properties
to optimise the algorithm and achieve a better time complexity. In a complete
binary tree, the last level may not be completely filled, but the nodes are
positioned from left to right. This property allows us to determine the number
of nodes using just the height. The relationship between the height of the binary
tree (h) and the maximum number of nodes it can have, denoted by the formula:
Maximum Number of Nodes: 2^h-1

If the last level of a binary tree is perfectly filled, known as a perfect binary
tree, the count of nodes can be determined by the formula: 2h-1, where h is the
height. To check if the last level of the Binary Tree is filled or not we can
compare the left and right heights of the tree.

1. If the left height equals right height, it indicates that the last level
is completely filled.

2. If the left height does not equal right height, the last level is not
completely filled.

3. In the case where left height and right height differ, we can employ a
recursive approach. We recursively calculate the number of nodes in the left
subtree and in the right subtree , and then return the total count as
1 + leftNodes + rightNodes. If the height of the left subtree is equal to the
height of the right subtree, we can directly calculate using the 2h-1 formula.

Algorithm:
Step 1: Base Case If the given node is null, we return 0 as there are no nodes to count.
Step 2: Recursive Calls: Recursively find the left height and right height of the Binary Tree.
Step 3: Comparison: If the left height is equal to the right height implies that the tree’s
last level is completely filled. Return the count of nodes using the formula:
return (2 << lh) - 1, where << represents the left shift operator and represents the power of 2.
Step 4: If the left height is not equal to the right height implies that the tree’s last
level is not completely filled. Recursively call the function to the left and right
subtree and return the final number of nodes as 1 + countNodes(root->left) + countNodes(root->right)

Step 5: Implement the find left height and right height functions.

1. Start with the variable height set to 0.
2. Use a while loop to traverse the left/right side of the tree incrementing the height
until reaching a leaf node.
3. Return the calculated height.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=u-yWemKGWO0
