package BitManipulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Single_Number_III {

    // Method 1 : Brute Force

    // Time Complexity:O(N x log2M + M) where N is the number of elements in the input
    // array and M = (N/2 + 2).
    // 1. Inserting into the hashmap with the frequency of each element in the input array
    // requires iterating through the entire array once ie. O(N). At each element, we search
    // if it is already present in the map or not.
    // 2. In the worst case scenario, this adds a complexity of O(log2M) for each insertion.
    // Here M is the maximum size of the map, which can be N/2 + 2 at max as all elements are
    // present twice except one.
    // 3. After populating the hashmap, we iterate over it to find the element with a
    // frequency of 1. This operation's time complexity is O(M), where M = N/2 + 2 represents
    // the number of unique elements.

    // Space Complexity : O(M) where M = N/2 + 1, representing the number of unique elements
    // stored in the hashmap. This is because each element in the input array appears twice
    // except one, therefore after the last scenarios the space complexity taken up is N/2 + 2.

    // Function to find single
    // numbers in the given array
    public static ArrayList<Integer> findSingleNumber(int[] nums) {
        // Initialise a hashmap to store
        // the element as key and frequency as value
        HashMap<Integer, Integer> mpp = new HashMap<>();

        // Populate the hashmap with
        // the frequency of each element
        for (int i = 0; i < nums.length; i++) {
            if (mpp.containsKey(nums[i])) {
                mpp.put(nums[i], mpp.get(nums[i]) + 1);
            } else {
                mpp.put(nums[i], 1);
            }
        }

        // Initialize an array list to
        // store single numbers
        ArrayList<Integer> ans = new ArrayList<>();
        // Iterate through the hashmap
        for (int key : mpp.keySet()) {
            // If the frequency of the element
            // is 1, it is a single number
            if (mpp.get(key) == 1) {
                // Add the single number
                // to the result list
                ans.add(key);
            }
        }

        // Return the list
        // containing single numbers
        return ans;
    }

    // Method 2 : Optimal Solution (Concepts of Buckets)

    // Time Complexity: O(2N) where N is the number of elements in the input array. This
    // is because we iterate through the array twice: once to compute the XOR of all elements
    // and once to place each number in its corresponding bucket.

    // Space Complexity : O(1) as we are only using a constant amount of extra space regardless
    // of the size of the input array. The algorithm uses only a few extra variables to store
    // intermediate results and these variables do not depend on the size of the input array.

    // Function to find single
    // numbers in the given array
    public static List<Integer> singleNumber(int[] nums) {
        // Initialize a variable to store
        // the XOR of all elements in nums
        int xorr = 0;

        // Loop through all elements in nums
        for (int i = 0; i < nums.length; i++) {
            // XOR the current element with xorr
            xorr = xorr ^ nums[i];
        }

        // Declare a variable to store
        // the rightmost bit set in xorr
        int rightmost;
        // Check if xorr is equal to
        // Integer.MIN_VALUE (minimum value of int)
        if (xorr == Integer.MIN_VALUE) {
            // If xorr is Integer.MIN_VALUE,
            // set rightmost to Integer.MIN_VALUE
            rightmost = Integer.MIN_VALUE;
        } else {
            // Compute the rightmost bit set in xorr
            rightmost = (xorr & (xorr - 1)) ^ xorr;
        }

        // Initialize buckets to store numbers that
        // are set at the corresponding bit or not
        int bucket1 = 0;
        int bucket2 = 0;

        // Loop through all elements in nums
        for (int i = 0; i < nums.length; i++) {
            // Check if the rightmost bit set
            // in nums[i] is also set in rightmost
            if ((nums[i] & rightmost) != 0) {
                // XOR nums[i] with bucket1
                bucket1 = nums[i] ^ bucket1;
            } else {
                // XOR nums[i] with bucket2
                bucket2 = nums[i] ^ bucket2;
            }
        }

        // Return the two unique numbers
        // found in bucket1 and bucket2
        List<Integer> result = new ArrayList<>();
        result.add(bucket1);
        result.add(bucket2);
        return result;
    }

    // Main Function
    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 3, 2, 5};
        System.out.print("Input Array: ");
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
        System.out.print("Unique Numbers: ");
        List<Integer> result = singleNumber(nums);
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}

// Output :
// [3, 5]

// Intuition : Brute Force
/*
Algorithm / Intuition
A straightforward approach involves using a hashmap to store the frequency of each element
in the input array. After traversing the input array and storing the frequency of each
element in the hashmap, we iterate through it to find the two elements with a frequency
of 1 and return them.

Algorithm:
Step 1:Initialise an empty hashmap to store the frequency of each element.
Step 2: Populate the hashmap by iterating through the input array. For each element in the
array, if it is already present in the hashmap, increment its frequency. Otherwise,
add it to the hashmap with a frequency of 1.
Step 3:Initialise an array ans to store the elements that appear once.
Step 4: Iterate through the hashmap to find the elements with a frequency of 1. For
each key-value pair in the hashmap if the value (frequency) is 1, add the corresponding
key to the ans array.
Step 5: Return the ans vector.
 */

// Intuition : Optimal Solution
/*
Algorithm :

Step 1: Take the XOR of all numbers in the array. Since XOR of two identical numbers is 0,
and XOR of a number with 0 is the number itself, the XOR operation will result in the XOR
of the two numbers that appear only once in the array.

Step 2: The XOR operation of all numbers in the array will yield a result with at least one bit set to 1.
    1. This is because if the two unique numbers were identical, their XOR would be 0.
    2. Conversely, if they were different, there would be at least one bit position where
       they differ, and the XOR operation of different bits results in 1.
    3. Hence, the XOR result will have at least one bit set to 1.

Step 3: Take the rightmost set bit of the XOR result.
    1. To isolate this bit, create a mask equal to num-1 and use the bitwise AND operation with (num & (num-1)).
    2. XOR this result with the original XOR result to isolate the rightmost bit.

Step 4: Using the rightmost bit set number, we can create two buckets based on whether the
corresponding bit in each element of the array is set or unset.
    1. Since the corresponding rightmost bit is set differently in the two unique single numbers,
       they will fall into separate buckets.
    2. Based on the check of whether the bit is set or not, we XOR the number into one of the two buckets.

Step 5:The two buckets are divided in such a way that both the buckets will only have one number
appearing once. By taking a XOR of all numbers appearing in a bucket, all numbers appearing twice
will cancel out each other and only the single unique number will remain.
 */

// Striver's (Optimal Solution) : https://www.youtube.com/watch?v=UA5JnV1J2sI&list=PLgUwDviBIf0rnqh8QsJaHyIX7KUiaPUv7