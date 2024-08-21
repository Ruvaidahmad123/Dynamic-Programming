//recursive
class Solution {
    public boolean isPalindrome(int i, int j, String s) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }
    public int f(int i,int n,String s){
        if(i==n)return 0;
        int min=Integer.MAX_VALUE;
        for(int j=i;j<n;j++){
            if(isPalindrome(i,j,s)){
                int cost=1+f(j+1,n,s);
                min=Math.min(min,cost);
            }
        }
        return min;
    }
    public int minCut(String s) {
        int n=s.length();
        return f(0,n,s)-1;
    }
}
//memoized
class Solution {
    public boolean isPalindrome(int i, int j, String s) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }
    public int f(int i,int n,String s,int dp[][]){
        if(i==n)return 0;
        if(dp[i][n]!=-1)return dp[i][n];
        int min=Integer.MAX_VALUE;
        for(int j=i;j<n;j++){
            if(isPalindrome(i,j,s)){
                int cost=1+f(j+1,n,s,dp);
                min=Math.min(min,cost);
            }
        }
        return dp[i][n]=min;
    }
    public int minCut(String s) {
        int n=s.length();
        int dp[][]=new int[n+1][n+1];
        for(int rows[]:dp){
            Arrays.fill(rows,-1);
        }
        return f(0,n,s,dp)-1;
    }
}
//tabulation
public class PalindromePartitioning {
    static boolean isPalindrome(int i, int j, String s) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }

    static int palindromePartitioning(String str) {
        int n = str.length();
        int[] dp = new int[n + 1];
        dp[n] = 0;
        for (int i = n - 1; i >= 0; i--) {
            int minCost = Integer.MAX_VALUE;
            // String[i...j]
            for (int j = i; j < n; j++) {
                if (isPalindrome(i, j, str)) {
                    int cost = 1 + dp[j + 1];
                    minCost = Math.min(minCost, cost);
                }
            }
            dp[i] = minCost;
        }
        return dp[0] - 1;
    }
}
