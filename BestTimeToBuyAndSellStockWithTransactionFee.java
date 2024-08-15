//recursive
class Solution {
     int profit=0;
    public int f(int idx,int buy,int prices[],int fee){
        if(idx==prices.length)return 0;
        if(buy==1){
            profit=Math.max(-prices[idx]+f(idx+1,0,prices,fee),0+f(idx+1,1,prices,fee));
        }
        else{
            profit=Math.max(prices[idx]-fee+f(idx+1,1,prices,fee),0+f(idx+1,0,prices,fee));
        }
        return profit;
    }
    public int maxProfit(int[] prices, int fee) {
        return f(0,1,prices,fee);
    }
}
//memoization
class Solution {
     int profit=0;
    public int f(int idx,int buy,int prices[],int fee,int dp[][]){
        if(idx==prices.length)return 0;
        if(dp[idx][buy]!=-1)return dp[idx][buy];
        if(buy==1){
            profit=Math.max(-prices[idx]+f(idx+1,0,prices,fee,dp),0+f(idx+1,1,prices,fee,dp));
        }
        else{
            profit=Math.max(prices[idx]-fee+f(idx+1,1,prices,fee,dp),0+f(idx+1,0,prices,fee,dp));
        }
        return dp[idx][buy]=profit;
    }
    public int maxProfit(int[] prices, int fee) {
        int dp[][]=new int[prices.length][2];
        for(int rows[]:dp){
            Arrays.fill(rows,-1);
        }
        return f(0,1,prices,fee,dp);
    }
}
//tabulation
static int maximumProfit(int n, int fee, int[] Arr) {
        // Handle the base case when n is 0
        if (n == 0) {
            return 0;
        }
        int dp[][] = new int[n + 1][2];
        // Iterate through the array backwards
        for (int ind = n - 1; ind >= 0; ind--) {
            for (int buy = 0; buy <= 1; buy++) {
                int profit = 0;
                if (buy == 0) { // We can buy the stock
                    profit = Math.max(0 + dp[ind + 1][0], -Arr[ind] + dp[ind + 1][1]);
                }
                if (buy == 1) { // We can sell the stock
                    profit = Math.max(0 + dp[ind + 1][1], Arr[ind] - fee + dp[ind + 1][0]);
                }
                dp[ind][buy] = profit;
            }
        }
        return dp[0][0];
    }
//space optimized
static long maximumProfit(int n, int fee, int[] Arr) {
        // Handle the base case when n is 0
        if (n == 0) {
            return 0;
        }

        long ahead[] = new long[2];
        long cur[] = new long[2];
        
        // Initialize base conditions
        ahead[0] = ahead[1] = 0;
        long profit = 0;

        for (int ind = n - 1; ind >= 0; ind--) {
            for (int buy = 0; buy <= 1; buy++) {
                if (buy == 0) { // We can buy the stock
                    profit = Math.max(0 + ahead[0], -Arr[ind] + ahead[1]);
                }

                if (buy == 1) { // We can sell the stock
                    profit = Math.max(0 + ahead[1], Arr[ind] - fee + ahead[0]);
                }
                cur[buy] = profit;
            }

            ahead = (long[]) (cur.clone());
        }
        return cur[0];
    }
