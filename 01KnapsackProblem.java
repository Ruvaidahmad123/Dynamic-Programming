//recursive
class Solution{
  int f(int idx,int wt,int arr[],int val[]){
    if(idx==0){
      if(arr[0]<=wt)
        return val[0];
      else
        return 0;
  }
    int notpick=0+f(idx-1,wt,arr,val);
    int pick=Integer.MIN_VALUE;
    if(arr[idx]<=wt){
      pick=val[idx]+f(idx-1,wt-arr[idx],arr,val);
    }
    return Math.max(pick,notpick);
  }
  int knapsack(int wt,int arr[],int n,int val[]){
    return f(n-1,wt,arr,val);
  }
}
//memoized
class Solution{
  int f(int idx,int wt,int arr[],int val[],int dp[][]){
    if(idx==0){
      if(arr[0]<=wt)
        return val[0];
      else
        return 0;
  }
    if(dp[idx][wt]!=-1)return dp[idx][wt];
    int notpick=0+f(idx-1,wt,arr,val,dp);
    int pick=Integer.MIN_VALUE;
    if(arr[idx]<=wt){
      pick=val[idx]+f(idx-1,wt-arr[idx],arr,val,dp);
    }
    return dp[idx][wt]=Math.max(pick,notpick);
  }
  int knapsack(int wt,int arr[],int n,int val[]){
    int dp[][]=new int[n][wt+1];
    for(int rows[]:dp){
      Arrays.fill(rows,-1);
    }
    return f(n-1,wt,arr,val,dp);
  }
}
//tabulated
class Solution {
    public int knapsack(int W, int[] wt, int[] val, int n) {
        int[][] dp = new int[n][W + 1];
        // Initialize the dp array for the base case
        for (int i = wt[0]; i <= W; i++) {
            dp[0][i] = val[0];
        }
        // Fill the dp array iteratively
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= W; j++) {
                int notpick = dp[i - 1][j];
                int pick = Integer.MIN_VALUE;
                if (wt[i] <= j) {
                    pick = val[i] + dp[i - 1][j - wt[i]];
                }
                dp[i][j] = Math.max(pick, notpick);
            }
        }
        return dp[n - 1][W];
    }
}
//space optimized
class Solution {
    public int knapsack(int W, int[] wt, int[] val, int n) {
        int[] prev = new int[W + 1];
        int[] curr = new int[W + 1];
        // Initialize the prev array for the base case
        for (int i = wt[0]; i <= W; i++) {
            prev[i] = val[0];
        }
        // Fill the dp array iteratively using prev and curr arrays
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= W; j++) {
                int notpick = prev[j];
                int pick = Integer.MIN_VALUE;
                if (wt[i] <= j) {
                    pick = val[i] + prev[j - wt[i]];
                }
                curr[j] = Math.max(pick, notpick);
            }
            // Swap prev and curr arrays for the next iteration
            prev = Arrays.copyOf(curr, W + 1);
        }
        return prev[W];
    }
}
//further more space optimized
class Solution {
    public int knapsack(int W, int[] wt, int[] val, int n) {
        int[] dp = new int[W + 1];

        // Initialize the dp array for the base case
        for (int i = wt[0]; i <= W; i++) {
            dp[i] = val[0];
        }

        // Fill the dp array iteratively
        for (int i = 1; i < n; i++) {
            for (int j = W; j >= 0; j--) {
                if (wt[i] <= j) {
                    dp[j] = Math.max(dp[j], val[i] + dp[j - wt[i]]);
                }
            }
        }

        return dp[W];
    }
}
// Initialization:

// We use a single 1D array dp to represent the maximum value that can be obtained for each weight capacity up to W.
// Iterative DP Transition:

// We initialize the dp array for the first item.
// We iterate over each item and each possible weight capacity.
// For each item, we iterate backward over the weight capacities. This ensures that we do not overwrite the values we still need to use for the current iteration.
// For each weight capacity j, we update dp[j] to be the maximum of:
// The value without picking the current item (dp[j]).
// The value of picking the current item (val[i] + dp[j - wt[i]]), provided the current item's weight is less than or equal to the current weight capacity j.
// Result:s

// The result is found in dp[W], which represents the maximum value that can be obtained using all items with the full weight capacity W.
// This approach further optimizes the space complexity to 
// O(W) while maintaining the time complexity of 
// O(n×W).
