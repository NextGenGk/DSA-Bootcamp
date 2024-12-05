package BinaryTrees;

import java.util.Stack;

public class Flatten_Binary_Tree_to_Linked_List {

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

    // Time Complexity: O(N) where N is the number of nodes in the Binary Tree.
    // Each node of the binary node is visited exactly once. At each node,
    // constant-time operations are performed. Hence the time complexity is O(N).

    // Space Complexity : O(log2N)where N is the number of nodes in the Binary Tree.
    // There are no additional data structures or space used but the auxiliary stack
    // space is used during recursion. Since the recursion depth can be at most equal
    // to the height to the Binary Tree, the space complexity is O(H) where H is the
    // height of the Binary Tree. In the ideal case, H = log2N and in the worst case
    // H = N (skewed tree).

    static class Solution_Brute {
        // Initialize a global variable
        // 'prev' to keep track of the
        // previously processed node.
        static TreeNode prev = null;

        // Function to flatten a binary tree
        // to a right next Linked List structure
        public static void flatten(TreeNode root) {
            // Base case: If the current
            // node is null, return.
            if (root == null) {
                return;
            }

            // Recursive call to
            // flatten the right subtree
            flatten(root.right);

            // Recursive call to
            // flatten the left subtree
            flatten(root.left);

            // At this point, both left and right
            // subtrees are flattened, and 'prev'
            // is pointing to the rightmost node
            // in the flattened right subtree.

            // Set the right child of
            // the current node to 'prev'.
            root.right = prev;

            // Set the left child of the
            // current node to null.
            root.left = null;

            // Update 'prev' to the current
            // node for the next iteration.
            prev = root;
        }
    }

    // Method 2 : Better Solution

    // Time Complexity: O(N) where N is the number of nodes in the Binary Tree.
    // Each node of the binary node is visited exactly once. At each node, constant-
    // time operations are performed. Hence the time complexity is O(N).

    // Space Complexity : O(log2N) where N is the number of nodes in the Binary Tree.
    // There are no additional data structures or space used but the auxiliary stack
    // space is used during recursion. Since the recursion depth can be at most equal
    // to the height to the Binary Tree, the space complexity is O(H) where H is the
    // height of the Binary Tree. In the ideal case, H = log2N and in the worst case
    // H = N (skewed tree).

    static class Solution_Better {
        // Initialize a global variable
        // 'prev' to keep track of the
        // previously processed node.
        TreeNode prev = null;

        // Function to flatten a binary tree
        // to a right next Linked List structure
        public static void flatten(TreeNode root) {
            // Base case: If the current
            // node is null, return.
            if (root == null) {
                return;
            }
            // Use a stack for
            // iterative traversal.
            Stack<TreeNode> st = new Stack<>();
            // Push the root node
            // onto the stack.
            st.push(root);

            // Continue the loop until
            // the stack is empty.
            while (!st.empty()) {
                // Get the top node from the stack.
                TreeNode cur = st.pop();

                if (cur.right != null) {
                    // Push the right child
                    // onto the stack.
                    st.push(cur.right);
                }

                if (cur.left != null) {
                    // Push the left child
                    // onto the stack.
                    st.push(cur.left);
                }

                if (!st.empty()) {
                    // Connect the right child to
                    // the next node in the stack.
                    cur.right = st.peek();
                }

                // Set the left child to null to
                // form a right-oriented linked list.
                cur.left = null;
            }
        }
    }

    // Method 3 : Optimal Solution

    // Time Complexity: O(2N) where N is the number of nodes in the Binary Tree.
    // The time complexity is linear, as each node is visited at most twice
    // (once for establishing the temporary link and once for reverting it).
    // In each step, we perform constant-time operations, such as moving to the
    // left or right child and updating pointers.

    // Space Complexity: O(1) The space complexity is constant, as the algorithm
    // uses only a constant amount of extra space irrespective of the input size.
    // Morris Traversal does not use any additional data structures like stacks or
    // recursion, making it an in-place algorithm.
    // The only space utilised is for a few auxiliary variables, such as pointers to
    // current and in-order predecessor nodes.

    static class Solution_Optimal {
        // Function to flatten a binary tree
        // to a right next Linked List structure
        public void flatten(TreeNode root) {
            // Initialize a pointer
            // 'curr' to the root of the tree
            TreeNode curr = root;

            // Iterate until 'curr'
            // becomes NULL
            while (curr != null) {
                // Check if the current
                // node has a left child
                if (curr.left != null) {
                    // If yes, find the rightmost
                    // node in the left subtree
                    TreeNode pre = curr.left;
                    while (pre.right != null) {
                        pre = pre.right;
                    }

                    // Connect the rightmost node in
                    // the left subtree to the current
                    // node's right child
                    pre.right = curr.right;

                    // Move the entire left subtree to the
                    // right child of the current node
                    curr.right = curr.left;

                    // Set the left child of
                    // the current node to NULL
                    curr.left = null;
                }

                // Move to the next node
                // on the right side
                curr = curr.right;
            }
        }
    }

    // Print the preorder traversal of the
    // Original Binary Tree
    public static void printPreorder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        printPreorder(root.left);
        printPreorder(root.right);
    }

    // Print the Binary Tree along the
    // Right Pointers after Flattening
    public static void printFlattenTree(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        printFlattenTree(root.right);
    }

    // Main Function
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.right.right = new TreeNode(6);
        root.right.left = new TreeNode(8);
        root.right.right = new TreeNode(7);

        System.out.print("Binary Tree Preorder: ");
        printPreorder(root);
        System.out.println();

        // Uncomment the desired solution to test
        new Solution_Optimal().flatten(root);

        System.out.print("Binary Tree After Flatten: ");
        printFlattenTree(root);
        System.out.println();
    }
}

// Output :
// Binary Tree Preorder: 1 2 4 5 6 3 8 7,
// Binary Tree After Flatten: 1 2 4 5 6 3 8 7

// Algorithm / Intuition : Brute Force
/*
Algorithm / Intuition
The intuition behind this approach is to perform a reverse pre-order traversal where,
instead of simply visiting nodes, we flatten the tree into a linked list as we traverse it.

We start at the root of the tree and recursively do the following for each node,
we first ensure that the right subtree is flattened into a linked list. This means
that all nodes in the right subtree are processed and attached to the linked list
in the correct order.

Next, we do the same for the left subtree. This ensures that all nodes in the left
subtree are processed and attached to the linked list in the correct order. Once both
subtrees are flattened, we attach the flattened left subtree as the right child of
the current node. Since we're using the right pointer of the binary tree as the next
pointer for the linked list, this effectively attaches the left subtree to the current
node in the linked list. Finally, we attach the flattened right subtree to the rightmost
node of the flattened left subtree. This ensures that the right subtree is properly
attached to the end of the linked list.

Algorithm
Step 1:Initialise a global variable `prev` to keep track of the previously processed node.
Initially set it to null.

Step 2: Base Case: If the current node is null, return null.

Step 3: Flatten the Right and Left Subtree: Recursively flatten the right and left
subtree of the current node by calling the flatten function on the current node's
right and left child.

Step 4: Attach the Right Subtree to the Flattened Left Subtree: Set the right child
f the current node to the value of `prev` since `prev` points to the rightmost node
in the flattened left subtree. This effectively attaches the right subtree to the
right of the rightmost node of the left subtree.

Step 5:Attach the Left Subtree as Right Child: Set the right child of the current
node to the left subtree.

Set the left child of the current node to null since we are flattening the binary
tree to a linked list and there should be no left child.

Step 6:Update `prev` to the current node for the next iteration and recursion step.
 */

// Algorithm / Intuition : Better Solution
/*
Algorithm / Intuition
This approach is an extension of the previous approach where we use a stack to simulate recursion.

Algorithm

Step 1: Base Case: If the root node is null, we return as there is no tree to flatten.

Step 2: Initialise Stack: Create a stack to perform an iterative traversal of the binary tree.
Push the root node onto the sack to start the traversal.

Step 3: Iterative Traversal with Stack: While the stack is not empty, repeat the following steps:

1. Pop the top node from the stack.
2. If the popped node has a right child, push it onto the stack. This ensures that the right child
   is processed after the left child nodes.
3. If the popped node has a left child, push it onto the stack.
4. If the stack is not empty after pushing the left child, connect the right pointer of the
current node (popped from the stack) to the top node of the stack. This creates the linked
list structure by setting the next pointer.
5. Set the left pointer of the current node to null as we have to form a right skewed linked list.

Step 4: Once the stack becomes empty, the traversal is complete and the binary tree is
flattened into a right next linked list structure.
 */

// Algorithm / Intuition : Optimal Solution
/*
Morris Traversal is an algorithm that allows preorder/inorder tree traversal without
using any extra space for stack or recursion. It utilises threaded binary trees to
traverse the tree without requiring a stack for saving the path. Read more about Morris
Preorder Traversal here

Algorithm
Step 1: Start with the current node as the root of the tree.

Step 2: While the current node is not null we traverse the tree in a while loop:

1. If the current node has a left child:
    1. Find the rightmost node in the left subtree.
    2. Connect the rightmost node of the left subtree to the current node’s right child.
    3. Update the current node’s right child to be its left child.
    4. Set the current node’s left child to null.
2. Move to the current node’s right child.
3. If the current node has no right child:
    1. Move to the current node’s left child (if it exists) or null.
Step 3: Repeat until all nodes are processed.
 */

// Striver (Video Explanation) : https://www.youtube.com/watch?v=sWf7k1x9XR4