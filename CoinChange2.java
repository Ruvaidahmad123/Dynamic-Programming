//recursive
class Solution {
    public int f(int idx,int coins[],int target){
        if(idx==0){
            if(target%coins[idx]==0)return 1;
            else return 0;
        }
        int notpick=f(idx-1,coins,target);
        int pick=0;
        if(coins[idx]<=target){
            pick = f(idx, coins, target - coins[idx]);
        }
        return pick+notpick;
    }
    public int change(int amount, int[] coins) {
        int n=coins.length;
        return f(n-1,coins,amount);
    }
}
//memoized
class Solution {
    public int f(int idx,int coins[],int target,int dp[][]){
        if(idx==0){
            if(target%coins[idx]==0)return 1;
            else return 0;
        }
        if(dp[idx][target]!=-1)return dp[idx][target];
        int notpick=f(idx-1,coins,target,dp);
        int pick=0;
        if(coins[idx]<=target){
            pick = f(idx, coins, target - coins[idx],dp);
        }
        return dp[idx][target]=pick+notpick;
    }
    public int change(int amount, int[] coins) {
        int n=coins.length;
        int dp[][]=new int[n][amount+1];
        for(int rows[]:dp){
            Arrays.fill(rows,-1);
        }
        return f(n-1,coins,amount,dp);
    }
}
//tabulated
class Solution {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];
        // Base case: if target is 0, there is one way to make the amount (use no coins)
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }
        // Initialize the dp array for the first coin
        for (int t = 1; t <= amount; t++) {
            if (t % coins[0] == 0) {
                dp[0][t] = 1;
            } else {
                dp[0][t] = 0;
            }
        }
        // Fill the dp array using a bottom-up approach
        for (int i = 1; i < n; i++) {
            for (int t = 1; t <= amount; t++) {
                int notPick = dp[i - 1][t];
                int pick = 0;
                if (coins[i] <= t) {
                    pick = dp[i][t - coins[i]];
                }
                dp[i][t] = pick + notPick;
            }
        }
        return dp[n - 1][amount];
    }
}
//space optimized
class Solution {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[] prev = new int[amount + 1];
        int[] curr = new int[amount + 1];
        // Base case: if target is 0, there is one way to make the amount (use no coins)
        prev[0] = 1;
        for (int t = 1; t <= amount; t++) {
            if (t % coins[0] == 0) {
                prev[t] = 1;
            } else {
                prev[t] = 0;
            }
        }
        // Fill the dp array using a bottom-up approach
        for (int i = 1; i < n; i++) {
            curr[0] = 1; // There is always one way to make the amount 0, which is by using no coins
            for (int t = 1; t <= amount; t++) {
                int notPick = prev[t];
                int pick = 0;
                if (coins[i] <= t) {
                    pick = curr[t - coins[i]];
                }
                curr[t] = pick + notPick;
            }
            // Update prev array to be the current array for the next iteration
            prev = curr.clone();
        }
        return prev[amount];
    }
}
