//recursive
class Solution {
    int profit=0;
    public int f(int idx,int buy,int prices[]){
        if(idx>=prices.length)return 0;
        if(buy==1){
            profit=Math.max(-prices[idx]+f(idx+1,0,prices),0+f(idx+1,1,prices));
        }
        else{
            profit=Math.max(prices[idx]+f(idx+2,1,prices),0+f(idx+1,0,prices));
        }
        return profit;
    }
    public int maxProfit(int[] prices) {
        return f(0,1,prices);
    }
}
//memoization
class Solution {
    int profit=0;
    public int f(int idx,int buy,int prices[],int dp[][]){
        if(idx>=prices.length)return 0;
        if(dp[idx][buy]!=-1)return dp[idx][buy];
        if(buy==1){
            profit=Math.max(-prices[idx]+f(idx+1,0,prices,dp),0+f(idx+1,1,prices,dp));
        }
        else{
            profit=Math.max(prices[idx]+f(idx+2,1,prices,dp),0+f(idx+1,0,prices,dp));
        }
        return dp[idx][buy]=profit;
    }
    public int maxProfit(int[] prices) {
        int dp[][]=new int[prices.length][2];
        for(int rows[]:dp){
            Arrays.fill(rows,-1);
        }
        return f(0,1,prices,dp);
    }
}
//tabulation
static int stockProfit(int[] Arr) {
        int n = Arr.length;
        int dp[][] = new int[n + 2][2];
        
        // Iterate through the array backwards
        for (int ind = n - 1; ind >= 0; ind--) {
            for (int buy = 0; buy <= 1; buy++) {
                int profit = 0;

                if (buy == 0) { // We can buy the stock
                    profit = Math.max(0 + dp[ind + 1][0], -Arr[ind] + dp[ind + 1][1]);
                }

                if (buy == 1) { // We can sell the stock
                    profit = Math.max(0 + dp[ind + 1][1], Arr[ind] + dp[ind + 2][0]);
                }

                dp[ind][buy] = profit;
            }
        }

        // The maximum profit is stored in dp[0][0]
        return dp[0][0];
    }
//space optimized
static int stockProfit(int[] Arr) {
        int n = Arr.length;
        int[] cur = new int[2];
        int[] front1 = new int[2];
        int[] front2 = new int[2];
        
        // Iterate through the array backwards
        for (int ind = n - 1; ind >= 0; ind--) {
            for (int buy = 0; buy <= 1; buy++) {
                int profit = 0;

                if (buy == 0) { // We can buy the stock
                    profit = Math.max(0 + front1[0], -Arr[ind] + front1[1]);
                }

                if (buy == 1) { // We can sell the stock
                    profit = Math.max(0 + front1[1], Arr[ind] + front2[0]);
                }

                cur[buy] = profit;
            }

            // Update front1 and front2 arrays
            System.arraycopy(front1, 0, front2, 0, 2);
            System.arraycopy(cur, 0, front1, 0, 2);
        }

        // The maximum profit is stored in cur[0]
        return cur[0];
    }
