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
    public int maxProfit(int k, int[] prices) {
        int count=0;
        return f(0,1,prices,k);
    }
}
//memoization
class Solution {
    int profit=0;
    public int f(int idx,int buy,int prices[],int count,int dp[][][]){
        if(idx==prices.length||count==0)return 0;
        if(dp[idx][buy][count]!=-1)return dp[idx][buy][count];
        if(buy==1){
            profit=Math.max(-prices[idx]+f(idx+1,0,prices,count,dp),0+f(idx+1,1,prices,count,dp));
        }
        else{
            profit=Math.max(prices[idx]+f(idx+1,1,prices,count-1,dp),0+f(idx+1,0,prices,count,dp));
        }
        return dp[idx][buy][count]=profit;
    }
    public int maxProfit(int k, int[] prices) {
        int count=0;
        int dp[][][]=new int[prices.length][2][k+1];
        for(int rows[][]:dp){
            for(int inner[]:rows){
                Arrays.fill(inner,-1);
            }
        }
        return f(0,1,prices,k,dp);
    }
}
//tabulation
public class StockBuySell {
    
    // Function to calculate the maximum profit
    public static int maximumProfit(int[] prices, int n, int k) {
        // Creating a 3D array dp of size [n+1][2][k+1] initialized to 0
        int[][][] dp = new int[n + 1][2][k + 1];
        
        // As dp array is initialized to 0, we have already covered the base case
        
        // Iterate through the array and fill in the dp array
        for (int ind = n - 1; ind >= 0; ind--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= k; cap++) {
                    if (buy == 0) { // We can buy the stock
                        dp[ind][buy][cap] = Math.max(0 + dp[ind + 1][0][cap], 
                                -prices[ind] + dp[ind + 1][1][cap]);
                    } else { // We can sell the stock
                        dp[ind][buy][cap] = Math.max(0 + dp[ind + 1][1][cap],
                                prices[ind] + dp[ind + 1][0][cap - 1]);
                    }
                }
            }
        }
        
        return dp[0][0][k];
    }
  //space optimization
public static int maxProfit(int[] prices, int n, int k) {
        // Create two 2D arrays: 'ahead' and 'cur' to store intermediate results
        int[][] ahead = new int[2][k + 1];
        int[][] cur = new int[2][k + 1];
        
        // Iterate through the array backwards
        for (int ind = n - 1; ind >= 0; ind--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= k; cap++) {
                    if (buy == 0) { // We can buy the stock
                        cur[buy][cap] = Math.max(0 + ahead[0][cap], 
                                -prices[ind] + ahead[1][cap]);
                    } else { // We can sell the stock
                        cur[buy][cap] = Math.max(0 + ahead[1][cap],
                                prices[ind] + ahead[0][cap - 1]);
                    }
                }
            }
            // Update the 'ahead' array with the values from 'cur'
            for (int i = 0; i < 2; i++) {
                System.arraycopy(cur[i], 0, ahead[i], 0, k + 1);
            }
        }
        
        return ahead[0][k];
    }
