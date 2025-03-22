package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Cycle_Detection_Undirected_BFS {

    // Method 1 : Optimal Solution
    // Time Complexity: O(N) + O(V+E), Where O(N) is for outer loop and inner loop
    // runs in total a single BFS over entire graph, and we know BFS takes a time of O(V+E).
    // Space Complexity: O(N) + O(N), Space for queue data structure and visited array.
    // Helper class to store a node and its parent during BFS traversal
    static class Pair {
        int node;   // The current node
        int parent; // The parent node of the current node

        Pair(int node, int parent) {
            this.node = node;
            this.parent = parent;
        }
    }

    /**
     * Checks if an undirected graph contains a cycle using BFS.
     *
     * @param adjacencyList The adjacency list representation of the graph.
     * @param source        The starting node for BFS traversal.
     * @param visited       Array to track visited nodes.
     * @return true if a cycle is detected, otherwise false.
     */
    public static boolean hasCycle(ArrayList<ArrayList<Integer>> adjacencyList, int source, boolean[] visited) {
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(source, -1)); // Start BFS with the source node and no parent (-1)
        visited[source] = true; // Mark the source node as visited

        while (!queue.isEmpty()) {
            int currentNode = queue.peek().node;   // Get the front node from the queue
            int parentNode = queue.peek().parent; // Get its parent
            queue.poll(); // Remove the processed node from the queue

            // Traverse all adjacent nodes
            for (Integer adjacentNode : adjacencyList.get(currentNode)) {
                if (!visited[adjacentNode]) {
                    // If the adjacent node is not visited, add it to the queue
                    queue.add(new Pair(adjacentNode, currentNode));
                    visited[adjacentNode] = true; // Mark the adjacent node as visited
                } else if (parentNode != adjacentNode) {
                    // If the adjacent node is visited and not the parent of the current node, a cycle exists
                    return true;
                }
            }
        }
        return false; // No cycle detected
    }

    /**
     * Determines whether an undirected graph contains a cycle.
     *
     * @param vertices      The number of vertices in the graph.
     * @param adjacencyList The adjacency list representation of the graph.
     * @return true if a cycle exists, otherwise false.
     */
    public static boolean isCycle(int vertices, ArrayList<ArrayList<Integer>> adjacencyList) {
        boolean[] visited = new boolean[vertices]; // Array to track visited nodes

        // Check for cycles in each connected component of the graph
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                // If the node is not visited, perform BFS to check for cycles
                if (hasCycle(adjacencyList, i, visited)) return true;
            }
        }
        return false; // No cycles detected in any component
    }

    // Main Function
    public static void main(String[] args) {
        int V = 4;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        adj.get(0).add(1);
        adj.get(1).add(0);

        adj.get(1).add(2);
        adj.get(2).add(1);

        adj.get(2).add(3);
        adj.get(3).add(2);

        adj.get(0).add(2);
        adj.get(2).add(0);

        System.out.println(isCycle(V, adj));
    }
}

// Output:
// true

// Intuition: Optimal Solution
/*
The intuition is that we start from a node, and start doing BFS level-wise, if somewhere
down the line, we visit a single node twice, it means we came via two paths to end up at the
same node. It implies there is a cycle in the graph because we know that we start from different
directions but can arrive at the same node only if the graph is connected or contains a cycle,
otherwise we would never come to the same node again.

Approach:
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
 */

// Striver's (Video Explanation): https://www.youtube.com/watch?v=BPlrALf1LDU