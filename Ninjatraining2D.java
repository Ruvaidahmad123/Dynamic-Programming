//recursive

class Solution {
    public int solve(int day,int arr[][],int last){
        if(day==0){
            int maxi=0;
            for(int i=0;i<=2;i++){
                if(i!=last)
                maxi=Math.max(arr[day][i],maxi);
            }
            return maxi;
        }
        int ans=0;
        for(int i=0;i<=2;i++){
            if(i!=last){
            int points=solve(day-1,arr,i)+arr[day][i];
            ans=Math.max(ans,points);
            }
        }
        return ans;
    }
    public int maximumPoints(int arr[][], int n) {
        return solve(n-1,arr,3);
    }
}
//memoization


// User function Template for Java

class Solution {
    public int solve(int day,int arr[][],int last,int dp[][]){
        if(dp[day][last]!=-1){
            return dp[day][last];
        }
        if(day==0){
            int maxi=0;
            for(int i=0;i<=2;i++){
                if(i!=last)
                maxi=Math.max(arr[0][i],maxi);
            }
            return dp[day][last]=maxi;
        }
        int ans=0;
        for(int i=0;i<=2;i++){
            if(i!=last){
            int points=solve(day-1,arr,i,dp)+arr[day][i];
            ans=Math.max(ans,points);
            }
        }
        return dp[day][last]=ans;
    }
    public int maximumPoints(int arr[][], int n) {
        int dp[][]=new int[n][4];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        return solve(n-1,arr,3,dp);
    }
}
//tabulation
class Solution {
    public int maximumPoints(int arr[][], int n) {
        int dp[][] = new int[n][4];

        // Initializing the base cases
        dp[0][0] = Math.max(arr[0][1], arr[0][2]);
        dp[0][1] = Math.max(arr[0][0], arr[0][2]);
        dp[0][2] = Math.max(arr[0][0], arr[0][1]);
        dp[0][3] = Math.max(arr[0][0], Math.max(arr[0][1], arr[0][2]));

        for (int day = 1; day < n; day++) {
            for (int last = 0; last < 4; last++) {
                dp[day][last] = 0;                                 //day pe jaao uss day me 4 possibility(0,1,2,3) ho skti h last hone ki har ek possibility ko previous wale day ke saath
                                                                    //add karke update kardo
                for (int i = 0; i <= 2; i++) {
                    if (i != last) {
                        dp[day][last] = Math.max(dp[day][last], arr[day][i] + dp[day - 1][i]);
                    }
                }
            }
        }

        return dp[n - 1][3];    //recursion call hamne banayi thi solve(n-1,3)
    }
}
