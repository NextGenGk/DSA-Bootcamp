# Subarrays with Sum in Range (Two Pointers)

# Problem

Given an array of **positive integers** `arr[]` and two integers `l` and `r`, count the number of subarrays whose sum lies in the range **[l, r]** (inclusive).

---

## Key Idea

Instead of directly counting subarrays whose sum is in **[l, r]**, we use this trick:

```java
answer = countAtMost(r) - countAtMost(l - 1);
```

### Why does this work?

Let

- **A** = all subarrays with **sum ≤ r**
- **B** = all subarrays with **sum ≤ l - 1**

Since every subarray in **B** is also in **A**:

```
B ⊆ A
```

Removing **B** from **A** leaves only subarrays whose sums satisfy:

```
l ≤ sum ≤ r
```

This is the same idea as:

```
Count(L ≤ x ≤ R)
=
Count(x ≤ R) - Count(x < L)
```

For integers,

```
Count(x < L) = Count(x ≤ L-1)
```

Hence,

```java
answer = countAtMost(r) - countAtMost(l - 1);
```

---

# How does countAtMost(k) work?

We maintain a sliding window whose sum is always **≤ k**.

Whenever the sum becomes greater than **k**, move the left pointer until the window becomes valid again.

For every position `right`, all subarrays ending at `right` and starting anywhere from `left` to `right` are valid.

Therefore,

```java
count += right - left + 1;
```

---

# Example

### Input

```
arr = [2, 3, 5, 8]
l = 4
r = 13
```

### Step 1 : countAtMost(13)

| right | Window | Sum | Count Added |
|------:|--------|----:|------------:|
|0|[2]|2|1|
|1|[2,3]|5|2|
|2|[2,3,5]|10|3|
|3|Shrink to [5,8]|13|2|

```
countAtMost(13) = 8
```

---

### Step 2 : countAtMost(3)

Valid subarrays:

```
[2]
[3]
```

```
countAtMost(3) = 2
```

---

### Step 3

```
answer = 8 - 2 = 6
```

### Output

```
6
```

Valid subarrays are

```
[2,3]
[2,3,5]
[3,5]
[5]
[5,8]
[8]
```

---

---

# Visualize the Trick

Suppose every possible subarray sum is represented on a number line.

```
Possible sums:
1   2   3   4   5   6   7   8   9   10
```

Let

```
l = 4
r = 8
```

### Step 1: Count all subarrays with sum ≤ 8

```
✓   ✓   ✓   ✓   ✓   ✓   ✓   ✓   ✗   ✗
1   2   3   4   5   6   7   8   9   10
```

This includes every subarray whose sum is 1 through 8.

---

### Step 2: Count all subarrays with sum ≤ 3

```
✓   ✓   ✓   ✗   ✗   ✗   ✗   ✗   ✗   ✗
1   2   3   4   5   6   7   8   9   10
```

These are the subarrays whose sums are **less than l**.

---

### Step 3: Subtract them

```
countAtMost(8)
-
countAtMost(3)
```

Visually,

```
Before subtraction

≤ 8
✓ ✓ ✓ ✓ ✓ ✓ ✓ ✓ ✗ ✗

≤ 3
✓ ✓ ✓ ✗ ✗ ✗ ✗ ✗ ✗ ✗

----------------------

Remaining

✗ ✗ ✗ ✓ ✓ ✓ ✓ ✓ ✗ ✗
```

Only the sums

```
4   5   6   7   8
```

remain.

These are exactly the sums in the range

```
[l, r]
```

Therefore,

```java
answer = countAtMost(r) - countAtMost(l - 1);
```

---

## Another Visualization Using Actual Subarrays

Suppose

```
arr = [2, 3, 5]
l = 4
r = 7
```

All subarrays are

| Subarray | Sum |
|----------|----:|
|[2]|2|
|[2,3]|5|
|[2,3,5]|10|
|[3]|3|
|[3,5]|8|
|[5]|5|

### countAtMost(7)

```
✓ [2]
✓ [2,3]
✗ [2,3,5]
✓ [3]
✗ [3,5]
✓ [5]
```

Count = **4**

### countAtMost(3)

```
✓ [2]
✗ [2,3]
✗ [2,3,5]
✓ [3]
✗ [3,5]
✗ [5]
```

Count = **2**

Subtract:

```
4 - 2 = 2
```

The remaining valid subarrays are

```
[2,3]
[5]
```

whose sums are **5**, which lies in **[4,7]**.


# Algorithm

1. Create a helper function `countAtMost(k)`.
2. Use two pointers (`left`, `right`) and maintain the current window sum.
3. Expand the window by moving `right`.
4. If the sum exceeds `k`, move `left` until the window is valid.
5. Add `right - left + 1` to the answer.
6. Return

```java
countAtMost(r) - countAtMost(l - 1)
```

---

# Java Code

```java
class Solution {

    public int countSubarray(int[] arr, int l, int r) {
        return countAtMost(arr, r) - countAtMost(arr, l - 1);
    }

    private int countAtMost(int[] arr, int k) {
        if (k < 0) return 0;

        int left = 0;
        int sum = 0;
        int count = 0;

        for (int right = 0; right < arr.length; right++) {
            sum += arr[right];

            while (sum > k) {
                sum -= arr[left];
                left++;
            }

            count += (right - left + 1);
        }

        return count;
    }
}
```

---

# Complexity

- **Time Complexity:** `O(n)`
- **Space Complexity:** `O(1)`

---

# Important Note

This solution is correct **only when all array elements are positive (or non-negative)**.

If negative numbers are allowed, the sliding window technique no longer works, and the correct approach is **Prefix Sum + Merge Sort (`O(n log n)`)**.
