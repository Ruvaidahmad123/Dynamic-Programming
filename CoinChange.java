//recursive
class Solution {
    public int f(int idx,int coins[],int target){
        if(idx==0){
            if(target%coins[idx]==0)return target/coins[idx];
            else return Integer.MAX_VALUE;
        }
        int notpick=0+f(idx-1,coins,target);
        int pick=Integer.MAX_VALUE;
        if(coins[idx]<=target){
            int result = f(idx, coins, target - coins[idx]);
            if (result != Integer.MAX_VALUE) {
                pick = 1 + result;
            }
        }
        return Math.min(pick,notpick);
    }
    public int coinChange(int[] coins, int amount) {
        int n=coins.length;
        int ans=f(n-1,coins,amount);
        if(ans==Integer.MAX_VALUE)return -1;
        return ans;
    }
}
//memoization
class Solution {
    public int f(int idx,int coins[],int target,int dp[][]){
        if(idx==0){
            if(target%coins[idx]==0)return target/coins[idx];
            else return Integer.MAX_VALUE;
        }
        if(dp[idx][target]!=-1){
            return dp[idx][target];
        }
        int notpick=0+f(idx-1,coins,target,dp);
        int pick=Integer.MAX_VALUE;
        if(coins[idx]<=target){
            int result = f(idx, coins, target - coins[idx],dp);
            if (result != Integer.MAX_VALUE) {
                pick = 1 + result;
            }
        }
        return dp[idx][target]=Math.min(pick,notpick);
    }
    public int coinChange(int[] coins, int amount) {
        int n=coins.length;
        int dp[][]=new int[n][amount+1];
        for(int rows[]:dp){
            Arrays.fill(rows,-1);
        }
        int ans=f(n-1,coins,amount,dp);
        if(ans==Integer.MAX_VALUE)return -1;
        return ans;
    }
}
//tabulation
class Solution {
    public int coinChange(int[] coins, int amount) {
        int n=coins.length;
        int dp[][]=new int[n][amount+1];
        for(int rows[]:dp){
            Arrays.fill(rows,-1);
        }
        for(int trg=0;trg<=amount;trg++){
            if(trg%coins[0]==0){
                dp[0][trg]=trg/coins[0];
            }
            else dp[0][trg]=Integer.MAX_VALUE;
        }
        for(int i=1;i<n;i++){
            for(int target=0;target<=amount;target++){
                int notpick=dp[i-1][target];
                int pick=Integer.MAX_VALUE;
                if(coins[i]<=target){
                    int result = dp[i][target-coins[i]];
                    if (result != Integer.MAX_VALUE) {
                        pick = 1 + result;
                    }
                }
                dp[i][target]=Math.min(pick,notpick);
            }
        }
        int result=dp[n-1][amount];
        return (result == Integer.MAX_VALUE) ? -1 : result;
    }
}
//space optimized
class Solution {
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[] prev = new int[amount + 1];
        int[] curr = new int[amount + 1];    
        // Initialize the base case for the first row
        for (int trg = 0; trg <= amount; trg++) {
            if (trg % coins[0] == 0) {
                prev[trg] = trg / coins[0];
            } else {
                prev[trg] = Integer.MAX_VALUE;
            }
        }
        // Fill the rest of the dp table
        for (int i = 1; i < n; i++) {
            for (int target = 0; target <= amount; target++) {
                int notpick = prev[target];
                int pick = Integer.MAX_VALUE;
                if (coins[i] <= target) {
                    int result = curr[target - coins[i]];
                    if (result != Integer.MAX_VALUE) {
                        pick = 1 + result;
                    }
                }
                curr[target] = Math.min(pick, notpick);
            }
            // Move current row to previous row for the next iteration
            prev = curr.clone();
        }
        int result = prev[amount];
        return (result == Integer.MAX_VALUE) ? -1 : result;
    }
}
