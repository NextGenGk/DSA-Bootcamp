package LinkedList;

public class Design_Browser_History {

    // Method 1 : Optimal Solution
    // Time Complexity: O(steps) where 'steps' denotes the number of steps to move backward or
    // forward in the browsing history.

    // To visit a web page ie. create a node takes constant time ie. O(1) and it doesn’t depend
    // on the number of existing web pages as it only adds a new node in front of the current page pointer.

    // Space Complexity: O(N) where N denotes the number of web pages visited. Each webpage is
    // represented by a Node object containing the URL and the pointers to the next and previous
    // pages hence the complexity is proportional to the number of web pages visited.

    // Node class to represent each page in the browser history
    class Node {
        String data; // Stores the URL of the page
        Node next;   // Pointer to the next page in history
        Node back;   // Pointer to the previous page in history

        // Default constructor initializes with a placeholder value
        Node() {
            data = "0";
            next = null;
            back = null;
        }

        // Constructor with data initializes with the given URL
        Node(String x) {
            data = x;
            next = null;
            back = null;
        }

        // Constructor with data and pointers
        Node(String x, Node next, Node back) {
            data = x;
            this.next = next;
            this.back = back;
        }
    }

    class BrowserHistory {
        // Pointer to the current page in the browser history
        Node currentPage;

        // Constructor initializes the browser history with a homepage
        public BrowserHistory(String homepage) {
            currentPage = new Node(homepage);
        }

        // Method to visit a new URL
        public void visit(String url) {
            Node node = new Node(url); // Create a new node for the visited page
            node.back = currentPage;   // Set the back pointer to the current page
            currentPage.next = node;   // Link the current page to the new page
            currentPage = node;        // Update the current page pointer to the new page
        }

        // Method to go back 'steps' times in the browsing history
        public String back(int steps) {
            while (steps > 0 && currentPage.back != null) { // Move back until steps are exhausted or no previous page
                currentPage = currentPage.back;
                steps--;
            }
            return currentPage.data; // Return the URL of the current page after moving back
        }

        // Method to go forward 'steps' times in the browsing history
        public String forward(int steps) {
            while (steps > 0 && currentPage.next != null) { // Move forward until steps are exhausted or no next page
                currentPage = currentPage.next;
                steps--;
            }
            return currentPage.data; // Return the URL of the current page after moving forward
        }
    }

    // Main Function
    public static void main(String[] args) {
        Design_Browser_History browser = new Design_Browser_History();
        BrowserHistory history = browser.new BrowserHistory("leetcode.com");
        history.visit("google.com");
        history.visit("facebook.com");
        history.visit("youtube.com");
        System.out.println(history.back(1)); // facebook.com
        System.out.println(history.back(1)); // google.com
        System.out.println(history.forward(1)); // facebook.com
        history.visit("linkedin.com");
        System.out.println(history.forward(2)); // linkedin.com
        System.out.println(history.back(2)); // google.com
        System.out.println(history.back(7)); // leetcode.com
    }
}

// Output
/*
facebook.com
google.com
facebook.com
linkedin.com
google.com
leetcode.com
 */

// Intuition : Optimal Solution
/*
Algorithm / Intuition
The problem involves implementing a basic browser functionality that supports visiting URLs,
going back a certain number of steps in history and going forward a certain number of steps in history.

Node Class: Define a ‘Node” class to represent each page in the browsing history, linked by pointers
to the previous and next pages.

1. ‘data’ stores the URL or identifier of the webpage.
2. ‘next’ stores the pointer to the next page in the browsing history.
3. ‘back’ stores the previous page in the browsing history

‘next’ and ‘back’ pointers link each node to its adjacent nodes forming a doubly linked list
representing the browsing history and enabling navigation backward and forward in the browsing history.

Browser Class:

The Browser Class has to be implemented in such a way that it encapsulates the functionalities
of browsing, managing a history of visited URLs and navigating that history.

Constructor: initialises the `Browser` object with the specified homepage URL and it creates a
`Node` representing the homepage and sets it as the `currentPage`.

`currentPage` is a private member variable initialised when a new ‘Browser’ object is created.
Each instance of the `Browser` class will have its own `currentPage` pointer, maintaining its
individual browsing history.

`visit (string url)` adds a new page to the history by creating a new `Node` with the given URL,
setting its back pointer to the current page and updating the current page to this node.

`back (int steps)` navigates backward in the history by the specified number of steps, updating
the `currentPage` pointer to the previous node. Returns the URL of the resulting page.

`forward (int steps)` navigates forward in the history by a specified number of steps, updating
the `currentPage` pointer to the next node. Returns the URL of the resulting page.


This Browser class essentially maintains a linked list structure of visited pages, where each
Node represents a page and contains the URL and pointers to the previous and next pages in the
history. The Browser methods enable navigation through this history by updating the currentPage
pointer accordingly.

Algorithm
Step 1:Define a Node class to represent each webpage in the browsing history. This class should
have data fields for URL, a pointer to the next page, and a pointer to the previous page.

Step 2: Implement the Browser class by Create a private member variable `currentPage` within the
constructor to maintain its browser history navigation pointer.


Step 3: Implement the `visit` function by adding a new webpage to the browsing history every time
it is called.

    1. When invoked, this function creates a new Node representing the visited webpage and updates
       the pointers linking it ahead of the current webpage.
    2. The browser history now gets extended to this newly visited webpage with the currentPage
       navigator on it to go forward and backward.

Step 4: Implement the `forward function to facilitate forward traversal in the browsing history.

    1. This function advances the `currentPage` pointer to the next web page in the sequence.
    2. Allows users to navigate forward by a specified number of steps within their browsing history
       object instance.

Step 5: Implement the `backward function` to facilitate backward navigation in the browsing history.

    1. This function moves the `currentPage` pointer backward to the previous web pages.
    2. Allows users to move to web pages they visited web pages they had previously visited.
 */

// Striver's (Video Explanation) : https://www.youtube.com/watch?v=mG3KLugbOdc