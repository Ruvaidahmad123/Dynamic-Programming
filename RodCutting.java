//recursion
class Solution{
    public int f(int n,int price[],int target){
        if(n==1){
            if(n<=target){
                return target*price[0];
            }
            else return 0;
        }
        int notpick=f(n-1,price,target);
        int pick=Integer.MIN_VALUE;
        if(n<=target){
            pick=price[n-1]+f(n,price,target-n); //same value can be taken multiple times thats why n not n-1
        }
        return Math.max(pick,notpick);
    }
    public int cutRod(int price[], int n) {
        int target=n;
        return f(n,price,target);
    }
}
//memoization
class Solution{
    public int f(int n,int price[],int target,int dp[][]){
        if(n==1){
            if(n<=target){
                return target*price[0];
            }
            else return 0;
        }
        if(dp[n][target]!=-1)return dp[n][target];
        int notpick=f(n-1,price,target,dp);
        int pick=Integer.MIN_VALUE;
        if(n<=target){
            pick=price[n-1]+f(n,price,target-n,dp);
        }
        return dp[n][target]=Math.max(pick,notpick);
    }
    public int cutRod(int price[], int n) {
        int target=n;
        int dp[][]=new int[n+1][target+1];
        for(int rows[]:dp){
            Arrays.fill(rows,-1);
        }
        return f(n,price,target,dp);
    }
}
//tabulation
class Solution {
    public int cutRod(int price[], int n) {
        // Create a DP array where dp[i][j] represents the maximum value
        // obtainable by cutting up a rod of length j using the first i pieces
        int[][] dp = new int[n + 1][n + 1];
        // Initialize the dp array for the first piece
        for (int j = 0; j <= n; j++) {
            dp[0][j] = (j / 1) * price[0];
        }
        // Fill the DP array
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= n; j++) {
                int notPick = dp[i - 1][j];
                int pick = Integer.MIN_VALUE;
                if (i + 1 <= j) {
                    pick = price[i] + dp[i][j - (i + 1)];
                }
                dp[i][j] = Math.max(pick, notPick);
            }
        }
        // The answer will be in dp[n-1][n], which represents the maximum value
        // for a rod of length n considering all n pieces
        return dp[n - 1][n];
    }
}
//space optimized
class Solution {
    public int cutRod(int price[], int n) {
        // Create two 1D DP arrays, prev and curr
        int[] prev = new int[n + 1];
        int[] curr = new int[n + 1];
        // Initialize the prev array for the first piece
        for (int j = 0; j <= n; j++) {
            prev[j] = j * price[0];
        }
        // Fill the DP arrays
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= n; j++) {
                int notPick = prev[j];
                int pick = Integer.MIN_VALUE;
                if (i + 1 <= j) {
                    pick = price[i] + curr[j - (i + 1)];
                }
                curr[j] = Math.max(pick, notPick);
            }
            // Copy current row to previous row for the next iteration
            System.arraycopy(curr, 0, prev, 0, n + 1);
        }
        // The answer will be in prev[n], which represents the maximum value
        // for a rod of length n
        return prev[n];
    }
}
//further space optimized
class Solution {
    public int cutRod(int price[], int n) {
        // Create a 1D DP array where dp[j] represents the maximum value
        // obtainable by cutting up a rod of length j
        int[] dp = new int[n + 1];

        // Initialize the dp array for the first piece
        for (int j = 0; j <= n; j++) {
            dp[j] = j * price[0];
        }
        // Fill the DP array
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= n; j++) {
                if (i + 1 <= j) {
                    dp[j] = Math.max(dp[j], price[i] + dp[j - (i + 1)]);
                }
            }
        }
        // The answer will be in dp[n], which represents the maximum value
        // for a rod of length n
        return dp[n];
    }
}
