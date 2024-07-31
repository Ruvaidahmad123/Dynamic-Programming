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

//spaceoptimized
static int ninjaTraining(int n, int[][] points) {
        // Initialize an array 'prev' to store the maximum points for the previous day
        int prev[] = new int[4];

        // Initialize the first day's maximum points based on the available choices
        prev[0] = Math.max(points[0][1], points[0][2]);
        prev[1] = Math.max(points[0][0], points[0][2]);
        prev[2] = Math.max(points[0][0], points[0][1]);
        prev[3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));

        // Iterate through each day starting from the second day
        for (int day = 1; day < n; day++) {
            // Initialize an array 'temp' to store the maximum points for the current day
            int temp[] = new int[4];
            for (int last = 0; last < 4; last++) {
                temp[last] = 0; // Initialize the maximum points for the current day and last activity
                // Consider each possible task for the current day
                for (int task = 0; task <= 2; task++) {
                    if (task != last) { // Ensure that the current task is different from the last
                        // Calculate the points for the current activity and add it to the maximum points from the previous day
                        temp[last] = Math.max(temp[last], points[day][task] + prev[task]);
                    }
                }
            }
            // Update 'prev' to store the maximum points for the current day
            prev = temp;
        }

        // Return the maximum points achievable after all days (last activity is 3)
        return prev[3];
    }
