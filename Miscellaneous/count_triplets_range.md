# Count Triplets With Sum in a Range

## Problem

Given an array `arr[]` and a range from `l` to `r`, count the number of triplets whose sum lies in the range `[l, r]`.

A triplet means choosing **any 3 different elements** from the array. The elements do **not** need to be adjacent.

### Example

```text
arr = [5, 1, 4, 3, 2]
l = 2
r = 7
```

Valid triplets include:

```text
[1, 2, 3] → 6
[1, 2, 4] → 7
```

So the answer is:

```text
2
```

---

## Important Observation

A simple 3-loop solution checks every possible triplet:

```text
O(n³)
```

We can do better using:

1. Sorting
2. Two pointers

This gives:

```text
O(n²)
```

---


# Three Approaches

There are three useful ways to solve this problem.

| Approach | Idea | Time | Space |
|---|---|---:|---:|
| Brute Force | Check every possible triplet | `O(n³)` | `O(1)` |
| Better | Sort + binary search for the valid third-element range | `O(n² log n)` | `O(1)`* |
| Optimal | Sort + two pointers | `O(n²)` | `O(1)`* |

> `*` Space depends on the sorting implementation. The algorithm itself uses `O(1)` extra space.

---

# 1. Brute Force

## Intuition

The simplest approach is to generate every possible triplet using three loops.

For every:

```text
i < j < k
```

calculate:

```text
arr[i] + arr[j] + arr[k]
```

If the sum is between `l` and `r`, increase the answer.

## Code

```java
class Solution {

    public int countTriplets(int[] arr, int l, int r) {

        int n = arr.length;
        int count = 0;

        // Pick the first element
        for (int i = 0; i < n - 2; i++) {

            // Pick the second element
            for (int j = i + 1; j < n - 1; j++) {

                // Pick the third element
                for (int k = j + 1; k < n; k++) {

                    int sum = arr[i] + arr[j] + arr[k];

                    if (sum >= l && sum <= r) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}
```

## Example

```text
arr = [5, 1, 4, 3, 2]
l = 2
r = 7
```

Some triplets checked are:

```text
5 + 1 + 4 = 10  ❌
5 + 1 + 3 = 9   ❌
5 + 1 + 2 = 8   ❌
1 + 4 + 3 = 8   ❌
1 + 4 + 2 = 7   ✅
1 + 3 + 2 = 6   ✅
```

Answer:

```text
2
```

## Complexity

```text
Time  = O(n³)
Space = O(1)
```

This is the easiest approach but can be slow for large arrays.

---

# 2. Better Approach — Sorting + Binary Search

## Intuition

We can reduce one loop.

First sort the array:

```text
[5, 1, 4, 3, 2]
```

becomes:

```text
[1, 2, 3, 4, 5]
```

Fix the first two elements:

```text
arr[i]
arr[j]
```

Then we need the third element `arr[k]` to satisfy:

```text
l <= arr[i] + arr[j] + arr[k] <= r
```

Rearrange:

```text
l - arr[i] - arr[j] <= arr[k] <= r - arr[i] - arr[j]
```

Because the array is sorted, we can use binary search to find the first and last valid `k`.

## Code

```java
import java.util.*;

class Solution {

    public int countTriplets(int[] arr, int l, int r) {

        Arrays.sort(arr);

        int n = arr.length;
        int count = 0;

        // Fix the first element
        for (int i = 0; i < n - 2; i++) {

            // Fix the second element
            for (int j = i + 1; j < n - 1; j++) {

                int minThird = l - arr[i] - arr[j];
                int maxThird = r - arr[i] - arr[j];

                // First index where arr[index] >= minThird
                int left = lowerBound(arr, j + 1, n, minThird);

                // First index where arr[index] > maxThird
                int right = upperBound(arr, j + 1, n, maxThird);

                // All indices [left, right) are valid
                count += right - left;
            }
        }

        return count;
    }

    // First index where arr[index] >= target
    int lowerBound(int[] arr, int start, int end, int target) {

        int left = start;
        int right = end;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (arr[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    // First index where arr[index] > target
    int upperBound(int[] arr, int start, int end, int target) {

        int left = start;
        int right = end;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (arr[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}
```

## Complexity

There are two loops:

```text
O(n²)
```

and each binary search takes:

```text
O(log n)
```

Therefore:

```text
Time = O(n² log n)
```

This is better than `O(n³)` but not as good as the two-pointer solution.

---

# 3. Optimal Approach — Sorting + Two Pointers

This is the recommended solution.

## Key Trick

Instead of directly counting triplets in `[l, r]`, calculate:

```text
count(sum <= r)
-
count(sum <= l - 1)
```

So:

```text
answer = count(arr, r) - count(arr, l - 1)
```

## Code

```java
import java.util.*;

class Solution {

    public int countTriplets(int[] arr, int l, int r) {

        // Sort the array
        Arrays.sort(arr);

        // Count:
        // sum <= r
        // minus
        // sum <= l - 1
        return count(arr, r) - count(arr, l - 1);
    }

    // Count triplets having sum <= target
    int count(int[] arr, int target) {

        int n = arr.length;
        int ans = 0;

        // i is the first element.
        //
        // We need two more elements after i,
        // therefore i < n - 2.
        for (int i = 0; i < n - 2; i++) {

            int left = i + 1;
            int right = n - 1;

            while (left < right) {

                int sum = arr[i] + arr[left] + arr[right];

                if (sum <= target) {

                    /*
                     * Since the array is sorted,
                     * every element between left and right
                     * will also make a valid triplet.
                     *
                     * Example:
                     *
                     * [1, 2, 3, 4, 5]
                     *  i  L        R
                     *
                     * If:
                     * 1 + 2 + 5 <= target
                     *
                     * then:
                     * 1 + 2 + 3
                     * 1 + 2 + 4
                     * 1 + 2 + 5
                     *
                     * are all valid.
                     */
                    ans += right - left;

                    // Move left forward
                    left++;

                } else {

                    // Sum is too large.
                    // Reduce the largest element.
                    right--;
                }
            }
        }

        return ans;
    }
}
```

## Dry Run

```text
arr = [5, 1, 4, 3, 2]
l = 2
r = 7
```

Sort:

```text
[1, 2, 3, 4, 5]
```

Calculate:

```text
count(sum <= 7) - count(sum <= 1)
```

### `count(sum <= 7)`

Start:

```text
i = 0 → 1
left = 1 → 2
right = 4 → 5
```

Sum:

```text
1 + 2 + 5 = 8
```

Too large:

```text
right--
```

Now:

```text
1 + 2 + 4 = 7
```

Valid.

Since the array is sorted:

```text
1 + 2 + 3 = 6
1 + 2 + 4 = 7
```

are both valid.

Therefore:

```text
ans += right - left
     = 3 - 1
     = 2
```

So:

```text
count(sum <= 7) = 2
```

### `count(sum <= 1)`

The smallest possible triplet is:

```text
1 + 2 + 3 = 6
```

Therefore:

```text
count(sum <= 1) = 0
```

Final:

```text
2 - 0 = 2
```

---

# Why Is the Optimal Approach Faster?

### Brute Force

Checks every:

```text
i, j, k
```

Combination.

```text
O(n³)
```

### Better

Fixes:

```text
i, j
```

and uses binary search for `k`.

```text
O(n² log n)
```

### Optimal

Fixes:

```text
i
```

and uses two pointers for the other two elements.

```text
O(n²)
```

The important advantage is that `left` and `right` move without repeatedly searching the same range.

---

# Final Comparison

```text
Brute:
Three loops
O(n³)
Easy to understand

Better:
Sort + two loops + binary search
O(n² log n)
Good intermediate solution

Optimal:
Sort + two pointers
O(n²)
Best choice
```

For interviews and coding platforms, learn the progression:

```text
Brute Force
    ↓
Remove one loop
    ↓
Binary Search
    ↓
Two Pointers
```

The most important pattern to remember is:

```text
Count range [l, r]

= Count(<= r)
  - Count(<= l - 1)
```


# Intuition

It is easier to count:

```text
Number of triplets with sum <= x
```

Then we can get the answer for the range `[l, r]` using:

```text
count(sum <= r) - count(sum <= l - 1)
```

### Why?

Suppose:

```text
l = 2
r = 7
```

We want:

```text
2 <= sum <= 7
```

If we count all triplets with:

```text
sum <= 7
```

we also include sums:

```text
..., -1, 0, 1
```

So we remove all triplets with:

```text
sum <= 1
```

Therefore:

```text
answer = count(sum <= 7) - count(sum <= 1)
```

In general:

```text
answer = count(sum <= r) - count(sum <= l - 1)
```

---

# Algorithm

## Step 1: Sort the array

For example:

```text
[5, 1, 4, 3, 2]
```

becomes:

```text
[1, 2, 3, 4, 5]
```

Sorting allows us to use two pointers.

---

## Step 2: Create a function to count sums <= target

We fix the first element using `i`.

Then use:

```text
left = i + 1
right = n - 1
```

Now calculate:

```text
sum = arr[i] + arr[left] + arr[right]
```

### Case 1: sum <= target

Because the array is sorted, if:

```text
arr[i] + arr[left] + arr[right] <= target
```

then every element between `left` and `right` also works.

So the number of valid triplets is:

```text
right - left
```

Then move:

```text
left++
```

### Case 2: sum > target

The sum is too large.

Since the array is sorted, decrease the largest value:

```text
right--
```

---

# Code

```java
import java.util.*;

class Solution {

    public int countTriplets(int[] arr, int l, int r) {

        // Sort the array so that we can use two pointers
        Arrays.sort(arr);

        // Number of triplets with sum <= r
        // minus
        // Number of triplets with sum <= l - 1
        return count(arr, r) - count(arr, l - 1);
    }

    // Counts the number of triplets having sum <= target
    int count(int[] arr, int target) {

        int n = arr.length;
        int ans = 0;

        // i is the first element of the triplet.
        //
        // We stop at n - 3 because we need
        // two more elements after i.
        //
        // Example for n = 5:
        // i = 0, 1, 2
        for (int i = 0; i < n - 2; i++) {

            // Second element
            int left = i + 1;

            // Third element
            int right = n - 1;

            while (left < right) {

                int sum = arr[i] + arr[left] + arr[right];

                if (sum <= target) {

                    /*
                     * Since the array is sorted, if
                     *
                     * arr[i] + arr[left] + arr[right] <= target
                     *
                     * then all elements between left and right
                     * will also produce a valid sum.
                     *
                     * Number of such triplets = right - left
                     */
                    ans += right - left;

                    // Try the next left element
                    left++;

                } else {

                    // Sum is too large,
                    // so reduce the largest element.
                    right--;
                }
            }
        }

        return ans;
    }
}
```

---

# Dry Run

Given:

```text
arr = [5, 1, 4, 3, 2]
l = 2
r = 7
```

After sorting:

```text
[1, 2, 3, 4, 5]
```

We calculate:

```text
count(sum <= 7) - count(sum <= 1)
```

## count(sum <= 7)

Start with:

```text
i = 0
left = 1
right = 4
```

Values:

```text
1, 2, 5
```

Sum:

```text
1 + 2 + 5 = 8
```

`8 > 7`, so:

```text
right--
```

Now:

```text
i = 0
left = 1
right = 3
```

Values:

```text
1, 2, 4
```

Sum:

```text
1 + 2 + 4 = 7
```

Valid.

Because the array is sorted, these are also valid:

```text
[1, 2, 3]
[1, 2, 4]
```

So:

```text
ans += right - left
     = 3 - 1
     = 2
```

Therefore:

```text
count(sum <= 7) = 2
```

---

## count(sum <= 1)

The smallest possible triplet is:

```text
1 + 2 + 3 = 6
```

So there cannot be any triplet with sum `<= 1`.

Therefore:

```text
count(sum <= 1) = 0
```

---

## Final Answer

```text
count(sum <= 7) - count(sum <= 1)

= 2 - 0

= 2
```

The two valid triplets are:

```text
[1, 2, 3] → 6
[1, 2, 4] → 7
```

Therefore:

```text
Answer = 2
```

---

# Why `i < n - 2`?

This is important.

We need 3 elements:

```text
i
left
right
```

So `i` must have at least **2 elements after it**.

For:

```text
arr = [1, 2, 3, 4, 5]
n = 5
```

The possible values are:

```text
i = 0
i = 1
i = 2
```

At `i = 2`:

```text
[1, 2, 3, 4, 5]
       ↑  ↑  ↑
       i left right
```

There are exactly two elements after `i`.

But at `i = 3`:

```text
[1, 2, 3, 4, 5]
          ↑  ↑
          i  only one element
```

We cannot make a triplet.

Therefore:

```java
for (int i = 0; i < n - 2; i++)
```

is correct.

It is equivalent to:

```text
i <= n - 3
```

---

# Complexity

### Sorting

```text
O(n log n)
```

### Two-pointer traversal

For every `i`, `left` and `right` move only across the array:

```text
O(n²)
```

### Total

```text
O(n²)
```

Space complexity:

```text
O(1)
```

ignoring the space used internally by the sorting implementation.

---

# Key Things to Remember

### 1. Triplets do not need to be adjacent

For:

```text
[5, 1, 4, 3, 2]
```

`[1, 4, 2]` is a valid triplet.

### 2. Sort first

Sorting makes the two-pointer technique possible.

### 3. Convert a range into two counts

```text
[l, r]

= count(<= r) - count(<= l - 1)
```

### 4. When sum is small enough

```java
ans += right - left;
left++;
```

### 5. When sum is too large

```java
right--;
```

### 6. Why `i < n - 2`

Because `i` needs two elements after it to form a triplet.
