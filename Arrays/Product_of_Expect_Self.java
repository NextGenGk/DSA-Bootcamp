package Arrays;

public class Product_of_Expect_Self {

    // Method 1 : Brute Force
    // Time Complexity : O(N^2)
    // Space Complexity : O(N)
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length; // Get the size of the input array
        int[] result = new int[n]; // Initialize an array to store the result

        // Iterate over each element in the array
        for (int i = 0; i < n; i++) {
            int product = 1; // Initialize the product for the current index

            // Calculate the product of all elements except the one at index 'i'
            for (int j = 0; j < n; j++) {
                if (j != i) { // Skip the current index
                    product *= nums[j]; // Multiply the other elements
                }
            }

            result[i] = product; // Store the computed product in the result array
        }

        return result; // Return the result array
    }

    // Method 2 : Better Solution
    // Time Complexity : O(N)
    // Space Complexity : O(N)
    public int[] productExceptSelf1(int[] nums) {
        int n = nums.length;  // Get the length of the input array
        int[] prefix = new int[n];  // Array to store the prefix product
        int[] suffix = new int[n];  // Array to store the suffix product
        int[] ans = new int[n];  // Array to store the final result

        // Initialize prefix and suffix arrays to handle boundary cases
        prefix[0] = 1;  // The product of all elements before the first element is 1
        suffix[n - 1] = 1;  // The product of all elements after the last element is 1

        // Build the prefix product array
        for (int i = 1; i < n; i++) {
            // Multiply current prefix value by
            // the previous element in nums
            prefix[i] = prefix[i - 1] * nums[i - 1];
        }

        // Build the suffix product array (this step starts from the end of the array)
        for (int i = n - 2; i >= 0; i--) {
            // Multiply current suffix value by the
            // next element in nums
            suffix[i] = suffix[i + 1] * nums[i + 1];
        }

        // Build the final answer array by multiplying the prefix and suffix for each element
        for (int i = 0; i < n; i++) {
            ans[i] = prefix[i] * suffix[i];  // The product of all elements except the current element
        }

        return ans;  // Return the final result array
    }

    // Method 3 : Optimal Solution
    // Time Complexity : O(N)
    // Space Complexity : O(1)
    public int[] productExceptSelf2(int[] nums) {
        int n = nums.length;  // Get the length of the input array
        int[] ans = new int[n];  // Array to store the final result

        // Initialize the first element of the answer array as 1, since there are
        // no elements before the first element
        ans[0] = 1;

        // First loop: Build the prefix product array (stored in ans)
        for (int i = 1; i < n; i++) {
            ans[i] = ans[i - 1] * nums[i - 1];  // Multiply the previous result by the element before the current element
        }

        int suffix = 1;  // Initialize the suffix product (starting from the end of the array)

        // Second loop: Build the suffix product array and multiply it with the existing values in ans
        for (int i = n - 2; i >= 0; i--) {
            suffix *= nums[i + 1];  // Multiply suffix by the element after the current element
            ans[i] *= suffix;  // Multiply the current value of ans[i] by the current suffix value
        }

        return ans;  // Return the final answer array
    }

    // Main Function
    public static void main(String[] args) {
        Product_of_Expect_Self obj = new Product_of_Expect_Self();
        int[] nums = {1, 2, 3, 4};
        int[] result = obj.productExceptSelf(nums);
        System.out.print("The product of all elements except self is : ");
        for (int i : result) {
            System.out.print(i + " ");
        }
    }
}

// Output :
// The product of all elements except self is : 24 12 8 6

// Algorithm : Brute Force
/*
Intuition :
The idea is straightforward: For each element in the array, compute the product of all the other
elements, which is done by iterating over the entire array and skipping the current element.

Approach :
1. Initialize an array to store the result.
2. Iterate over each element in the array.
3. For each element, iterate over the entire array and calculate the product of all
elements except the current one.
4. Store the computed product in the result array.
5. Return the result array.
 */

// Algorithm : Better Solution
/*
Intuition :
The goal is to compute an array ans[] where each element at index i contains the product of
all elements in the input array nums[] except the element at index i. This should be done
without using division and in O(n) time.

We can achieve this by using two auxiliary arrays:
1. prefix[]: This will store the product of all elements before index i.
2. suffix[]: This will store the product of all elements after index i.

The final product for each index will be the product of the values in prefix[i] and suffix[i].

Approach :
1. Initialize three arrays: prefix[], suffix[], and ans[].
2. Initialize the prefix and suffix arrays to handle boundary cases.
3. Build the prefix product array by multiplying the current prefix value by the previous element in nums.
4. Build the suffix product array by multiplying the current suffix value by the next element in nums.
5. Build the final answer array by multiplying the prefix and suffix for each element.
6. Return the final result array.
 */

// Algorithm : Optimal Solution
/*
Intuition :
Objective:
We need to compute an array ans[] where each element at index i contains the product of
all elements in the input array nums[], except the element at index i.

How to Achieve This:
1. Instead of using two separate arrays for the prefix and suffix products, we can combine
the steps into a more space-efficient solution, which requires only a single ans[] array.
2. We break the solution into two passes through the array:
    i. Prefix Product Pass: This pass calculates the product of all elements before each index i.
   ii. Suffix Product Pass: This pass calculates the product of all elements after each
   index i and combines it with the result from the first pass to get the final product for each index.

Approach :
1. Initialize the answer array ans[] with the first element as 1, since there are no elements
   before the first element.
2. First Loop: Build the prefix product array (stored in ans) by multiplying the previous result
   by the element before the current element.
3. Initialize the suffix product as 1.
4. Second Loop: Build the suffix product array and multiply it with the existing values in ans.
5. Return the final answer array.
 */

// Sharadha Ma'am (Video Explanation) : https://www.youtube.com/watch?v=TW2m8m_FNJE