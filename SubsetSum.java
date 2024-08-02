//recursive
class Solution{
    public static boolean f(int idx,int arr[],int target){
        if(target==0)return true;
        if(idx==0) return arr[0]==target;
        boolean nottake=f(idx-1,arr,target);
        boolean take=false;
        if(arr[idx]<=target){
            take=f(idx-1,arr,target-arr[idx]);
        }
        return take || nottake;
    }
    static Boolean isSubsetSum(int n, int arr[], int sum){
        return f(n-1,arr,sum);
    }
}
//memoization
class Solution{
    public static boolean f(int idx,int arr[],int target,int dp[][]){
        if(target==0)return true;
        if(idx==0) return arr[0]==target;
        if (dp[idx][target] != -1){
            return dp[idx][target] == 0 ? false : true;
        }
        boolean nottake=f(idx-1,arr,target,dp);
        boolean take=false;
        if(arr[idx]<=target){
            take=f(idx-1,arr,target-arr[idx],dp);
        }
        dp[idx][target] = nottake || take ? 1 : 0;
        return nottake || take;
    }
    static Boolean isSubsetSum(int n, int arr[], int sum){
        int dp[][]=new int[n][sum+1];
        for(int rows[]:dp){
            Arrays.fill(rows,-1);
        }
        return f(n-1,arr,sum,dp);
    }
}
//tabulation
static boolean subsetSumToK(int n, int k, int[] arr) {
        // Create a boolean DP table with dimensions [n][k+1]
        boolean dp[][] = new boolean[n][k + 1];
        
        // Initialize the first row of the DP table
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }

        // Initialize the first column of the DP table
        if (arr[0] <= k) {
            dp[0][arr[0]] = true;
        }

        // Fill in the DP table using bottom-up approach
        for (int ind = 1; ind < n; ind++) {
            for (int target = 1; target <= k; target++) {
                // Calculate if the current target can be achieved without taking the current element
                boolean notTaken = dp[ind - 1][target];
                
                // Calculate if the current target can be achieved by taking the current element
                boolean taken = false;
                if (arr[ind] <= target) {
                    taken = dp[ind - 1][target - arr[ind]];
                }
                
                // Store the result in the DP table
                dp[ind][target] = notTaken || taken;
            }
        }

        // The final result is stored in the bottom-right cell of the DP table
        return dp[n - 1][k];
    }
//space optimized
static boolean subsetSumToK(int n, int k, int[] arr) {
        // Create an array to store the previous row of the DP table
        boolean prev[] = new boolean[k + 1];
        
        // Initialize the first row of the DP table
        prev[0] = true;

        // Initialize the first column of the DP table
        if (arr[0] <= k) {
            prev[arr[0]] = true;
        }

        // Fill in the DP table using bottom-up approach
        for (int ind = 1; ind < n; ind++) {
            // Create an array to store the current row of the DP table
            boolean cur[] = new boolean[k + 1];
            
            // Initialize the first column of the current row
            cur[0] = true;
            
            for (int target = 1; target <= k; target++) {
                // Calculate if the current target can be achieved without taking the current element
                boolean notTaken = prev[target];
                
                // Calculate if the current target can be achieved by taking the current element
                boolean taken = false;
                if (arr[ind] <= target) {
                    taken = prev[target - arr[ind]];
                }
                
                // Store the result in the current row of the DP table
                cur[target] = notTaken || taken;
            }
            
            // Update the previous row with the current row
            prev = cur;
        }

        // The final result is stored in the last cell of the previous row
        return prev[k];
    }
