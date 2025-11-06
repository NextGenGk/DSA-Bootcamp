package Strings;

import java.util.HashMap;

public class Isomorphic_Strings {

    // Method : HashMap based One-to-One Mapping
    // Time Complexity: O(N^2) in worst case because map.containsValue() is O(N)
    // Space Complexity: O(N) for storing mapping of characters
    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> map = new HashMap<>();

        // Edge Case: If lengths are different, they can't be isomorphic
        if (s.length() != t.length()) return false;

        // Traverse both strings character by character
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);   // character from s
            char c2 = t.charAt(i);   // corresponding character from t

            // Case 1: mapping already exists for c1
            if (map.containsKey(c1)) {
                // mapping exists but does NOT match → NOT isomorphic
                if (map.get(c1) != c2) {
                    return false;
                }
            }
            // Case 2: mapping does NOT exist for c1 but c2 is already mapped by some other char
            // Ex: s = "ab", t = "cc" → b cannot also map to c
            else if (map.containsValue(c2)) {  // prevents two keys mapping to the same value
                return false;
            }
            // Case 3: No mapping yet → create mapping
            else {
                map.put(c1, c2);
            }
        }

        return true;
    }
}

/*
---------------------------- OUTPUT ----------------------------
Input:  s = "egg", t = "add"
Output: true
Explanation:
    e -> a
    g -> d
    One-to-one mapping holds.
----------------------------------------------------------------
*/

/*
---------------------------- ALGORITHM -------------------------
1. If lengths differ, return false.
2. Create a HashMap<Character, Character> to store mapping from s → t.
3. Loop through both strings:
     a. If c1 already mapped:
          check if it maps to the same c2. If not, return false.
     b. If c1 not mapped but c2 already mapped by someone else:
          return false (prevents many-to-one mapping)
     c. Otherwise, store mapping c1 → c2 into map.
4. If loop completes without mismatch, return true.
----------------------------------------------------------------
*/

/*
---------------------------- EDGE CASES ------------------------
1. s = "ab", t = "aa"   → false (b cannot also map to a)
2. s = "paper", t = "title" → true
3. s = "foo", t = "bar" → false (mapping conflict)
4. Different lengths → false immediately
----------------------------------------------------------------
*/

// Striver's (Article Explanation): https://takeuforward.org/data-structure/isomorphic-string
