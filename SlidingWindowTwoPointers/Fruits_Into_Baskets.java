package SlidingWindowTwoPointers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Fruits_Into_Baskets {

    // Method 1 : Brute Force
    // Time Complexity : O(N^2)
    // Space Complexity : O(3), because set contains at most 3 elements
    public static int totalFruit(int[] fruits) {
        // Get the length of the input array
        int n = fruits.length;
        // Initialize the maximum length of subarray with at most 2 types of fruits
        int maxLength = 0;

        // Outer loop to fix the starting point of the subarray
        for (int i = 0; i < n; i++) {
            // Initialize a set to track the types of fruits in the current subarray
            Set<Integer> set = new HashSet<Integer>();

            // Inner loop to extend the subarray from the current starting point
            for (int j = i; j < n; j++) {
                set.add(fruits[j]);     // Add the current fruit to the set

                // If the set contains 2 or fewer types of fruits, update the maxLength
                if (set.size() <= 2) {
                    // Calculate the length of the current subarray
                    maxLength = Math.max(maxLength, j - i + 1);
                }
                else {
                    break;              // If more than 2 types of fruits are found, break out of the loop
                }
            }
        }
        return maxLength;               // Return the maximum length found
    }

    // Method 2 : Better Solution
    // Time Complexity : O(2N)
    // Space Complexity : O(3), because map stores at most 3 elements
    public static int totalFruit1(int[] fruits) {
        // Get the length of the input array
        int n = fruits.length;
        // Initialize the left pointer of the sliding window
        int left = 0;
        // Initialize the right pointer of the sliding window
        int right = 0;
        // Variable to store the maximum length of a valid subarray
        int maxLength = 0;
        // Map to count the frequency of each type of fruit in the current window
        Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();

        // Iterate through the array using the right pointer
        while (right < n) {
            // Add the current fruit to the countMap and increment its count
            countMap.put(fruits[right], countMap.getOrDefault(fruits[right], 0) + 1);

            // If the map size exceeds 2, we need to shrink the window from the left
            while (countMap.size() > 2) {
                // Decrease the count of the fruit at the left pointer
                countMap.put(fruits[left], countMap.get(fruits[left]) - 1);

                // If the count becomes 0, remove the fruit from the map
                if (countMap.get(fruits[left]) == 0) {
                    countMap.remove(fruits[left]);
                }
                left++;  // Move the left pointer to the right to shrink the window
            }

            // Update maxLength if the current window contains at most 2 types of fruits
            if (countMap.size() <= 2) {
                maxLength = Math.max(maxLength, right - left + 1);
            }

            // Move the right pointe r to expand the window
            right++;
        }

        // Return the maximum length of a subarray with at most 2 types of fruits
        return maxLength;  
    }

    // Method 3 : Optimal Solution
    // Time Complexity : O(N)
    // Space Complexity : O(3)
    public static int totalFruit2(int[] fruits) {
        // Get the length of the input array
        int n = fruits.length;
        // Initialize the left pointer of the sliding window
        int left = 0;
        // Initialize the right pointer of the sliding window
        int right = 0;
        // Variable to store the maximum length of a valid subarray
        int maxLength = 0;
        // Map to count the frequency of each type of fruit in the current window
        Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();

        // Iterate through the array using the right pointer
        while (right < n) {
            // Add the current fruit to the countMap and increment its count
            countMap.put(fruits[right], countMap.getOrDefault(fruits[right], 0) + 1);

            // If the map size exceeds 2, it means we have more than two types of fruits in the window
            if (countMap.size() > 2) {
                // Decrease the count of the fruit at the left pointer
                countMap.put(fruits[left], countMap.get(fruits[left]) - 1);

                // If the count becomes 0, remove the fruit from the map
                if (countMap.get(fruits[left]) == 0) {
                    countMap.remove(fruits[left]);
                }
                left++;  // Move the left pointer to the right to shrink the window
            }

            // Update maxLength if the current window contains at most 2 types of fruits
            if (countMap.size() <= 2) {
                maxLength = Math.max(maxLength, right - left + 1);
            }

            // Move the right pointer to expand the window
            right++;
        }

        return maxLength;  // Return the maximum length of a subarray with at most 2 types of fruits
    }

    // Main Function
    public static void main(String[] args) {
        int[] fruits = {3, 3, 3, 1, 2, 1, 1, 1, 2, 3, 3, 4};
        int result = totalFruit2(fruits);
        System.out.println(result);
    }
}

// Output : 6

// Approach : Brute Force
/*
The intuition behind this code is to explore every possible subarray within the array, starting from each
index, and check how long the subarray can be while containing at most two distinct types of fruits.
The outer loop sets the starting point for the subarray, while the inner loop extends the subarray
by adding fruits to a set. If the set's size exceeds two, it means the subarray has more than two
types of fruits, so the inner loop breaks. The code keeps track of the maximum length of valid subarrays
found during this process. This approach, though straightforward, is less efficient because it examines
each subarray independently, leading to a time complexity of O(N^2)
 */

// Approach : Better Solution
/*
The intuition behind this code is to use a sliding window approach to find the longest subarray containing
at most two distinct types of fruits. As the right pointer expands the window by adding fruits, the
left pointer adjusts to shrink the window whenever more than two fruit types are present. This ensures
that the window always remains valid (with at most two types), while continuously updating the maximum
length of such subarrays. By efficiently managing the window with a frequency map, the code achieves
an optimal solution with a linear time complexity.

 */

// Approach : Optimal Solution
/*
The intuition behind this code is to efficiently find the longest subarray that contains at most two
distinct types of fruits using a sliding window approach. As the right pointer expands the window by
adding fruits, a frequency map (countMap) tracks how many of each type are in the window. If the window
exceeds two types of fruits, the left pointer moves rightward to shrink the window by removing fruits
until only two types remain. This dynamic adjustment ensures that the window always remains valid,
and the code continuously updates the maximum length of such valid subarrays. By managing the window and
its contents efficiently, the algorithm achieves an optimal solution with a linear time complexity of O(N).
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=e3bs0uA1NhQ
