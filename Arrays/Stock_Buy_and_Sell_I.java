package Arrays;

public class Stock_Buy_and_Sell_I {


    // Method 1: Brute Force
    // Time - O(N^2), Space - O(1)
   static int maximize (int[] arr, int n) {
       int maxProfit = 0;
       for (int i=0; i<n; i++) {
           for (int j=i+1; j<n; j++) {
               if (arr[j] - arr[i] > maxProfit) {
                   maxProfit = arr[j] - arr[i];
               }
           }
       }
       return maxProfit;
   }

    // Method 2: Better Solution (Using temp array)
    // Time - O(N), Space - O(N)
    static int maximize1(int[] arr, int n) {
       int[] temp = new int[n];

       temp[n-1] = arr[n-1];

       for (int i=n-2; i>=0; i--) {
           temp[i] = Math.max(arr[i], temp[i+1]);
       }

       int maxProfit = 0;
       for (int i=0; i<n; i++) {
           maxProfit = Math.max(maxProfit, temp[i] - arr[i]);
       }
       return maxProfit;
   }

    // Method 3 - Optimal Solution (Using minPrice and maxProfit)
    // Time - O(N), Space - O(1)
    static int maximize2(int[] arr, int n) {
        int minPrice = arr[0];
        int maxProfit = 0;

        for (int i=0; i<n; i++) {
            minPrice = Math.min(arr[i], minPrice);
            int profit = arr[i] - minPrice;
            maxProfit = Math.max(maxProfit, profit);
        }
        return maxProfit;
    }

    // Main Function
    public static void main(String[] args) {
        int[] arr = {7, 1, 5, 6, 3, 4};
        int n = arr.length;

        int res = maximize(arr, n);
        System.out.println(res);
    }
}

// Output:
// 5

// Algorithm: Brute Force
/*
1. Initialize maxProfit = 0
2. Run a loop from i = 0 to n-1
    a. Run a loop from j = i+1 to n-1
        i. If arr[j] - arr[i] > maxProfit, then update maxProfit
3. Return maxProfit
 */

// Algorithm: Better Solution (Using temp array)
// Idea - Find the maximum element in the right side (Preprocessing)
/*
1. Initialize temp array of size n
2. Initialize temp[n-1] = arr[n-1]
3. Run a loop from i = n-2 to 0
    a. temp[i] = max(arr[i], temp[i+1])
4. Initialize j = 0
5. Initialize maxProfit = 0
6. Run a loop from i = 0 to n-1
    a. maxProfit = max(maxProfit, temp[j] - arr[i])
    b. j++
7. Return maxProfit
 */

// Algorithm: Optimal Solution (Using minPrice and maxProfit)
// Idea : In order to maximize the profit, we need to minimize the cost price and maximize the selling price. 
// So at every step, we keep track of the minimum buy price of stock encountered so far. For every price, 
// we subtract with the minimum so far and if we get more profit than the current result, we update the result.
/*
1. Initialize minPrice = arr[0]
2. Initialize maxProfit = 0
3. Run a loop from i = 0 to n-1
    a. minPrice = min(arr[i], minPrice)
    b. profit = arr[i] - minPrice
    c. maxProfit = max(maxProfit, profit)
4. Return maxProfit
 */

// Anuj Bhaiya Video Link: https://youtu.be/34WE6kwq49U
