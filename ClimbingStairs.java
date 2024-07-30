// Points to remember:
// Step1. Identify a DP Problem.
// Step2. To solve the problem after identification.
//    1. Try to represent the given problem in terms of index.
//    2. Do all possible operations on that index according to the problem statement.
//    3. To count all possible ways - sum of all stuff.
//         To find minimum/maximum - Take Minimum/maximum of all stuff.
class Solution {
    public int climbStairs(int n) {
        if(n<=2){
            return n; 
        }
        int dp[]=new int[n+1];
        dp[0]=0;
        dp[1]=1;
        dp[2]=2;
        for(int i=3;i<=n;i++){
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n];
    }
}
