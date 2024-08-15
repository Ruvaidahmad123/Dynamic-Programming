//recursive
class Solution {
    int profit=0;
    public int f(int idx,int buy,int prices[],int count){
        if(idx==prices.length||count==0)return 0;
        if(buy==1){
            profit=Math.max(-prices[idx]+f(idx+1,0,prices,count),0+f(idx+1,1,prices,count));
        }
        else{
            profit=Math.max(prices[idx]+f(idx+1,1,prices,count-1),0+f(idx+1,0,prices,count));
        }
        return profit;
    }
    public int maxProfit(int[] prices) {
        int count=0;
        int dp[][]=new int[prices.length][2];
        for(int rows[]:dp){
            Arrays.fill(rows,-1);
        }
        return f(0,1,prices,2);
    }
}
//memoization
class Solution {
    int profit=0;
    public int f(int idx,int buy,int prices[],int count,int dp[][][]){
        if(idx==prices.length || count==0 )return 0;
        if(dp[idx][buy][count]!=-1)return dp[idx][buy][count];
        if(buy==1){
            profit=Math.max(-prices[idx]+f(idx+1,0,prices,count,dp),0+f(idx+1,1,prices,count,dp));
        }
        else{
            profit=Math.max(prices[idx]+f(idx+1,1,prices,count-1,dp),0+f(idx+1,0,prices,count,dp));
        }
        return dp[idx][buy][count]=profit;
    }
    public int maxProfit(int[] prices) {
        int count=0;
        int dp[][][]=new int[prices.length][2][3];
        for(int rows[][]:dp){
            for(int inner[]:rows)
            Arrays.fill(inner,-1);
        }
        return f(0,1,prices,2,dp);
    }
}
//tabulation
class StockProfit {
    static int maxProfit(int[] prices) {
        int n = prices.length;
        // Creating a 3D dp array of size [n+1][2][3] initialized to 0
        int[][][] dp = new int[n + 1][2][3];
        // Loop through the dp array, starting from the second last stock (ind=n-1)
        for (int ind = n - 1; ind >= 0; ind--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= 2; cap++) {
                    if (buy == 0) { // We can buy the stock
                        dp[ind][buy][cap] = Math.max(0 + dp[ind + 1][0][cap],
                                -prices[ind] + dp[ind + 1][1][cap]);
                    }
                    if (buy == 1) { // We can sell the stock
                        dp[ind][buy][cap] = Math.max(0 + dp[ind + 1][1][cap],
                                prices[ind] + dp[ind + 1][0][cap - 1]);
                    }
                }
            }
        }
        // The maximum profit with 2 transactions is stored in dp[0][0][2]
        return dp[0][0][2];
    }
}
//space optimized
static int maxProfit(int[] prices) {
        int n = prices.length;

        // Create a 2D array 'ahead' and 'cur' to store profit values
        int[][] ahead = new int[2][3];
        int[][] cur = new int[2][3];

        // Loop through the prices array, starting from the second last stock (ind=n-1)
        for (int ind = n - 1; ind >= 0; ind--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= 2; cap++) {

                    if (buy == 0) { // We can buy the stock
                        cur[buy][cap] = Math.max(0 + ahead[0][cap],
                                -prices[ind] + ahead[1][cap]);
                    }

                    if (buy == 1) { // We can sell the stock
                        cur[buy][cap] = Math.max(0 + ahead[1][cap],
                                prices[ind] + ahead[0][cap - 1]);
                    }
                }
            }

            // Update 'ahead' with the values in 'cur'
            for (int i = 0; i < 2; i++) {
                for (int j = 1; j < 3; j++) {
                    ahead[i][j] = cur[i][j];
                }
            }
        }

        // The maximum profit with 2 transactions is stored in ahead[0][2]
        return ahead[0][2];
    }
