package StacksAndQueues;

import LinkedList.Implementation.DoublyLinkedList;

import java.util.HashMap;
import java.util.Map;

public class Implement_LFU_Cache {

    // Helper Class LFU
    static class LFUCache {
        final int capacity; // Total capacity of the LFU cache
        int curSize; // Current number of items in the LFU cache
        int minFrequency; // Minimum frequency in the cache (used to evict the least frequently used item)
        Map<Integer, DLLNode> cache; // Maps keys to their corresponding nodes (stores key-node mapping)
        Map<Integer, DoubleLinkedList> frequencyMap; // Maps frequency values to doubly linked lists of nodes

        // Constructor
        public LFUCache(int capacity) {
            this.capacity = capacity; // Set the maximum capacity of the cache
            this.curSize = 0; // Initialize the current size of the cache as 0
            this.minFrequency = 0; // Initialize the minimum frequency as 0 (no nodes yet)
            this.cache = new HashMap<>(); // Initialize the cache map
            this.frequencyMap = new HashMap<>(); // Initialize the frequency map
        }

        /**
         * Retrieves the value for a given key from the cache.
         * If the key exists, the frequency of the node is updated and the node is relocated.
         * Otherwise, return -1 if the key is not present in the cache.
         */
        public int get(int key) {
            DLLNode curNode = cache.get(key); // Get the node associated with the key
            if (curNode == null) { // If the node is not in the cache, return -1
                return -1;
            }
            updateNode(curNode); // Update the node's frequency and position
            return curNode.val; // Return the value of the node
        }

        /**
         * Inserts a new node into the cache or updates an existing node's value.
         * - If the key exists in the cache, update the value and frequency of the node.
         * - If the key does not exist, check if there is enough space.
         *   - If the cache is full, remove the least frequently used node.
         *   - Add the new node into the cache.
         */
        public void put(int key, int value) {
            // If the cache has no capacity, do nothing
            if (capacity == 0) {
                return;
            }

            if (cache.containsKey(key)) { // If the node exists in the cache, update its value
                DLLNode curNode = cache.get(key);
                curNode.val = value;
                updateNode(curNode); // Update its frequency and position
            } else {
                curSize++; // Increment the cache size for the new node
                if (curSize > capacity) { // If the cache is full
                    // Get the linked list with the minimum frequency
                    DoubleLinkedList minFreqList = frequencyMap.get(minFrequency);
                    // Remove the least recently used node from the list and cache
                    cache.remove(minFreqList.tail.prev.key);
                    minFreqList.removeNode(minFreqList.tail.prev);
                    curSize--; // Decrement the current size
                }

                // Reset the minimum frequency to 1 because we are adding a new node
                minFrequency = 1;
                // Create a new node
                DLLNode newNode = new DLLNode(key, value);

                // Get or initialize the list with frequency 1 and add the new node
                DoubleLinkedList curList = frequencyMap.getOrDefault(1, new DoubleLinkedList());
                curList.addNode(newNode);
                frequencyMap.put(1, curList); // Update the frequency map
                cache.put(key, newNode); // Add the new node to the cache
            }
        }

        /**
         * Updates a node's frequency by moving it to the appropriate frequency list.
         * This involves removing the node from its current frequency list and adding it to a new list
         * with a frequency one higher.
         */
        public void updateNode(DLLNode curNode) {
            int curFreq = curNode.frequency; // Get the current frequency of the node
            DoubleLinkedList curList = frequencyMap.get(curFreq); // Get the corresponding frequency list
            curList.removeNode(curNode); // Remove the node from the current frequency list

            // If the current list was the minimum frequency list and is now empty,
            // increment the minimum frequency
            if (curFreq == minFrequency && curList.listSize == 0) {
                minFrequency++;
            }

            curNode.frequency++; // Increment the node's frequency
            // Get or initialize the list for the new frequency
            DoubleLinkedList newList = frequencyMap.getOrDefault(curNode.frequency, new DoubleLinkedList());
            newList.addNode(curNode); // Add the node to the new frequency list
            frequencyMap.put(curNode.frequency, newList); // Update the frequency map
        }

        /*
         * DLLNode represents a node in a doubly linked list. Each node stores:
         * @param key: node key
         * @param val: node value
         * @param frequency: frequency count of current node (all nodes in the same doubly linked list have the same frequency)
         * @param prev: previous pointer of the current node
         * @param next: next pointer of the current node
         */
        class DLLNode {
            int key; // The key of the node
            int val; // The value of the node
            int frequency; // The frequency count of the node
            DLLNode prev; // Pointer to the previous node
            DLLNode next; // Pointer to the next node

            public DLLNode(int key, int val) {
                this.key = key; // Initialize the key
                this.val = val; // Initialize the value
                this.frequency = 1; // Set the initial frequency to 1
            }
        }

        /*
         * DoubleLinkedList represents a doubly linked list where each node has the same frequency.
         * @param listSize: current size of the doubly linked list
         * @param head: dummy head node of the list
         * @param tail: dummy tail node of the list
         */
        class DoubleLinkedList {
            int listSize; // Size of the doubly linked list
            DLLNode head; // Dummy head node
            DLLNode tail; // Dummy tail node

            public DoubleLinkedList() {
                this.listSize = 0; // Initialize list size to 0
                // Initialize dummy head and tail nodes
                this.head = new DLLNode(0, 0);
                this.tail = new DLLNode(0, 0);
                head.next = tail; // Point head's next to tail
                tail.prev = head; // Point tail's prev to head
            }

            /** Add a new node at the head of the list and increase the list size by 1 **/
            public void addNode(DLLNode curNode) {
                DLLNode nextNode = head.next; // Get the node after the head
                curNode.next = nextNode; // Point current node's next to the node after the head
                curNode.prev = head; // Point current node's prev to head
                head.next = curNode; // Make head's next point to the current node
                nextNode.prev = curNode; // Update the previous pointer of the next node
                listSize++; // Increment the list size
            }

            /** Remove the input node from the list and decrease the list size by 1 **/
            public void removeNode(DLLNode curNode) {
                DLLNode prevNode = curNode.prev; // Get the previous node
                DLLNode nextNode = curNode.next; // Get the next node
                prevNode.next = nextNode; // Link previous node to the next node
                nextNode.prev = prevNode; // Link next node back to the previous node
                listSize--; // Decrement the list size
            }
        }
    }

    // Main Function
    public static void main(String[] args) {
        // Create an LFU cache with a capacity of 2
        LFUCache lfuCache = new LFUCache(2);

        // Perform some put operations
        lfuCache.put(1, 1);  // Cache: [1:1]
        lfuCache.put(2, 2);  // Cache: [1:1, 2:2]

        // Perform a get operation
        System.out.println(lfuCache.get(1));  // Returns 1, Cache: [2:2, 1:1] (1's frequency increased)

        // Perform another put operation
        lfuCache.put(3, 3);  // Cache: [1:1, 3:3], 2 is evicted (least frequently used)

        // Perform more get operations
        System.out.println(lfuCache.get(2));  // Returns -1 (2 has been evicted)
        System.out.println(lfuCache.get(3));  // Returns 3, Cache: [1:1, 3:3]

        // Perform another put operation
        lfuCache.put(4, 4);  // Cache: [3:3, 4:4], 1 is evicted (least frequently used)

        // Perform more get operations
        System.out.println(lfuCache.get(1));  // Returns -1 (1 has been evicted)
        System.out.println(lfuCache.get(3));  // Returns 3, Cache: [4:4, 3:3]
        System.out.println(lfuCache.get(4));  // Returns 4, Cache: [3:3, 4:4]
    }
}

// Output :
/*
1
-1
3
-1
3
4
 */

// Algorithm :
/*
Input: capacity (max capacity of the LFU Cache).
Initialize Variables:
capacity: Stores the maximum capacity of the cache.
curSize: Tracks the current size of the cache (starts at 0).
minFrequency: Tracks the minimum frequency among all nodes in the cache (used for eviction).
cache: A hashmap that maps key → DLLNode (to store nodes).
frequencyMap: A hashmap that maps frequency → DoubleLinkedList (to store nodes based on their frequency).
Operations:
1. get(key) Operation:
Step 1: Check if the key exists in cache.
    i. If key does not exist, return -1.
Step 2: If the key exists:
    i. Retrieve the corresponding node (curNode) from cache.
   ii. Call updateNode(curNode) to update the node's frequency and position.
  iii. Return the value (curNode.val) of the node.

2. put(key, value) Operation:
Step 1: Check if the cache's capacity is 0.
    i. If yes, return without doing anything (invalid cache).
Step 2: Check if the key already exists in cache.
    i. If the key exists:
        i. Update the node's value (curNode.val = value).
       ii. Call updateNode(curNode) to update its frequency and position.
Step 3: If the key does not exist:
    i. Increment curSize by 1.
   ii. Check if cache is full (curSize > capacity):
        i. If yes, get the DoubleLinkedList for the minFrequency and remove the Least Recently
        Used (LRU) node from that list.
       ii. Remove the node from cache and decrement curSize.
  iii. Insert new node:
        i. Reset minFrequency to 1 (because we're adding a new node).
       ii. Create a new DLLNode with the provided key and value.
      iii. Add this new node to the frequency list with frequency 1 (frequencyMap[1]).
       iv. Add this new node to cache.

3. updateNode(DLLNode curNode) Operation:
Step 1: Retrieve the current frequency of curNode (curFreq).
Step 2: Remove the curNode from its current frequency list (frequencyMap[curFreq]).
Step 3: Check if this frequency list was the minimum frequency list and if it is now empty:
    I. If yes, increment the minFrequency.
Step 4: Increment curNode's frequency (curNode.frequency++).
Step 5: Add the curNode to the frequency list with the updated frequency (frequencyMap[curFreq + 1]).
Step 6: Update the frequencyMap to reflect the changes.

4. DoubleLinkedList Operations:
1. addNode(DLLNode curNode):
    i. Adds the node to the head of the doubly linked list and increments the list size.
2. removeNode(DLLNode curNode):
    i. Removes the node from the list and decrements the list size.

Algorithm Behavior:
1. Frequency Tracking: The algorithm uses frequency-based tracking to manage cache eviction.
The frequency of each node is updated each time it's accessed or modified.
2. Least Frequently Used Eviction: When the cache exceeds its capacity, the least frequently used
node (with the smallest frequency) is evicted. If multiple nodes have the same frequency, the least
recently used (LRU) node is evicted from that frequency list.
3. Min Frequency Maintenance: The minFrequency variable helps efficiently identify the frequency
list from which to evict nodes when the cache is full.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=0PSB9y8ehbk&list=PLgUwDviBIf0p4ozDR_kJJkONnb1wdx2Ma
// (Implementation) : https://www.youtube.com/watch?v=mzqHlAW7jeE
