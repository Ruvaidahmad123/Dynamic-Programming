//recursive
class Solution {
    int profit=0;
    public int f(int idx,int buy,int prices[]){
        if(idx==prices.length)return 0;
        if(buy==1){
            profit=Math.max(-prices[idx]+f(idx+1,0,prices),0+f(idx+1,1,prices));  //7 5 3 let 7 par buy kara aur 3 par sell kara profit = 3-7 mtlb jispe buy karte uski negative value lenge aur aage sell(0) karenge ya fir profit kya hoga ki yeh value pe buy nahi karenge aage jayenge waha pe buy karenge in 2 way me jisse bhi max profit aa jaye 
        }
        else{
            profit=Math.max(prices[idx]+f(idx+1,1,prices),0+f(idx+1,0,prices));
            //sell karenge toh woh value pe plus aata +3-7 toh add karenge aur if possible toh aage ab aur buy(1) kar skte ya toh uss value pe sell nahi karenge aage koi value dhundhege sell(0) karne ke liye
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
        if(idx==prices.length)return 0;
        if(dp[idx][buy]!=-1)return dp[idx][buy];
        if(buy==1){
            profit=Math.max(-prices[idx]+f(idx+1,0,prices,dp),0+f(idx+1,1,prices,dp));
        }
        else{
            profit=Math.max(prices[idx]+f(idx+1,1,prices,dp),0+f(idx+1,0,prices,dp));
        }
        return dp[idx][buy]=profit;
    }
    public int maxProfit(int[] prices) {
        int n=prices.length;
        int dp[][]=new int[n][2];
        for(int rows[]:dp){
            Arrays.fill(rows,-1);
        }
        return f(0,1,prices,dp);
    }
}
//tabulation
static long getMaximumProfit(long[] Arr, int n) {
        // Create a 2D array for dynamic programming with dimensions [n+1][2]
        long[][] dp = new long[n + 1][2];
        // Initialize the entire dp table with -1
        for (long[] row : dp) {
            Arrays.fill(row, -1);
        }
        // Base condition: If we have no stocks to buy or sell, profit is 0
        dp[n][0] = dp[n][1] = 0;
        long profit = 0;
        // Iterate through the array in reverse to calculate the maximum profit
        for (int ind = n - 1; ind >= 0; ind--) {
            for (int buy = 0; buy <= 1; buy++) {
                if (buy == 0) { // We can buy the stock
                    profit = Math.max(0 + dp[ind + 1][0], -Arr[ind] + dp[ind + 1][1]);
                }
                if (buy == 1) { // We can sell the stock
                    profit = Math.max(0 + dp[ind + 1][1], Arr[ind] + dp[ind + 1][0]);
                }
                dp[ind][buy] = profit;
            }
        }
        return dp[0][0]; // The maximum profit is stored at dp[0][0]
    }

//spacee optimized
static long getMaximumProfit(long[] Arr, int n) {
        // Create arrays 'ahead' and 'cur' to store the maximum profit ahead and current profit
        long[] ahead = new long[2];
        long[] cur = new long[2];

        // Base condition: If we have no stocks to buy or sell, profit is 0
        ahead[0] = ahead[1] = 0;

        long profit = 0;

        // Iterate through the array in reverse to calculate the maximum profit
        for (int ind = n - 1; ind >= 0; ind--) {
            for (int buy = 0; buy <= 1; buy++) {
                if (buy == 0) { // We can buy the stock
                    profit = Math.max(0 + ahead[0], -Arr[ind] + ahead[1]);
                }

                if (buy == 1) { // We can sell the stock
                    profit = Math.max(0 + ahead[1], Arr[ind] + ahead[0]);
                }
                cur[buy] = profit;
            }

            // Update the 'ahead' array with the current profit values
            System.arraycopy(cur, 0, ahead, 0, 2);
        }
        return cur[0]; // The maximum profit is stored in 'cur[0]'
    }
//greedy approach
//dynamic programming is in tag because previous element is used for current one
class Solution {
    public int maxProfit(int[] prices) {
        int profit=0;
        for(int i=0;i<prices.length-1;i++){
            if(prices[i+1]>prices[i]){
                profit+=(prices[i+1]-prices[i]);
            }
        }
        return profit;
    }
}
