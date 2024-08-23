package StacksAndQueues;

import java.util.HashMap;
import java.util.Map;

public class Implement_LRU_Cache {

    // Method 1 : Optimal Solution
    // Time Complexity: O(1) for both get and put operations.
    // Space Complexity: O(n), where n is the capacity of the cache.
    static class LRUCache {
        // Create dummy head and tail nodes for the doubly linked list
        Node head = new Node(0, 0);
        Node tail = new Node(0, 0);

        // A map to store key to node mapping for O(1) access
        Map<Integer, Node> map = new HashMap<Integer, Node>();

        // The maximum capacity of the cache
        int capacity = 0;

        // Constructor to initialize LRUCache with a given capacity
        public LRUCache(int capacity) {
            this.capacity = capacity;

            // Link head and tail together, establishing a doubly linked list structure
            head.next = tail;
            tail.prev = head;
        }

        // Retrieve a value from the cache by key
        public int get(int key) {
            // Check if the key exists in the cache
            if (map.containsKey(key)) {
                // Fetch the node corresponding to the key
                Node node = map.get(key);

                // Remove the node from its current position in the list
                remove(node);

                // Insert the node right after the head to mark it as most recently used
                insertAfterHead(node);

                // Return the value stored in the node
                return node.value;
            }
            // Return -1 if the key is not found in the cache
            return -1;
        }

        // Add a new key-value pair to the cache, or update an existing key
        public void put(int key, int value) {
            // If the key already exists, remove it from the list so it can be updated
            if (map.containsKey(key)) {
                remove(map.get(key));
            }
            // If the cache is full, remove the least recently used item (i.e., the item before the tail)
            else if (map.size() == capacity) {
                remove(tail.prev);
            }
            // Insert the new node with the key-value pair right after the head
            insertAfterHead(new Node(key, value));
        }

        // Helper method to insert a node after the head of the doubly linked list
        public void insertAfterHead(Node node) {
            // Add the node to the map for quick access
            map.put(node.key, node);

            // Insert the node into the list right after the head
            Node currentAfterHead = head.next;
            head.next = node;
            node.next = currentAfterHead;
            node.prev = head;
            currentAfterHead.prev = node;
        }

        // Helper method to remove a node from the doubly linked list
        public void remove(Node node) {
            // Remove the node from the map
            map.remove(node.key);

            // Reconnect the previous and next nodes, effectively removing the node from the list
            Node prevNode = node.prev;
            Node nextNode = node.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
        }

        // Inner class representing a node in the doubly linked list
        class Node {
            // Pointers to the next and previous nodes in the list
            Node next, prev;

            // The key-value pair stored in the node
            int key, value;

            // Constructor to initialize a node with a key and value
            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }
    }

    // Main Function
    public static void main(String[] args) {
        // Example of calling LRUCache class methods as described by LeetCode test cases

        // Create an LRUCache with a capacity of 2
        LRUCache cache = new LRUCache(2);

        // Example Test Case 1
        cache.put(1, 1);          // Cache is {1=1}
        cache.put(2, 2);          // Cache is {1=1, 2=2}
        System.out.println(cache.get(1));  // Returns 1 (cache is {2=2, 1=1})

        cache.put(3, 3);          // Evicts key 2, cache is {1=1, 3=3}
        System.out.println(cache.get(2));  // Returns -1 (not found)

        cache.put(4, 4);          // Evicts key 1, cache is {4=4, 3=3}
        System.out.println(cache.get(1));  // Returns -1 (not found)
        System.out.println(cache.get(3));  // Returns 3 (cache is {4=4, 3=3})
        System.out.println(cache.get(4));  // Returns 4 (cache is {3=3, 4=4})
    }
}

// Output : [null,null,null,1,null,-1,null,-1,3,4]

// Approach : Optimal Solution
/*
1. Initialization (LRUCache):
    1. Initialize the cache with a given capacity.
    2. Create a dummy head and dummy tail for the doubly linked list. These nodes help simplify the
    insertion and deletion of nodes in the list.
    3. Connect the dummy head's next to the dummy tail, and the dummy tail's prev to the dummy head.

2. Get (get(int key)):
    1. Check Existence:
        1. If the key is not present in the cache (map), return -1 (indicating that the key is not found).
    2. Update Usage:
        1. If the key is present, retrieve the corresponding node from the map.
        2. Remove the node from its current position in the linked list.
        3. Re-insert the node at the head of the linked list (since it is now the most recently used item).

    3. Return Value:
        1. Return the value associated with the key.

3. Put (put(int key, int value)):
    1. Check Existence:
        1. If the key already exists in the cache, retrieve the corresponding node and remove it from
        the linked list. This is because the value associated with the key is going to be updated.
    2. Eviction (if necessary):
        1. If the cache is at full capacity (i.e., the size of the map equals the capacity), remove the
        least recently used node. This node is the one just before the dummy tail.
        2. Remove this node from both the linked list and the map.
    3. Insert New Node:
        1. Insert a new node with the given key and value at the head of the doubly linked list
        (since it is the most recently used).
        2. Add this node to the map.

Helper Functions:
1. Insert After Head (insertAfterHead(Node node)):
    1. Insert the given node right after the dummy head node in the doubly linked list (since this
    is the most recently used position).
    2. Update the next and prev pointers accordingly.

2. Remove (remove(Node node)):
    1. Remove the given node from its current position in the doubly linked list by adjusting the next
    and prev pointers of its neighbors.
    2. Also, remove the node from the map.
 */

// Time & Space Complexity :
/*
Time Complexity:
1. get(int key):
    1. The get operation involves checking if the key exists in the map. This lookup takes O(1) time
     because HashMap provides constant time complexity for lookups.
    2. If the key exists, the node needs to be removed from its current position in the doubly linked
    list and moved to the head (most recently used position). Removing and inserting a node in a
    doubly linked list also takes O(1) time.
    3. Thus, the total time complexity for get is O(1).

2. put(int key, int value):
    1. The put operation involves first checking if the key already exists in the cache (O(1) time
    due to HashMap lookup).
    2. If the key exists, the node needs to be removed from the list and re-inserted at the head,
    which takes O(1) time.
    3. If the key does not exist, and the cache is at full capacity, the least recently used node
    (tail node) needs to be removed. This removal operation also takes O(1) time.
    4. Then a new node is inserted at the head of the list, which also takes O(1) time.
    5. Therefore, the total time complexity for put is O(1).

Summary of Time Complexity:
get(int key): O(1)
put(int key, int value): O(1)

Space Complexity:
1. Doubly Linked List (Nodes): You are storing up to capacity number of nodes. Each node takes O(1) space.
2. HashMap: You are storing up to capacity number of key-value pairs. Each key-value pair takes O(1) space.

Thus, for n as the capacity of the cache, the total space complexity is O(n).
Summary of Space Complexity:

O(n), where n is the capacity of the cache.

Conclusion:
Time Complexity: O(1) for both get and put operations.
Space Complexity: O(n), where n is the capacity of the cache.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=z9bJUPxzFOw