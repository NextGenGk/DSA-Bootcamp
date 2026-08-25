# Maximum Sum Subarray with At Least K Elements

## Problem Statement

Given an integer array `arr[]` and an integer `k`, find the **maximum
sum** among all contiguous subarrays whose length is **greater than or
equal to `k`**.

### Example

``` text
Input:
arr = [3, 2, -5, 4, 6, -1]
k = 3

Output:
10
```

### Explanation

All valid subarrays (length ≥ 3):

``` text
Length 3
[3,2,-5]      = 0
[2,-5,4]      = 1
[-5,4,6]      = 5
[4,6,-1]      = 9

Length 4
[3,2,-5,4]    = 4
[2,-5,4,6]    = 7
[-5,4,6,-1]   = 4

Length 5
[3,2,-5,4,6]  = 10   <-- Maximum
[2,-5,4,6,-1] = 6

Length 6
[3,2,-5,4,6,-1] = 9
```

Hence the answer is **10** because `[3,2,-5,4,6]` has the maximum sum.

------------------------------------------------------------------------

# Approach 1 : Brute Force

## Intuition

Generate every possible subarray.

If its length is at least `k`, calculate its sum and update the answer.

## Algorithm

``` text
1. ans = -∞

2. For every starting index i
      sum = 0

      For every ending index j
            sum += arr[j]

            If length >= k
                  ans = max(ans, sum)

3. Return ans
```

## Java Code

``` java
class Solution {

    public int maxSumWithK(int[] arr, int k) {

        int n = arr.length;
        int ans = Integer.MIN_VALUE;

        // Try every starting index
        for (int i = 0; i < n; i++) {

            int sum = 0;

            // Extend the subarray
            for (int j = i; j < n; j++) {

                sum += arr[j];

                // Consider only subarrays of length >= k
                if (j - i + 1 >= k) {
                    ans = Math.max(ans, sum);
                }
            }
        }

        return ans;
    }
}
```

### Complexity

  Time    Space
  ------- -------
  O(n²)   O(1)

------------------------------------------------------------------------

# Why Brute Force is Slow

It checks **every possible subarray**, so for large arrays it performs
too many operations.

We need something faster.

------------------------------------------------------------------------

# Approach 2 : Optimal (Kadane + Sliding Window)

## Main Idea

Combine two techniques:

-   **Sliding Window** → Gives every window of exactly `k` elements.
-   **Kadane's Algorithm** → Gives the best subarray that can be
    attached before the current window.

------------------------------------------------------------------------

## Step 1 : Build `maxEndHere[]`

Meaning:

``` text
maxEndHere[i]
```

= **Maximum subarray sum ending at index i.**

Example:

``` text
arr = [3,2,-5,4,6,-1]

maxEndHere = [3,5,0,4,10,9]
```

Think of it as:

> **The best sum available before my current window.**

------------------------------------------------------------------------

## Step 2 : First Window

Take the first `k` elements.

``` text
Window = [3,2,-5]

sum = 0
ans = 0
```

------------------------------------------------------------------------

## Step 3 : Slide the Window

Every time the window moves, ask **two questions**.

### Question 1

Should I take only this window?

``` java
ans = Math.max(ans, sum);
```

### Question 2

Can I extend this window using the best subarray before it?

``` java
ans = Math.max(ans, sum + maxEndHere[i-k]);
```

------------------------------------------------------------------------

## Why `i-k`?

Suppose the current window is

``` text
3   2  -5   4   6  -1
        |-----------|
```

The element immediately before the window is at index `i-k`.

If we want to make the window longer, the attached subarray **must end
at `i-k`**.

That is exactly what `maxEndHere[i-k]` stores.

------------------------------------------------------------------------

# Dry Run

### Window 1

``` text
[3,2,-5]

Window Sum = 0

Answer = 0
```

------------------------------------------------------------------------

### Window 2

``` text
[2,-5,4]

Window Sum = 1
```

Option 1

``` text
Take only this window

Answer = 1
```

Option 2

``` text
Attach previous best

[3] + [2,-5,4]

Total = 4
```

Choose **4**.

------------------------------------------------------------------------

### Window 3

``` text
[-5,4,6]

Window Sum = 5
```

Option 1

``` text
5
```

Option 2

``` text
Attach previous best

[3,2] + [-5,4,6]

5 + 5 = 10
```

Choose **10**.

------------------------------------------------------------------------

# Optimal Algorithm

``` text
1. Build maxEndHere[] using Kadane.

2. Compute the first window sum of size k.

3. ans = first window sum.

4. Slide the window.

5. For every window

      Update window sum.

      Compare ans with current window sum.

      Compare ans with
      current window sum + maxEndHere[i-k].

6. Return ans.
```

------------------------------------------------------------------------

# Java Code (Optimal)

``` java
class Solution {

    public int maxSumWithK(int[] arr, int k) {

        int n = arr.length;

        // maxEndHere[i] = best subarray sum ending at i
        int[] maxEndHere = new int[n];
        maxEndHere[0] = arr[0];

        // Kadane
        for (int i = 1; i < n; i++) {
            maxEndHere[i] = Math.max(arr[i], arr[i] + maxEndHere[i - 1]);
        }

        // Sum of first window
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += arr[i];
        }

        int ans = sum;

        // Slide the window
        for (int i = k; i < n; i++) {

            // Remove left element and add new element
            sum += arr[i] - arr[i-k];

            // Option 1: Only current window
            ans = Math.max(ans, sum);

            // Option 2: Extend the window
            ans = Math.max(ans, sum + maxEndHere[i-k]);
        }

        return ans;
    }
}
```

------------------------------------------------------------------------

# Complexity Comparison

  Approach      Time    Space
  ------------- ------- -------
  Brute Force   O(n²)   O(1)
  Optimal       O(n)    O(n)

------------------------------------------------------------------------

# Common Mistakes

-   Forgetting to initialize the first window sum.
-   Using `i-k+1` instead of `i-k`.
-   Thinking Kadane gives the answer directly (it doesn't because
    subarray length must be at least `k`).
-   Forgetting to compare both:
    -   Current window
    -   Extended window

------------------------------------------------------------------------

# Interview Memory Trick

Whenever the window moves, ask only two questions:

✅ Should I keep only this window?

``` java
ans = Math.max(ans, sum);
```

✅ Should I attach the best subarray before this window?

``` java
ans = Math.max(ans, sum + maxEndHere[i-k]);
```

The larger one becomes the answer.

> **Sliding Window gives exactly `k` elements.**
>
> **Kadane gives the best part to attach on the left.**
>
> **Together they solve the problem in O(n).**