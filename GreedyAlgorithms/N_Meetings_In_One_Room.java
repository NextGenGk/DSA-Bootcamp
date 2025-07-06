package GreedyAlgorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class N_Meetings_In_One_Room {

    // Method 1 : Optimal Solution
    // Time Complexity: O(N) to iterate through every position and insert them in a data structure.
    // O(N log N)  to sort the data structure in ascending order of end time. O(n)  to iterate through
    // the positions and check which meeting can be performed.
    // Overall : O(N) +O(N log N) + O(N) ~ O(N log N)
    // Space Complexity: O(N)  since we used an additional data structure for storing the start time,
    // end time, and meeting no.
    static class Meeting {
        int start;   // The start time of the meeting
        int end;     // The end time of the meeting
        int position; // The position of the meeting in the input list

        // Constructor to initialize the meeting object
        public Meeting(int start, int end, int position) {
            this.start = start;
            this.end = end;
            this.position = position;
        }
    }

    // Comparator class to sort meetings by their end time first,
    // and if end times are equal, by their position in the list
    static class meetingComparator implements Comparator<Meeting> {

        @Override
        public int compare(Meeting o1, Meeting o2) {
            // Compare by end time (sort by ascending order)
            if (o1.end < o2.end) {
                return -1;  // o1 comes before o2
            }
            else if (o1.end > o2.end) {
                return 1;   // o2 comes before o1
            }
            // If end times are the same, compare by position (ascending order)
            else if (o1.position < o2.position) {
                return -1;  // o1 comes before o2 based on position
            }
            return 1; // Otherwise, o2 comes before o1
        }
    }

    // Comparator working 
    /*
    [1: (1,2)], [2: (3,4)], [3: (0,5)], [4: (5,7)], [5: (8,9)], [6: (5,9)]

    Step 1: Start Sorting (using comparator)
    
    Iteration 1: Compare Meeting 1 and Meeting 2
    Meeting 1 end = 2, Meeting 2 end = 4
    2 < 4 → return -1
    Result: Meeting 1 goes before Meeting 2
    
    Iteration 2: Compare Meeting 3 and Meeting 2
    Meeting 3 end = 5, Meeting 2 end = 4
    5 > 4 → return 1
    Result: Meeting 2 goes before Meeting 3
    
    Iteration 3: Compare Meeting 3 and Meeting 4
    Meeting 3 end = 5, Meeting 4 end = 7
    5 < 7 → return -1
    Result: Meeting 3 goes before Meeting 4
    
    Iteration 4: Compare Meeting 5 and Meeting 6
    Both end at 9
    Compare position: Meeting 5 position = 5, Meeting 6 position = 6
    5 < 6 → return -1
    Result: Meeting 5 goes before Meeting 6
    
    Step 2: Final Sorted Order
    After all pairwise comparisons, the sorted order is:
    
    Meeting 1 (end=2, pos=1)
    Meeting 2 (end=4, pos=2)
    Meeting 3 (end=5, pos=3)
    Meeting 4 (end=7, pos=4)
    Meeting 5 (end=9, pos=5)
    Meeting 6 (end=9, pos=6)
    */    

    // Function to find the maximum number of meetings that can be attended
    // without any overlap, and print the order in which they should be attended
    public static void maxMeetings(int[] start, int[] end, int n) {
        ArrayList<Meeting> meet = new ArrayList<>();

        // Create Meeting objects for each start and end time
        for (int i = 0; i < start.length; i++) {
            meet.add(new Meeting(start[i], end[i], i+1));  // i+1 to store original position (1-indexed)
        }

        // Sort the meetings using the meetingComparator by end time and position
        meetingComparator meetingComparator = new meetingComparator();
        Collections.sort(meet, meetingComparator);

        // To store the order of meetings that can be attended
        ArrayList<Integer> result = new ArrayList<Integer>();

        // Add the first meeting in the sorted list to the result
        result.add(meet.get(0).position);

        // Track the ending time of the last selected meeting
        int limit = meet.get(0).end;

        // Iterate through the sorted meetings
        for (int i = 1; i < start.length; i++) {
            // If the start time of the current meeting is after the end time of the last attended meeting
            if (meet.get(i).start > limit) {
                limit = meet.get(i).end;  // Update limit to the end time of the current meeting
                result.add(meet.get(i).position); // Add the current meeting's position to the result
            }
        }

        // Print the order of meetings that can be attended
        System.out.println("The order in which the meetings will be performed is ");
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i) + " ");
        }
    }

    // Main Function
    public static void main(String[] args) {
        // Number of meetings
        int n = 6;

        // Start and end times of the meetings
        int[] start = {1, 3, 0, 5, 8, 5};
        int[] end = {2, 4, 5, 7, 9, 9};

        // Call maxMeetings function to find the optimal meeting schedule
        maxMeetings(start, end, n);
    }
}

// Output :
/*
The order in which the meetings will be performed is
1 2 4 5
 */

// Approach / Intuition : Optimal Solution
/*
Initial Thought Process:-
Say if you have two meetings, one which gets over early and another which gets over late. Which one
should we choose?  If our meeting lasts longer the room stays occupied and we lose our time. On the
other hand, if we choose a meeting that finishes early we can accommodate more meetings. Hence we
should choose meetings that end early and utilize the remaining time for more meetings.

The code aims to maximize the number of non-overlapping meetings that can be attended. It first
creates `Meeting` objects for each meeting's start and end times, then sorts them by end time
(and by position if end times are the same) using a custom comparator. By sorting this way, the
meetings that finish earliest are prioritized. The algorithm iterates through the sorted list and
greedily selects meetings whose start time is after the previous selected meeting’s end time. This
ensures that the maximum number of meetings are attended without overlap, and the selected meetings'
positions are printed in the order they can be attended.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=mKfhTotEguk
