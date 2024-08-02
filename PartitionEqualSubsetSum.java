//recursive
class Solution {
    public boolean canPartitionHelper(int idx, int[] nums, int target) {
        // Base cases
        if(idx==0)return target==nums[0];
        if(target==0)return true;
        // Recursively explore the two possibilities:
        // 1. Exclude the current element and move to the next
        boolean notPick = canPartitionHelper(idx - 1, nums, target);
        // 2. Include the current element and reduce the target by the current element's value
        boolean pick = canPartitionHelper(idx - 1, nums, target - nums[idx]);
        // Return true if any of the above possibilities is true
        return notPick || pick;
    }
    // Main function to check if the array can be partitioned into two subsets with equal sum
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = Arrays.stream(nums).sum();
        // If the total sum is odd, it's impossible to partition the array into two equal subsets
        if (sum % 2 != 0) {
            return false;
        }
        // The target sum for each subset
        int target = sum / 2;
        // Start the recursive function from the last index
        return canPartitionHelper(n - 1, nums, target);
    }
}

//memoized
class Solution {
    public boolean f(int idx,int nums[],int dp[][],int target){
        if(idx==0)return target==nums[0];
        if(target==0)return true;
        if(dp[idx][target]!=-1){
            return dp[idx][target]==0?false:true;
        }
        boolean notpick=f(idx-1,nums,dp,target);
        boolean pick=false;
        if(nums[idx]<=target){
            pick=f(idx-1,nums,dp,target-nums[idx]);
        }
        dp[idx][target]=pick||notpick?1:0;
        return pick||notpick;
    }
    public boolean canPartition(int[] nums) {
        int n=nums.length;
        int sum = Arrays.stream(nums).sum();
        if(sum%2!=0){
            return false;
        }
        int dp[][]=new int[n][sum/2+1];
        for(int rows[]:dp){
            Arrays.fill(rows,-1);
        }
        return f(n-1,nums,dp,sum/2);
    }
}
//tabulated
class Solution {
   public boolean canPartition(int[] arr) {
        // Calculate the total sum of the array elements
        int n=arr.length;
        int totSum = 0;
        for (int i = 0; i < n; i++) {
            totSum += arr[i];
        }
        // If the total sum is odd, it cannot be partitioned into equal subsets
        if (totSum % 2 == 1) 
            return false;
        else {
            // Calculate the target sum for each subset
            int k = totSum / 2;
            // Create a DP table to store the results of subproblems
            boolean dp[][] = new boolean[n][k + 1];

            // Initialize the first row of the DP table
            for (int i = 0; i < n; i++) {
                dp[i][0] = true;
            }
            // Initialize the first column of the DP table
            if (arr[0] <= k) {
                dp[0][arr[0]] = true;
            }
            // Fill in the DP table using bottom-up dynamic programming
            for (int ind = 1; ind < n; ind++) {
                for (int target = 1; target <= k; target++) {
                    // Calculate if the current element is not taken
                    boolean notTaken = dp[ind - 1][target];
                    // Calculate if the current element is taken
                    boolean taken = false;
                    if (arr[ind] <= target) {
                        taken = dp[ind - 1][target - arr[ind]];
                    }
                    // Update the DP table for the current element and target sum
                    dp[ind][target] = notTaken || taken;
                }
            }
            // The result is stored in the last cell of the DP table
            return dp[n - 1][k];
        }
    }
}
//space optimized
class Solution {
   static boolean canPartition(int[] arr) {
        // Calculate the total sum of the array elements
        int n= arr.length;
        int totSum = 0;
        for (int i = 0; i < n; i++) {
            totSum += arr[i];
        }
        // If the total sum is odd, it cannot be partitioned into equal subsets
        if (totSum % 2 == 1)
            return false;
        else {
            // Calculate the target sum for each subset
            int k = totSum / 2;
            // Create two arrays to store the DP results for the previous and current rows
            boolean prev[] = new boolean[k + 1];
            // Initialize the first row of the DP table
            prev[0] = true;
            // Initialize the first column of the DP table
            if (arr[0] <= k) {
                prev[arr[0]] = true;
            }
            // Fill in the DP table using bottom-up dynamic programming
            for (int ind = 1; ind < n; ind++) {
                boolean cur[] = new boolean[k + 1];
                for (int target = 1; target <= k; target++) {
                    // Calculate if the current element is not taken
                    boolean notTaken = prev[target];
                    // Calculate if the current element is taken
                    boolean taken = false;
                    if (arr[ind] <= target) {
                        taken = prev[target - arr[ind]];
                    }
                    // Update the DP table for the current element and target sum
                    cur[target] = notTaken || taken;
                }
                // Update the previous row with the current row for the next iteration
                prev = cur;
            }
            // The result is stored in the last cell of the DP table
            return prev[k];
        }
    }
}
