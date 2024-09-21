package BinaryTrees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrder_Traversal {

    // Node class for the binary tree
    static class Node {
        int data;
        Node left;
        Node right;

        // Constructor to initialize
        // the node with a value
        Node(int val) {
            data = val;
            left = null;
            right = null;
        }
    }

    // Method 1 : Optimal Solution
    // Time Complexity: O(N) where N is the number of nodes in the binary tree. Each node of the
    // binary tree is enqueued and dequeued exactly once, hence all nodes need to be processed and
    // visited. Processing each node takes constant time operations which contributes to the overall
    // linear time complexity.
    // Space Complexity: O(N) where N is the number of nodes in the binary tree. In the worst case,
    // the queue has to hold all the nodes of the last level of the binary tree, the last level could
    // at most hold N/2 nodes hence the space complexity of the queue is proportional to O(N).The
    // resultant vector answer also stores the values of the nodes level by level and hence contains
    // all the nodes of the tree contributing to O(N) space as well.
    // Level Order Traversal Function
    public static List<List<Integer>> levelOrder(Node root) {
        // create a Queue
        Queue<Node> queue = new LinkedList<>();
        // create a List
        List<List<Integer>> ans = new ArrayList<>();

        // if, root is null, return ans
        if (root == null) {
            return ans;
        }

        // queue.add root
        queue.add(root);

        // while queue is not empty
        while (!queue.isEmpty()) {
            // calculate size
            int size = queue.size();

            // create a list of integer
            List<Integer> list = new ArrayList<>();

            // while size is greater than zero
            // then remove the current value
            // and than list.add current value
            // & decrease the size by one
            while (size > 0) {
                Node curr = queue.poll();
                list.add(curr.data);
                size--;

                // if, left subtree is not null
                // add this on queue
                if (curr.left != null) {
                    queue.add(curr.left);
                }

                // if, right subtree is not null
                // add this on queue
                if (curr.right != null) {
                    queue.add(curr.right);
                }
            }
            // & then ans to add list
            ans.add(list);
        }
        // finally, return ans
        return ans;
    }

    // Function to print the elements of a list
    static void printList(List<Integer> list) {
        // Iterate through the
        // list and print each element
        for (int num : list) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // Main function
    public static void main(String[] args) {
        // Creating a sample binary tree
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        // Perform level-order traversal
        List<List<Integer>> result = levelOrder(root);

        System.out.println("Level Order Traversal of Tree:");

        // Printing the level order traversal result
        for (List<Integer> level : result) {
            printList(level);
        }
    }
}

// Output :
/*
Level Order Traversal of Tree:
1
2 3
4 5 6 7
 */

// Approach / Intuition
/*
To perform a level-order traversal on a binary tree and store the nodes’ values in a 2D vector
representing each level, start by initialising an empty queue to hold the level by level nodes.
Enqueue the root node into the queue and traverse until the queue is empty. For each level, track
the number of nodes in that level, creating a temporary vector to deque and store them. At each node,
store its value in the temporary vector and enqueue its left and right children if they exist.Once all
the nodes at a level are processed add this 1D temporary vector to the final 2D vector, representing
that level. This process repeats until all levels are traversed. Finally, return this 2D vector
containing the level order traversal of the binary tree.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=EoAsWbO7sqg