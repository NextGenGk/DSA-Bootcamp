# Minimum Product of a Non-Empty Subset

## Problem

Given an integer array `arr[]`, find the minimum possible product that can be obtained by multiplying the elements of any **non-empty subset** of the array.

### Example

**Input:**
```text
arr[] = [1, 2, 3]
```

**Output:**
```text
1
```

### Explanation

The non-empty subsets of `[1, 2, 3]` are:

```text
[1]       -> 1
[2]       -> 2
[3]       -> 3
[1, 2]    -> 2
[1, 3]    -> 3
[2, 3]    -> 6
[1, 2, 3] -> 6
```

The minimum product is `1`, obtained from the subset `[1]`.

The empty subset `[]` is not considered because the problem requires a **non-empty subset**.

---

## Input

An integer array:

```java
int[] arr
```

For example:

```text
[1, 2, 3]
```

---

## Output

Return an integer representing the minimum product obtainable from any non-empty subset.

For:

```text
[1, 2, 3]
```

the output is:

```text
1
```

---

# Intuition

For every element in the array, we have exactly **two choices**:

1. Do not include the element in the subset.
2. Include the element in the subset.

For example, with:

```text
[1, 2, 3]
```

we start with an empty subset:

```text
[]
```

For `1`:

```text
Don't take 1  -> []
Take 1        -> [1]
```

For `2`, both of those branches split again:

```text
             []
           /    \
        skip 2  take 2
          |        |
         []       [2]

             [1]
           /     \
        skip 2   take 2
          |         |
         [1]      [1,2]
```

The same thing happens for `3`.

Therefore, each element creates two choices, giving:

```text
2^n
```

possible subsets.

We generate every subset using recursion and keep the minimum product.

---

# Algorithm

We use a recursive **include/exclude** approach.

### Step 1

Start from index `0` with:

```java
product = 1
selected = false
```

The initial product is `1` because `1` is the multiplicative identity.

`selected = false` means that no element has been selected yet.

### Step 2

At every index, make two recursive calls.

#### Choice 1: Don't take the current element

```java
generate(arr, index + 1, product, selected);
```

The product stays unchanged.

#### Choice 2: Take the current element

```java
generate(
    arr,
    index + 1,
    product * arr[index],
    true
);
```

The current element is multiplied into the product, and `selected` becomes `true`.

### Step 3

When:

```java
index == arr.length
```

all elements have been considered.

If `selected` is `true`, we have a valid non-empty subset, so return its product.

If `selected` is `false`, the subset is empty. Return:

```java
Integer.MAX_VALUE
```

so that the empty subset cannot become the minimum.

### Step 4

After calculating the result of both choices:

```java
int notTake = ...
int take = ...
```

return:

```java
Math.min(notTake, take)
```

This propagates the smallest product back up through the recursion.

---

# Recursion Tree

For:

```text
arr = [1, 2, 3]
```

the recursion tree is:

```text
                              []
                         index = 0
                        product = 1
                       /           \
                Don't take 1       Take 1
                     []               [1]
                  /     \           /     \
           Don't 2    Take 2   Don't 2   Take 2
              []        [2]       [1]      [1,2]
             / \       / \      / \       / \
           D3   T3   D3   T3   D3   T3   D3   T3
           |     |    |     |    |     |    |     |
          []   [3]  [2]  [2,3] [1] [1,3] [1,2] [1,2,3]
```

There are:

```text
2^3 = 8
```

subsets in total.

The empty subset is ignored.

---

# Code

```java
class Solution {

    public int minProd(int[] arr) {
        return generate(arr, 0, 1, false);
    }

    int generate(int[] arr, int index, int product, boolean selected) {

        // All elements processed
        if (index == arr.length) {

            // Valid non-empty subset
            if (selected) {
                return product;
            }

            // Empty subset is not allowed
            return Integer.MAX_VALUE;
        }

        // Choice 1: Don't take arr[index]
        int notTake = generate(
            arr,
            index + 1,
            product,
            selected
        );

        // Choice 2: Take arr[index]
        int take = generate(
            arr,
            index + 1,
            product * arr[index],
            true
        );

        // Return the smaller product
        return Math.min(notTake, take);
    }
}
```

---

# Code Explanation

## `minProd()`

```java
public int minProd(int[] arr) {
    return generate(arr, 0, 1, false);
}
```

This starts the recursion.

The four values mean:

```text
arr       -> original array
0         -> start at index 0
1         -> initial product
false     -> no element selected yet
```

---

## `index`

```java
int index
```

tells us which element we are currently considering.

For:

```text
[1, 2, 3]
```

we have:

```text
index = 0 -> 1
index = 1 -> 2
index = 2 -> 3
index = 3 -> finished
```

---

## `product`

```java
int product
```

stores the product of the elements selected so far.

For example, if we select:

```text
[1, 2]
```

then:

```text
product = 1 * 2 = 2
```

---

## `selected`

```java
boolean selected
```

tells us whether the current subset contains at least one element.

Initially:

```java
false
```

because the subset is empty.

When we take an element:

```java
true
```

This is necessary because the problem says **non-empty subset**.

---

## Base Case

```java
if (index == arr.length)
```

This means we have considered every element.

If:

```java
selected == true
```

we have a valid subset:

```java
return product;
```

Otherwise, we have the empty subset:

```java
return Integer.MAX_VALUE;
```

We use `Integer.MAX_VALUE` because we are looking for the minimum. It effectively makes the empty subset impossible to choose as the answer.

---

## Don't Take

```java
int notTake = generate(
    arr,
    index + 1,
    product,
    selected
);
```

The current element is excluded.

Therefore, `product` remains unchanged.

---

## Take

```java
int take = generate(
    arr,
    index + 1,
    product * arr[index],
    true
);
```

The current element is included.

Therefore:

```text
new product = old product * current element
```

and:

```java
selected = true;
```

because the subset now contains at least one element.

---

## Find the Minimum

```java
return Math.min(notTake, take);
```

The recursive calls return the minimum product from their respective branches.

We simply choose the smaller one.

---

# Complexity Analysis

## Time Complexity

For every element, there are two choices:

```text
Take
Don't take
```

Therefore, for `n` elements:

```text
2 * 2 * 2 * ... * 2
```

which is:

```text
2^n
```

So the time complexity is:

```text
O(2^n)
```

We visit every possible subset.

---

## Space Complexity

The maximum recursion depth is `n`.

Therefore, the recursion stack uses:

```text
O(n)
```

extra space.

So:

```text
Time Complexity  : O(2^n)
Space Complexity : O(n)
```

---

# Important Pattern to Remember

This problem demonstrates a very common recursion pattern:

```text
                    Current Element
                    /              \
                 Don't             Take
                   |                 |
              index + 1          index + 1
```

The general template is:

```java
solve(index) {

    if (index == n) {
        // base case
        return;
    }

    // Don't take
    solve(index + 1);

    // Take
    solve(index + 1);
}
```

Whenever a problem asks you to generate:

- all subsets
- all subsequences
- include/exclude combinations
- every possible selection

this **take / don't-take recursion pattern** is one of the first approaches to consider.

---

# Summary

For `arr = [1, 2, 3]`:

```text
Number of subsets = 2^3 = 8
```

Non-empty subsets:

```text
[1]
[2]
[3]
[1,2]
[1,3]
[2,3]
[1,2,3]
```

Their products:

```text
1
2
3
2
3
6
6
```

Minimum:

```text
1
```

Final result:

```text
1
```

Complexity:

```text
Time  : O(2^n)
Space : O(n)
```
