//recursive
class Solution{
    public static int f(int arr[],int i,int j){
        if(i == j)
        return 0;
        int mini = Integer.MAX_VALUE;
    // partioning loop
    for(int k = i; k<= j-1; k++){
        int ans = f(arr,i,k) + f(arr, k+1,j) + arr[i-1]*arr[k]*arr[j];
        mini = Math.min(mini,ans);
    }
    return mini;
    }
    static int matrixMultiplication(int N, int arr[])
    {
        return f(arr,1,N-1);
    }
}
//memoization
class Solution{
    public static int f(int arr[],int i,int j,int dp[][]){
        if(i == j)
        return 0;
        if(dp[i][j]!=-1)return dp[i][j];
        int mini = Integer.MAX_VALUE;
    // partioning loop
    for(int k = i; k<= j-1; k++){
        int ans = f(arr,i,k,dp) + f(arr, k+1,j,dp) + arr[i-1]*arr[k]*arr[j];
        mini = Math.min(mini,ans);
    }
    return dp[i][j]=mini;
    }
    static int matrixMultiplication(int N, int arr[])
    {
        int dp[][]=new int[N+1][N+1];
        for(int rows[]:dp){
            Arrays.fill(rows,-1);
        }
        return f(arr,1,N-1,dp);
    }
}
//tabulation
class Solution{
    static int matrixMultiplication(int N, int arr[])
    {
        int dp[][]=new int[N][N];
        for(int i=n-1;i>=1;i--){
          for(int j=i+1;j<n;j++){
            int mini = Integer.MAX_VALUE;
            for(int k = i; k<= j-1; k++){
                int ans = dp[i][k] + dp[k+1][j] + arr[i-1]*arr[k]*arr[j];
                mini = Math.min(mini,ans);
            }
          }
        }
      return dp[1][N - 1];
    }
}
