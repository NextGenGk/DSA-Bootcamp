package BitManipulation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Single_Number_II {

    // Method 1 : Brute Force
    // Time Complexity: O(N x log2M + M) where N is the number of elements in the
    // input array and M = (N/3 + 1).
    // 1. Inserting into the hashmap with the frequency of each element in the input array
    // requires iterating through the entire array once ie. O(N). At each element, we search
    // if it is already present in the map or not.
    // 2. In the worst case scenario, this adds a complexity of O(log2M) for each insertion.
    // Here M is the maximum size of the map, which can be N//3 + 1 at max as all elements are
    // present thrice except one.
    // 3. After populating the hashmap, we iterate over it to find the element with a frequency
    // of 1. This operation's time complexity is O(M), where M = N/3 + 1 represents the number of unique
    // elements.
    // Space Complexity: O(M)where M = N/3 + 1, representing the number of unique elements
    // stored in the hashmap. This is because each element in the input array appears thrice
    // except one, therefore after the last scenarios the space complexity taken up is N/3 + 1.
    public static int findSingleNumber(int[] nums) {
        // Initialise a hashmap to store the
        // element as key and frequency as value
        Map<Integer, Integer> mpp = new HashMap<>();

        // Populate the hashmap with
        // the frequency of each element
        for (int i = 0; i < nums.length; i++) {
            mpp.put(nums[i], mpp.getOrDefault(nums[i], 0) + 1);
        }

        // Iterate through the hashmap to
        // find the element with frequency 1
        for (Map.Entry<Integer, Integer> entry : mpp.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }

        // If no element with frequency 1 is
        // found, return -1 as input invalid
        return -1;
    }

    // Method 2 : Better Solution
    // Time Complexity: O(32*N) where N is the number of elements in the input array.
    // This is because we iterate through each of the 32 bits of all N numbers in the input array.
    // Space Complexity: O(1) as we are only using a constant amount of extra space regardless
    // of the size of the input array.
    public static int findSingleNumber1(int[] nums) {
        int ans = 0;

        // Iterate through each bit
        // position (from 0 to 31)
        for (int bitIndex = 0; bitIndex < 32; bitIndex++) {
            // Counter to count the number of
            // set bits at the current bit position
            int cnt = 0;

            // Iterate through each
            // number in the input array
            for (int i = 0; i < nums.length; i++) {
                // Check if the bit at bitIndex is
                // set in the current number nums.get(i)
                if ((nums[i] & (1 << bitIndex)) != 0) {
                    // Increment the counter
                    // if the bit is set
                    cnt++;
                }
            }

            // If the count of set bits at the current bit
            // position is not divisible by 3 (i.e., cnt % 3 == 1)
            // Set the corresponding bit in the answer variable ans
            if (cnt % 3 == 1) {
                ans |= (1 << bitIndex);
            }
        }

        // Return the decimal
        // representation of the answer
        return ans;
    }

    // Method 3 : Better Solution
    // Time Complexity: O(N log2N) where N is the number of elements in the input array.
    // Sorting the array has a time complexity of log2N and traversing the sorted array to
    // find the unique element takes linear time.
    // Space Complexity: O(1) as we are only using a constant amount of extra space regardless
    // of the size of the input array. The sorting operation is performed in-place, and no
    // additional space is used beyond the input array.
    public static int findSingleNumber2(int[] nums) {
        // Sort the input array
        Arrays.sort(nums);

        // Traverse the sorted array and check
        // middle elements of groups of three
        for (int i = 1; i < nums.length; i += 3) {
            // If the current element is not equal to
            // the previous one, it's the unique element
            if (nums[i] != nums[i - 1]) {
                return nums[i - 1];
            }
        }

        // If the unique element is not found in the
        // loop then it has to be the last element
        return nums[nums.length - 1];
    }

    // Method 4 : Optimal Solution
    // Time Complexity: O(N) where N is the number of elements in the input array.
    // This is because we iterate through each element of the input array once.
    // Space Complexity: O(1) as we are only using a constant amount of extra space regardless
    // of the size of the input array.
    public static int findSingleNumber3(int[] nums) {
        // 'ones' stores the bits of numbers that have appeared once
        int ones = 0;
        // 'twos' stores the bits of numbers that have appeared twice
        int twos = 0;

        // Iterate through each number in the array
        for (final int num : nums) {
            // Update 'ones' by XORing with 'num' only if it's not already in 'twos'
            ones ^= (num & ~twos);

            // Update 'twos' by XORing with 'num' only if it's not already in 'ones'
            twos ^= (num & ~ones);
        }

        // At the end, 'ones' contains the unique number that appears exactly once
        return ones;
    }

    // Main Function
    public static void main(String[] args) {
        int[] nums = {4, 1, 2, 1, 2};
        System.out.print("Initial array: ");
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
        System.out.println("Element that appears only once: " + findSingleNumber3(nums));
    }
}

// Output :
/*
Initial array: 4 1 2 1 2
Element that appears only once: 4
 */

// Intuition : Brute Force
/*
Algorithm / Intuition
A straightforward approach involves using a hashmap to store the frequency of each
element in the input array. After traversing the input array and storing the frequency
of each element in the hashmap, we iterate through it to find the element with a frequency
of 1. Return this element as it appears only once in the input array.

Algorithm:
Step 1: Initialise an empty hashmap to store the frequency of each element.
Step 2: Populate the hashmap by iterating through the input array.
For each element in the array, if it is already present in the hashmap, increment its frequency.
Otherwise, add it to the hashmap with a frequency of 1.
Step 3: Iterate through the hashmap to find the element with a frequency of 1.
For each key-value pair in the hashmap if the value (frequency) is 1, return the corresponding
key (element) as it appears only once in the input array.
 */

// Intuition : Better Solution
/*
The previous hashmap based approach requires a lot of space complexity to store the
frequencies. We also iterate over the input array and the frequency map leading to
high time complexity as well. To overcome this, we can use bitwise operations to solve
this problem.

We iterate through each bit position of the binary representation of the numbers in the
input array. For each bit position, we calculate the sum of all numbers in the input array
that have a set bit at that position and check if this sum is divisible by 3.

If the sum is divisible by 3, it means that the number that appears only once on that
bit position has a bit value of 0. Otherwise, it has a bit value of 1. This is because
if a number appears thrice in the input array, the sum of its bits at any position will
be divisible by 3.

Algorithm:
Step 1: Initialise a variable `ans` to store the result.
Step 2: Iterate through each bit position from 0 to 31. Inside the loop:
Initialise a counter `cnt` to count the number of set bits at the current bit position.
Step 3: Inside the loop, iterate through each element in the input array. Inside this nested loop:
Check if the bit at the current position is set in the current element using bitwise AND with a mask
for that current bit position from 0 to 31 ie. nums[i] & 1 << bitIndex.
If the bit is set, increment the counter `cnt`.
Step 4: After iterating through all elements, check if the count of set bits at the current bit position
is not divisible by 3. If the condition is true, set the corresponding bit in the answer variable
`ans` using the bitwise OR operation ie. ans |= 1 << bitIndex.
Step 5: After iterating through all bit positions, return the decimal representation of the answer
stored in ans.
 */

// Intuition : Better Solution
/*
The previous approach uses nested loops to iterate over each bit of every element of the
input array and can be optimised. Every element in the input array except one element
is present three times.

By sorting the array, the identical elements are grouped together. We iterate through the
sorted array, skipping elements in groups of three. For each iteration we compare the current
 element with its preceding element.

If the current element is different from its preceding element, it means that the preceding
element appears only once in the array as all other elements appear three times. Therefore,
we return the preceding element as the unique element.

Algorithm:
Step 1:Sort the input array using a sorting algorithm.
Step 2: Traverse through the sorted array, starting from the second element (index 1).
Since the array has been sorted, there will be consecutive triplets of each unique number
except one single number out of order.
Increment the loop variable by 3 in each iteration to skip elements in groups of three.
Step 3: In each iteration, compare the current element with its preceding element. If the
current element is not equal to its preceding element, return the preceding element as its
unique element.
Step 4: If the loop ends without returning the out of place element. Return the end element
as that is the only unchecked element. Return it.
 */

// Intuition : Optimal Solution (Concept of Buckets)
/*
1. Initialize two variables, ones and twos, to keep track of the count of each bit position.
    1. ones: Tracks the bits that have appeared once.
    2. twos: Tracks the bits that have appeared twice.

2. Iterate through the array of numbers.
    1. For each number i in the array:
        1. Update ones and twos:
        2. Let's analyze each step of the update process:
            a. ones = (ones ^ i) & (~twos);:
                1. ones ^ i XORs the current number i with the previous value of ones. This operation
                   toggles the bits that have appeared an odd number of times, keeping the bits that have
                   appeared twice unchanged.
                2. (~twos) negates the bits in twos, effectively removing the bits that have appeared
                   twice from consideration.
                3. The & operation ensures that only the bits that have appeared once (after XOR) and
                   not twice (after negating twos) are retained.
            b. twos = (twos ^ i) & (~ones);:
                1. twos ^ i XORs the current number i with the previous value of twos. This operation
                   toggles the bits that have appeared an even number of times, effectively removing the
                   bits that have appeared twice.
                2. (~ones) negates the bits in ones, effectively removing the bits that have appeared
                   once from consideration.
                3. The & operation ensures that only the bits that have appeared twice (after XOR) and
                   not once (after negating ones) are retained.
3. After iterating through all the numbers, the value stored in ones will represent the single number
that appears only once in the array.

Let's understand why this approach works:

1. The key idea is to use bitwise operations to keep track of the count of each bit position.
 By doing so, we can identify the bits that have appeared once, twice, or three times.
2. When a bit appears for the first time (ones is 0 and the bit is toggled), it is stored in ones.
3. When a bit appears for the second time (ones is 1 and the bit is toggled), it is removed from
 ones and stored in twos.
4. When a bit appears for the third time (ones is 0 and the bit is toggled), it is removed from
both ones and twos.
5. By the end of the iteration, the bits that remain in ones represent the bits of the single
number that appeared only once, while the bits in twos represent bits that appeared three times
(which is not possible).

In summary, the algorithm uses bit manipulation to efficiently keep track of the counts of each
bit position. By utilizing XOR and AND operations, it can identify the bits of the single number
that appears only once in the array while ignoring the bits that appear multiple times.
 */

// Note : https://leetcode.com/problems/single-number-ii/solutions/3714928/bit-manipulation-c-java-python-beginner-friendly/

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=5Bb2nqA40JY