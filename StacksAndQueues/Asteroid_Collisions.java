package StacksAndQueues;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Stack;

public class Asteroid_Collisions {

    // Method 1 : Optimal Solution
    // Time Complexity : O(2N), O(N) for traversing and another O(N) for pushing and popping
    // elements onto the stack.
    // Space Complexity : O(2N), O(N) is for using external list data structure and another O(N) for converting
    // list into array to return the answer

    // Function to simulate the asteroid collisions
    public static int[] asteroidCollisions(int[] arr) {
        // List to store the resulting asteroids after collisions
        List<Integer> list = new ArrayList<>();

        // Loop through each asteroid in the array
        for (int i = 0; i < arr.length; i++) {
            // If the current asteroid is moving to the right (positive direction)
            if (arr[i] > 0) {
                // Add it directly to the list (no collision with left-moving asteroids)
                list.add(arr[i]);
            }
            // If the current asteroid is moving to the left (negative direction)
            else {
                // Check for collisions with right-moving asteroids in the list
                while (!list.isEmpty() && list.get(list.size() - 1) > 0 &&
                        list.get(list.size() - 1) < Math.abs(arr[i])) {
                    // Remove the smaller right-moving asteroid since it collides and explodes
                    list.remove(list.size() - 1);
                }

                // If the list is empty or the last asteroid in the list is also moving to the left,
                // or there are no more right-moving asteroids to collide with
                if (list.isEmpty() || list.get(list.size() - 1) < 0) {
                    // Add the current left-moving asteroid to the list
                    list.add(arr[i]);
                }
                // If the last asteroid in the list is the same size but moving in the opposite direction
                else if (list.get(list.size() - 1) == Math.abs(arr[i])) {
                    // Both asteroids destroy each other (equal in magnitude), so remove the last one
                    list.remove(list.size() - 1);
                }
                // If the current left-moving asteroid is smaller, it is destroyed by the larger right-moving asteroid,
                // and we do not add it to the list (handled implicitly by not adding it to the list).
            }
        }

        // Convert the List of remaining asteroids to an array to return as the result
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    // Main Function
    public static void main(String[] args) {
        int[] arr = {4, 7, 1, 1, 2, -3, -7, 17, 15, -16};
        int[] result1 = asteroidCollisions(arr);
        System.out.println("Result 1: " + Arrays.toString(result1));
    }
}

// Output : [4, 17]

// Approach : Optimal Solution
/*
Intuition & Idea Behind Using List :

1. Positive asteroids move right and are added to the list because they don't affect each other.
2. Negative asteroids move left and may collide with the last right-moving asteroid in the list.
    i. If the negative asteroid is stronger, it destroys the last positive asteroid.
   ii. If both are the same size, both are destroyed.
  iii. If the positive asteroid is stronger, the negative one is destroyed.

The List acts like a flexible stack, allowing us to easily manage asteroid collisions by adding
and removing elements from the end. It helps handle dynamic changes in asteroid counts without
worrying about fixed sizes.

At the end, whatever is left in the list are the surviving asteroids.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=_eYGqw_VDR4