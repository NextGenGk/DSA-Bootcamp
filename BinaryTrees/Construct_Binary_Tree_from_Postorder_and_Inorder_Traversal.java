package BinaryTrees;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Construct_Binary_Tree_from_Postorder_and_Inorder_Traversal {

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

    // Function to build a binary tree
    // from preorder and inorder traversals
    public static TreeNode buildTree(Vector<Integer> postorder, Vector<Integer> inorder) {
        // Edge Case
        if (inorder.size() != postorder.size()) {
            return null;
        }

        // Create a map to store the indices
        // of elements in the inorder traversal
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.size(); i++) {
            inMap.put(inorder.get(i), i);
        }

        // Call the recursive function
        // to build the binary tree
        TreeNode root = buildTree(postorder, 0, postorder.size() - 1, inorder,
                0, inorder.size() - 1, inMap);

        // Finally, return the root of the tree
        return root;
    }

    // Recursive helper function to build the tree
    public static TreeNode buildTree(Vector<Integer> postorder, int postStart, int postEnd,
                                     Vector<Integer> inorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        // Base case: If the start indices
        // exceed the end indices, return null
        if (postStart > postEnd || inStart > inEnd) {
            return null;
        }

        // Create a new TreeNode with value
        // at the current preorder index
        TreeNode root = new TreeNode(postorder.get(postEnd));

        // Find the index of the current root
        // value in the inorder traversal
        int inRoot = inMap.get(root.val);

        // Calculate the number of
        // elements in the left subtree
        int numsLeft = inRoot - inStart;

        // Recursively build the left subtree
        root.left = buildTree(postorder, postStart, postStart + numsLeft - 1,
                inorder, inStart, inRoot - 1, inMap);

        // Recursively build the right subtree
        root.right = buildTree(postorder, postStart + numsLeft, postEnd - 1,
                inorder, inRoot + 1, inEnd, inMap);

        // Return the current root node
        return root;
    }

    // Function to print the
    // inorder traversal of a tree
    private static void printInorder(TreeNode root) {
        if (root != null) {
            printInorder(root.left);
            System.out.print(root.val + " ");
            printInorder(root.right);
        }
    }

    // Function to print the
    // given vector
    private static void printVector(Vector<Integer> vec) {
        for (int i = 0; i < vec.size(); i++) {
            System.out.print(vec.get(i) + " ");
        }
        System.out.println();
    }

    // Main Function
    public static void main(String[] args) {
        // Example input vectors
        Vector<Integer> inorder = new Vector<>(java.util.Arrays.asList(40, 20, 50, 10, 60, 30));
        Vector<Integer> postorder = new Vector<>(java.util.Arrays.asList(40, 50, 20, 60, 30, 10));

        System.out.print("Inorder Vector: ");
        printVector(inorder);

        System.out.print("Preorder Vector: ");
        printVector(postorder);

        TreeNode root = buildTree(postorder, inorder);

        System.out.println("Inorder of Unique Binary Tree Created:");
        printInorder(root);
        System.out.println();
    }
}

// Output :
/*
Inorder Vector: 40 20 50 10 60 30
Postorder Vector: 40 50 20 60 30 10
Inorder of Unique Binary Tree Created: 40 20 50 10 60 30
 */

// Algorithm / Intuition : Optimal Solution
/*
Construct Binary Tree from Inorder and Preorder is a prerequisite to this problem as the
core approach remains recursively focusing on creating one node at a time We identify a
node from the postorder array, locate it in the inorder traversal which splits the array
into the left and right subtree.

To avoid unnecessary array duplication, we use variables (inStart, inEnd) and (postStart, postEnd)
on the inorder and postorder arrays, respectively. These variables effectively define the boundaries
of the current subtree within the original inorder and postorder traversals.

Similar to the previous algorithm, we employ a hashmap to store the index of each element in
the inorder traversal, transforming the search operation into a constant-time lookup.

Algorithm :
Step 1:Create an empty map to store the indices of elements in the inorder traversal.
Iterate through each element in the inorder traversal and store its index in the map
(inMap) using the element as the key and its index as the value.

Step 2: Create a recursive helper function buildTree with the following parameters:

1. Postorder vector
2. Start index of postorder (postStart), initially set to 0
3. End index of postorder (postEnd), initially set to postorder.size() - 1
4. Inorder vector
5. Start index of inorder (inStart), initially set to 0
6. End index of inorder (inEnd), initially set to inorder.size() - 1
7. Map for efficient root index lookup in the inorder traversal.

Step 3: Base Case: Check if postStart is greater than postEnd or inStart is greater than inEnd.
If true, return NULL, indicating an empty subtree/node.

Step 4: The root node for the current subtree is the last element in the postorder traversal
(postorder[postEnd]).

Step 5: Find the index of this root node in the inorder traversal using the map (inMap[rootValue]).
This is the rootIndex.

Step 6: Hence, the left subtree ranges from inStart to rootIndex. Subtracting these indexes gives
us the leftSubtreeSize.

Step 7: Make two recursive calls to buildTree to build the left and right subtrees:

For the left subtree:
1. Update postStart to postEnd - leftSubtreeSize (moving to the next element in postorder)
2. Update postEnd to postEnd - 1 (end of left subtree in postorder)
3. Update inEnd to rootIndex - 1 (end of left subtree in inorder)

For the right subtree:
1. Update postStart to postStart (moving to the next element in postorder)
2. Update postEnd to postEnd - leftSubtreeSize - 1 (end of right subtree in postorder)
3. Update inStart to rootIndex + 1 (start of right subtree in inorder)

Step 8: Return the root node constructed for the current subtree.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=LgLRTaEMRVc
