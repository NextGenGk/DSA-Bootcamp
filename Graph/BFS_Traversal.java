package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS_Traversal {

    // Method 1 : Optimal Solution
    // Time Complexity: O(N) + O(2E), Where N = Nodes, 2E is for total degrees as we traverse all adjacent nodes.
    // Space Complexity: O(3N) ~ O(N), Space for queue data structure visited array and an adjacency list
    // Function to return Breadth-First Traversal (BFS) of a given graph
    public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        // Result list to store BFS traversal order
        ArrayList<Integer> result = new ArrayList<>();

        // Boolean array to keep track of visited nodes
        boolean[] visited = new boolean[V];

        // Queue for BFS traversal
        Queue<Integer> queue = new LinkedList<>();

        // Start BFS from node 0
        queue.add(0);
        visited[0] = true;

        // BFS Algorithm
        while (!queue.isEmpty()) {
            // Remove a node from the queue and add it to the result list
            Integer node = queue.poll();
            result.add(node);

            // Traverse all adjacent nodes
            for (Integer it : adj.get(node)) {
                // If the node is not visited, mark it visited and add to queue
                if (!visited[it]) {
                    visited[it] = true;
                    queue.add(it);
                }
            }
        }
        return result; // Return the BFS traversal order
    }
}

// Output :
// For the given graph, the BFS traversal order is [0, 1, 2, 3]

// Algorithm :
/*
Algorithm:
1. Initialize a queue and a visited array.
2. Start from node 0:
   - Mark it as visited.
   - Push it into the queue.
3. While the queue is not empty:
   - Dequeue a node and add it to the result.
   - Traverse its adjacent nodes:
     - If an adjacent node is not visited, mark it as visited and enqueue it.
4. Repeat until all reachable nodes are visited.
5. Return the result list containing the BFS traversal order.

Example:
Consider the following graph:

    0 -- 1
    |    |
    2 -- 3

Adjacency list representation:
V = 4
adj = { {1, 2}, {0, 3}, {0, 3}, {1, 2} }

BFS starting from node 0:
Queue: [0] → [1, 2] → [2, 3] → [3]
Result: [0, 1, 2, 3]
*/
