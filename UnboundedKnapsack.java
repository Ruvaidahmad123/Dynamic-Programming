//recursive
class Solution{
    public static int f(int idx,int target,int val[],int wt[]){
        if(idx==0){
            if(wt[0]<=target)return ((target/wt[0])*val[0]);
            else return 0;
        }
        int notpick=f(idx-1,target,val,wt);
        int pick=Integer.MIN_VALUE;
        if(wt[idx]<=target){
            pick=val[idx]+f(idx,target-wt[idx],val,wt); //REMEMBER   to take it's value multiple times we didn't do idx-1
        }
        return Math.max(pick,notpick);
    }
    static int knapSack(int N, int W, int val[], int wt[])
    {
        return f(N-1,W,val,wt);
    }
}
//memoization
class Solution{
    public static int f(int idx,int target,int val[],int wt[],int dp[][]){
        if(idx==0){
            if(wt[0]<=target)return ((target/wt[0])*val[0]);
            else return 0;
        }
        if(dp[idx][target]!=-1)return dp[idx][target];
        int notpick=f(idx-1,target,val,wt,dp);
        int pick=Integer.MIN_VALUE;
        if(wt[idx]<=target){
            pick=val[idx]+f(idx,target-wt[idx],val,wt,dp);
        }
        return dp[idx][target]=Math.max(pick,notpick);
    }
    static int knapSack(int N, int W, int val[], int wt[])
    {   
        int dp[][]=new int[N][W+1];
        for(int rows[]:dp){
            Arrays.fill(rows,-1);
        }
        return f(N-1,W,val,wt,dp);
    }
}
//tabulation
class Solution {
    static int knapSack(int N, int W, int val[], int wt[]) {
        // Create a 2D DP array where dp[i][j] represents the maximum value
        // achievable with the first i items and a knapsack capacity of j
        int[][] dp = new int[N][W + 1];

        // Initialize the first row (considering only the first item)
        for (int j = 0; j <= W; j++) {
            if (wt[0] <= j) {
                dp[0][j] = (j / wt[0]) * val[0];
            }
        }
        // Fill the DP array
        for (int i = 1; i < N; i++) {
            for (int j = 0; j <= W; j++) {
                // Not picking the current item
                int notPick = dp[i - 1][j];
                // Picking the current item
                int pick = Integer.MIN_VALUE;
                if (wt[i] <= j) {
                    pick = val[i] + dp[i][j - wt[i]];
                }
                // Store the maximum value of picking or not picking the current item
                dp[i][j] = Math.max(pick, notPick);
            }
        }
        // The answer will be in dp[N-1][W], which represents the maximum value
        // for a knapsack of capacity W considering all N items
        return dp[N - 1][W];
    }
}
//space optimized
class Solution {
    static int knapSack(int N, int W, int val[], int wt[]) {
        // Create two 1D DP arrays, prev and curr
        int[] prev = new int[W + 1];
        int[] curr = new int[W + 1];
        // Initialize the prev array for the first item
        for (int j = 0; j <= W; j++) {
            if (wt[0] <= j) {
                prev[j] = (j / wt[0]) * val[0];
            }
        }
        // Fill the DP array
        for (int i = 1; i < N; i++) {
            for (int j = 0; j <= W; j++) {
                int notPick = prev[j];
                int pick = Integer.MIN_VALUE;
                if (wt[i] <= j) {
                    pick = val[i] + curr[j - wt[i]];
                }
                curr[j] = Math.max(pick, notPick);
            }
            // Copy current row to previous row for the next iteration
            System.arraycopy(curr, 0, prev, 0, W + 1);
        }
        // The answer will be in prev[W], which represents the maximum value
        // for a knapsack of capacity W considering all N items
        return prev[W];
    }
}
//further space optimization
static int unboundedKnapsack(int n, int W, int[] val, int[] wt) {
        // Create an array to store the maximum value for each capacity from 0 to W
        int cur[] = new int[W + 1];
        // Base condition: Initialize the cur array for the first item
        for (int i = wt[0]; i <= W; i++) {
            cur[i] = ((int) i / wt[0]) * val[0];
        }
        // Fill the cur array using dynamic programming
        for (int ind = 1; ind < n; ind++) {
            for (int cap = 0; cap <= W; cap++) {
                // Calculate the maximum value when the current item is not taken
                int notTaken = cur[cap];
                // Initialize the maximum value when the current item is taken as the minimum integer value
                int taken = Integer.MIN_VALUE;
                // If the weight of the current item is less than or equal to the current capacity (cap),
                // calculate the maximum value when the current item is taken
                if (wt[ind] <= cap)
                    taken = val[ind] + cur[cap - wt[ind]];
                // Store the result in the cur array
                cur[cap] = Math.max(notTaken, taken);
            }
        }
        return cur[W]; // Return the maximum value that can be obtained with the given capacity W
    }
