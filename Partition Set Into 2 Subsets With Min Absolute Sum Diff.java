import java.util.*;
class Solution {
    public int minimumDifference(int[] nums) {
        int n = nums.length;
        int sum = Arrays.stream(nums).sum(); // To find the total sum of the array      
        int N = n / 2; // Divide it into two equal parts as length is even
        List<List<Integer>> left = new ArrayList<>(N + 1);
        List<List<Integer>> right = new ArrayList<>(N + 1);     
        for (int i = 0; i <= N; ++i) {
            left.add(new ArrayList<>());
            right.add(new ArrayList<>());
        }
        // All possible sums in left and right part (Generating and storing using BIT-Masking)
        for (int mask = 0; mask < (1 << N); ++mask) {  // (1<<N) means 2^N i.e we'll iterate through 0 to 2^N
            int idx = 0, l_sum = 0, r_sum = 0;
            for (int i = 0; i < N; ++i) {
                if ((mask & (1 << i)) != 0) {  // To check if the bit is set or not 
                    idx++;
                    l_sum += nums[i];
                    r_sum += nums[i + N];
                }
            }
            left.get(idx).add(l_sum);
            right.get(idx).add(r_sum);   // Storing
        }
        for (int idx = 0; idx <= N; ++idx) {
            Collections.sort(right.get(idx));   // As we'll perform binary search on right, so we have to sort it first
        }
        int res = Math.min(Math.abs(sum - 2 * left.get(N).get(0)), Math.abs(sum - 2 * right.get(N).get(0)));  // To get the minimum answer from the max sum from both arrays
        // Iterating over left part
        for (int idx = 1; idx < N; ++idx) { // Iterate from 1 so we don't have to include 0 and check for all values except last as we have already considered it
            for (int a : left.get(idx)) { // Check the sum at each number position
                int b = (sum - 2 * a) / 2; // Find the value to be minimized 
                int rightIdx = N - idx; // Find the number value in right array
                List<Integer> v = right.get(rightIdx); // Store the vector in v at right number position
                int pos = Collections.binarySearch(v, b);
                if (pos < 0) {
                    pos = -pos - 1;
                }
                if (pos < v.size()) res = Math.min(res, Math.abs(sum - 2 * (a + v.get(pos)))); // If found in vector then only update otherwise continue
            }
        }
        return res;
    }
}
//above code works for both positive and negative elements in array 


//for only positive integers
//memoization approach
import java.util.*;

public class Main {
    // Helper function to calculate the minimum absolute difference of two subsets using memoization
    static int minSubsetSumDifference(ArrayList<Integer> arr, int n) {
        int totSum = 0;
        // Calculate the total sum of the array elements
        for (int num : arr) {
            totSum += num;
        }
        // Create a memoization table
        int[][] memo = new int[n][totSum + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        // Recursive function with memoization
        return findMinDifference(arr, n - 1, 0, totSum, memo);
    }
    // Recursive function to find the minimum difference
    static int findMinDifference(ArrayList<Integer> arr, int index, int currentSum, int totalSum, int[][] memo) {
        // Base case
        if (index < 0) {
            return Math.abs((totalSum - currentSum) - currentSum);
        }
        // Check if result is already computed
        if (memo[index][currentSum] != -1) {
            return memo[index][currentSum];
        }
        // Include the current element in the subset
        int include = findMinDifference(arr, index - 1, currentSum + arr.get(index), totalSum, memo);
        // Exclude the current element from the subset
        int exclude = findMinDifference(arr, index - 1, currentSum, totalSum, memo);
        // Store the result in the memoization table and return the minimum difference
        return memo[index][currentSum] = Math.min(include, exclude);
    }
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        int n = arr.size();
        // Calculate and print the minimum absolute difference
        System.out.println("The minimum absolute difference is: " + minSubsetSumDifference(arr, n));
    }
} //or u can simply fill the dp matrix using memization approch and iterate over last row to find answer which we do in tabulation



//tabulation
static int minSubsetSumDifference(ArrayList<Integer> arr, int n) {
        int totSum = 0;
        // Calculate the total sum of the array elements
        for (int i = 0; i < n; i++) {
            totSum += arr.get(i);
        }
        // Create a DP table to store subset sum information
        boolean[][] dp = new boolean[n][totSum + 1];
        // Initialize the DP table for the first row
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        // Initialize the DP table for the first column
        if (arr.get(0) <= totSum) {
            dp[0][totSum] = true;
        }
        // Fill in the DP table using bottom-up dynamic programming
        for (int ind = 1; ind < n; ind++) {
            for (int target = 1; target <= totSum; target++) {
                // Calculate if the current element is not taken
                boolean notTaken = dp[ind - 1][target];
                // Calculate if the current element is taken
                boolean taken = false;
                if (arr.get(ind) <= target) {
                    taken = dp[ind - 1][target - arr.get(ind)];
                }
                // Update the DP table for the current element and target sum
                dp[ind][target] = notTaken || taken;
            }
        }
        int mini = Integer.MAX_VALUE;
        // Find the minimum absolute difference between two subsets
        for (int i = 0; i <= totSum; i++) {
            if (dp[n - 1][i]) {
                int diff = Math.abs(i - (totSum - i));
                mini = Math.min(mini, diff);
            }
        }
        return mini;
    }
//space optimized
static int minSubsetSumDifference(ArrayList<Integer> arr, int n) {
        int totSum = 0;

        // Calculate the total sum of the array elements
        for (int i = 0; i < n; i++) {
            totSum += arr.get(i);
        }

        // Create an array to store DP results for the previous row
        boolean[] prev = new boolean[totSum + 1];

        // Initialize the DP array for the first row
        prev[0] = true;

        // Initialize the DP array for the first column
        if (arr.get(0) <= totSum) {
            prev[arr.get(0)] = true;
        }

        // Fill in the DP array using bottom-up dynamic programming
        for (int ind = 1; ind < n; ind++) {
            // Create an array to store DP results for the current row
            boolean[] cur = new boolean[totSum + 1];
            cur[0] = true;
            for (int target = 1; target <= totSum; target++) {
                // Calculate if the current element is not taken
                boolean notTaken = prev[target];

                // Calculate if the current element is taken
                boolean taken = false;
                if (arr.get(ind) <= target) {
                    taken = prev[target - arr.get(ind)];
                }

                // Update the DP array for the current element and target sum
                cur[target] = notTaken || taken;
            }
            prev = cur;
        }

        int mini = Integer.MAX_VALUE;

        // Find the minimum absolute difference between two subsets
        for (int i = 0; i <= totSum; i++) {
            if (prev[i]) {
                int diff = Math.abs(i - (totSum - i));
                mini = Math.min(mini, diff);
            }
        }
        return mini;
    }

