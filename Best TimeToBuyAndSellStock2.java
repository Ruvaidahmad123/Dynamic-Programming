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
