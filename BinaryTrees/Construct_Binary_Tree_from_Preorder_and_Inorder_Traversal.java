package BinaryTrees;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal {

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

    // Time Complexity: O(N) where N is the number of nodes in the Binary Tree.
    // This is because each node of the Binary Tree is visited once.

    // Space Complexity: O(N) where N is the number of nodes in the Binary Tree.
    // The inorder hashmap to store the inorder array for fast lookup takes up space
    // proportional to the input nodes. An auxiliary stack space ~ O(H) where H is
    // the height of the Binary Tree is used. This is the stack space used to build
    // the tree recursively. In the case of a skewed tree, the height of the tree
    // will be H ~ N hence the worst case auxiliary space is O(N).

    // Function to build a binary tree
    // from preorder and inorder traversals
    public static TreeNode buildTree(Vector<Integer> preorder, Vector<Integer> inorder) {
        // Edge Case
        if (inorder.size() != preorder.size()) {
            return null;
        }

        // Create a map to store indices
        // of elements in the inorder traversal
        Map<Integer, Integer> inMap = new HashMap<>();

        // Populate the map with indices
        // of elements in the inorder traversal
        for (int i = 0; i < inorder.size(); i++) {
            inMap.put(inorder.get(i), i);
        }

        // Call the private helper function
        // to recursively build the tree
        TreeNode root = buildTree(preorder, 0, preorder.size() - 1, inorder, 0,
                inorder.size() - 1, inMap);

        // Return, the root of the tree
        return root;
    }

    // Recursive helper function to build the tree
    private static TreeNode buildTree(Vector<Integer> preorder, int preStart, int preEnd,
                                      Vector<Integer> inorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        // Base case: If the start indices
        // exceed the end indices, return null
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        // Create a new TreeNode with value
        // at the current preorder index
        TreeNode root = new TreeNode(preorder.get(preStart));

        // Find the index of the current root
        // value in the inorder traversal
        int inRoot = inMap.get(root.val);

        // Calculate the number of
        // elements in the left subtree
        int numsLeft = inRoot - inStart;

        // Recursively build the left subtree
        root.left = buildTree(preorder, preStart + 1, preStart + numsLeft,
                inorder, inStart, inRoot - 1, inMap);

        // Recursively build the right subtree
        root.right = buildTree(preorder, preStart + numsLeft + 1, preEnd,
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
        Vector<Integer> inorder = new Vector<>(java.util.Arrays.asList(9, 3, 15, 20, 7));
        Vector<Integer> preorder = new Vector<>(java.util.Arrays.asList(3, 9, 20, 15, 7));

        System.out.print("Inorder Vector: ");
        printVector(inorder);

        System.out.print("Preorder Vector: ");
        printVector(preorder);

        TreeNode root = buildTree(preorder, inorder);

        System.out.println("Inorder of Unique Binary Tree Created:");
        printInorder(root);
        System.out.println();
    }
}

// Output :
/*
Inorder Vector: 9 3 15 20 7,
Preorder Vector: 3 9 20 15 7 ,
Inorder of Unique Binary Tree Created: 9 3 15 20 7
 */

// Algorithm / Intuition : Optimal Solution
/*
Algorithm / Intuition
Before we dive into the algorithm, it's essential to grasp the significance of
inorder and preorder traversals. Inorder traversal allows us to identify a node
and its left and right subtrees, while preorder traversal ensures we always encounter
the root node first. Leveraging these properties, we can uniquely construct a binary
tree. The core of our approach lies in a recursive algorithm that creates one node
at a time. We locate this root node in the inorder traversal, which splits the array
into the left and right subtrees.

The inorder array keeps getting divided into left and subtrees hence to avoid unnecessary
array duplication, we use variables (inStart, inEnd) and (preStart, preEnd) on the inorder
and preorder array respectively. These variables effectively define the boundaries of the
current subtree within the original inorder and preorder traversals. Everytime we encounter
the root of a subtree via preorder traversal, we locate its position in the inorder array
to get the left and right subtrees. So to save complexity on the linear look up, we employ
a hashmap to store the index of each element in the inorder traversal. This transforms the
search operation into a constant-time lookup.

Algorithm:

Step 1:Create an empty map to store the indices of elements in the inorder traversal.
Iterate through each element in the inorder traversal and store its index in the map
(inMap) using the element as the key and its index as the value.

Step 2: Create a recursive helper function `buildTree` with the following parameters:

1. Preorder vector
2. Start index of preorder (preStart), initially set to 0
3. End index of preorder (preEnd), initially set to preorder.size() - 1.
4. Inorder vector
5. Start index of inorder (inStart), initially set to 0.
6. End index of inorder (inEnd), initially set to inorder.size() - 1.
7. Map for efficient root index lookup in the inorder traversal.

Step 3: Base Case: Check if preStart is greater than preEnd or inStart is greater than
inEnd. If true, return NULL, indicating an empty subtree/node.

Step 4: The root node for the current subtree is the first element in the preorder traversal
(preorder[preStart]). Find the index of this root node in the inorder traversal using the map
(inMap[rootValue]). This is the rootIndex.

Step 5: Hence, the left subtree ranges from inStart to rootIndex. Subtracting these indexes
gives us the leftSubtreeSize.

Step 6: TMake two recursive calls to buildTree to build the left and right subtrees:
For the left subtree:
1. Update preStart to preStart + 1 (moving to the next element in preorder)
2. Update preEnd to preStart + leftSubtreeSize (end of left subtree in preorder)
3. Update inEnd to rootIndex - 1 (end of left subtree in inorder)

For the right subtree:
1. Update preStart to preStart + leftSubtreeSize + 1 (moving to the next element after the left subtree)
2. Update preEnd to the original preEnd (end of right subtree in preorder)
3. Update inStart to rootIndex + 1 (start of right subtree in inorder)

Step 7: Return the root node constructed for the current subtree. The function returns
the root of the entire binary tree constructed from the preorder and inorder traversals.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=aZNaLrVebKQ
