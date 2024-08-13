//recursive
class Solution {
    public boolean isAllStars(String s, int i) {
    for (int j = 0; j <= i; j++) {
      if (s.charAt(j) != '*')
        return false;
    }
    return true;
  }
    public boolean f(int i,int j,String p,String s){
        if(i<0 && j<0)return true;
        if(i<0 && j>=0)return false;
        if(j<0 && i>=0)return isAllStars(p, i) ? true : false;
        if(p.charAt(i)==s.charAt(j) || p.charAt(i)=='?'){
            return f(i-1,j-1,p,s);
        }
        if(p.charAt(i)=='*'){
            return f(i-1,j,p,s)||f(i,j-1,p,s);
        }
        return false;
    }
    public boolean isMatch(String s, String p) {
        int n1=p.length();
        int n2=s.length();
        return f(n1-1,n2-1,p,s);
    }
}
//memoization
class Solution {
    public boolean isAllStars(String s, int i) {
    for (int j = 0; j <= i; j++) {
      if (s.charAt(j) != '*')
        return false;
    }
    return true;
  }
    public boolean f(int i,int j,String p,String s,int dp[][]){
        if(i<0 && j<0)return true;
        if(i<0 && j>=0)return false;
        if(j<0 && i>=0)return isAllStars(p, i) ? true : false;
        if(dp[i][j]!=-1)return dp[i][j]==0?false:true;
        if(p.charAt(i)==s.charAt(j) || p.charAt(i)=='?'){
            boolean check1= f(i-1,j-1,p,s,dp);
            if(check1==true)dp[i][j]=1;
            else dp[i][j]=0;
            return check1;
        }
        if(p.charAt(i)=='*'){
            boolean check2= f(i-1,j,p,s,dp)||f(i,j-1,p,s,dp);
            if(check2==true)dp[i][j]=1;
            else dp[i][j]=0;
            return check2;
        }
        return false;
    }
    public boolean isMatch(String s, String p) {
        int n1=p.length();
        int n2=s.length();
        int dp[][]=new int[n1+1][n2+1];
        for(int rows[]:dp){
            Arrays.fill(rows,-1);
        }
        return f(n1-1,n2-1,p,s,dp);
    }
}
//tabulation
class Solution {
    public boolean isMatch(String s, String p) {
        int n1 = p.length();
        int n2 = s.length();
        boolean[][] dp = new boolean[n1 + 1][n2 + 1];

        // Base Cases
        dp[0][0] = true; // Both strings are empty

        // Fill the first column (when the string `s` is empty)
        for (int i = 1; i <= n1; i++) {
            dp[i][0] = dp[i - 1][0] && p.charAt(i - 1) == '*';
        }

        // Fill the DP table
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (p.charAt(i - 1) == s.charAt(j - 1) || p.charAt(i - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(i - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                } else {
                    dp[i][j] = false;
                }
            }
        }

        return dp[n1][n2];
    }
}
//space optimized
class Solution {
    public boolean isMatch(String s, String p) {
        int n1 = p.length();
        int n2 = s.length();
        boolean[] prev = new boolean[n2 + 1];
        boolean[] curr = new boolean[n2 + 1];

        // Base Case Initialization for the first row (when the string `s` is empty)
        prev[0] = true; // Both strings are empty
        
        // Fill the first row (when the pattern is non-empty and `s` is empty)
        for (int i = 1; i <= n1; i++) {
            curr[0] = prev[0] && p.charAt(i - 1) == '*';

            // Fill the current row
            for (int j = 1; j <= n2; j++) {
                if (p.charAt(i - 1) == s.charAt(j - 1) || p.charAt(i - 1) == '?') {
                    curr[j] = prev[j - 1];
                } else if (p.charAt(i - 1) == '*') {
                    curr[j] = prev[j] || curr[j - 1];
                } else {
                    curr[j] = false;
                }
            }

            // Move curr to prev for the next iteration
            boolean[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[n2];
    }
}
