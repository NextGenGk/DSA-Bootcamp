# Find Mark by Rank in Sorted Intervals

## Problem Statement

We are given two arrays `l[]` and `r[]`.

- `l[i]` is the starting mark of the `i-th` interval.
- `r[i]` is the ending mark of the `i-th` interval.
- Every interval contains all integers from `l[i]` to `r[i]`, inclusive.
- The intervals are sorted and do not overlap.
- We are also given `rank[]`.

For every value in `rank[]`, find the mark having that rank among all valid marks in increasing order.

Return the answers as an `ArrayList<Integer>`.

---

## Input / Output

### Input

```text
l[]    = [1, 6, 14]
r[]    = [3, 9, 15]
rank[] = [2, 5, 8]
```

### Output

```text
[2, 7, 14]
```

### Explanation

The intervals represent these valid marks:

```text
[1, 3]   -> 1, 2, 3
[6, 9]   -> 6, 7, 8, 9
[14,15]  -> 14, 15
```

Therefore, all valid marks are:

```text
1, 2, 3, 6, 7, 8, 9, 14, 15
```

Their ranks are:

```text
Rank:  1  2  3  4  5  6  7  8  9
Mark:  1  2  3  6  7  8  9 14 15
```

So:

```text
rank 2 -> mark 2
rank 5 -> mark 7
rank 8 -> mark 14
```

Hence the answer is:

```text
[2, 7, 14]
```

---

## Intuition

The important thing to understand is that each interval represents a group of consecutive marks.

For example:

```text
[6, 9]
```

contains:

```text
6, 7, 8, 9
```

So it contains:

```text
9 - 6 + 1 = 4
```

marks.

For every requested rank, we start from the first interval.

If the requested rank is larger than the number of marks in the current interval, we skip that entire interval.

For example, to find rank `5`:

```text
First interval: [1, 3]
Number of marks = 3
```

Rank `5` is not in this interval, so:

```text
5 - 3 = 2
```

Now we need the 2nd mark in the next interval:

```text
[6, 9]
```

The 2nd mark is:

```text
6 + 2 - 1 = 7
```

Therefore:

```text
rank 5 -> mark 7
```

### Key Formula

Number of marks in an interval:

```text
count = r[i] - l[i] + 1
```

If the desired rank is inside the interval:

```text
mark = l[i] + remaining - 1
```

---

## Algorithm

For every value `k` in `rank[]`:

1. Set `remaining = k`.
2. Go through every interval from left to right.
3. Calculate the number of marks in the current interval:
   ```text
   count = r[i] - l[i] + 1
   ```
4. If `remaining <= count`:
   - The required mark is inside this interval.
   - Calculate:
     ```text
     mark = l[i] + remaining - 1
     ```
   - Add the mark to the answer.
   - Stop searching for this rank.
5. Otherwise:
   - The whole interval can be skipped.
   - Subtract its size:
     ```text
     remaining = remaining - count
     ```
6. Repeat for the next requested rank.

---

## Example

Consider:

```text
l[]    = [5, 10]
r[]    = [7, 12]
rank[] = [1, 4, 6]
```

The valid marks are:

```text
[5, 7]   -> 5, 6, 7
[10,12]  -> 10, 11, 12
```

So:

```text
Rank:  1  2  3  4   5   6
Mark:  5  6  7  10  11  12
```

### rank = 1

First interval:

```text
[5, 7]
```

It has 3 marks.

Since:

```text
1 <= 3
```

the answer is inside this interval.

```text
mark = 5 + 1 - 1
     = 5
```

So:

```text
1 -> 5
```

### rank = 4

Start with:

```text
remaining = 4
```

First interval has 3 marks, so skip it:

```text
remaining = 4 - 3
          = 1
```

Now we are in `[10, 12]`.

The 1st mark is:

```text
mark = 10 + 1 - 1
     = 10
```

So:

```text
4 -> 10
```

### rank = 6

Start with:

```text
remaining = 6
```

Skip the first interval:

```text
remaining = 6 - 3
          = 3
```

Now `[10, 12]` contains 3 marks.

The 3rd mark is:

```text
mark = 10 + 3 - 1
     = 12
```

So:

```text
6 -> 12
```

Final output:

```text
[5, 10, 12]
```

---

## Java Code

```java
import java.util.*;

class Solution {
    public ArrayList<Integer> getMarks(int[] l, int[] r, int[] rank) {
        ArrayList<Integer> ans = new ArrayList<>();

        // Process every requested rank
        for (int k : rank) {

            // 'remaining' tells us which position
            // we need to find among the current interval.
            int remaining = k;

            // Check intervals from left to right
            for (int i = 0; i < l.length; i++) {

                // Number of marks in the current interval.
                // +1 because both endpoints are included.
                int count = r[i] - l[i] + 1;

                // If the required rank is inside
                // the current interval
                if (remaining <= count) {

                    // Find the mark at 'remaining' position.
                    //
                    // Example:
                    // interval = [6, 9]
                    // remaining = 2
                    //
                    // mark = 6 + 2 - 1
                    //      = 7
                    int mark = l[i] + remaining - 1;

                    ans.add(mark);

                    // We found the answer for this rank,
                    // so move to the next rank.
                    break;
                }

                // The required rank is not in this interval.
                // Skip all marks in this interval.
                remaining -= count;
            }
        }

        return ans;
    }
}
```

---

## Code Explanation

The most important part is:

```java
int count = r[i] - l[i] + 1;
```

This calculates how many marks are in the interval.

For example:

```text
[1, 3]

count = 3 - 1 + 1
      = 3
```

So the interval contains `1, 2, 3`.

Next:

```java
if (remaining <= count)
```

checks whether the required rank belongs to the current interval.

If it does:

```java
int mark = l[i] + remaining - 1;
```

finds the actual mark.

If it doesn't:

```java
remaining -= count;
```

means:

> "Skip this entire interval and continue looking in the next one."

---

## Complexity

Let:

- `Q` = number of elements in `rank[]`
- `N` = number of intervals

For every rank, we may scan all intervals.

### Time Complexity

```text
O(Q × N)
```

### Space Complexity

```text
O(Q)
```

The `O(Q)` space is for storing the output.

---

## Quick Summary

Remember these two formulas:

```text
Number of marks:
count = r[i] - l[i] + 1
```

When the rank is inside the interval:

```text
mark = l[i] + remaining - 1
```

Otherwise:

```text
remaining -= count
```

That is the complete idea behind the solution.
