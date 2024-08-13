//recursive
class Solution {
    public int f(int i,int j,String word1,String word2){
      //f(-1,2) minimum operation required to convert word1 which is at -ve index to string 2 from 0th index to jth index 3 insert operations
        if(i<0)return j+1;
      //f(2,-1) minimum operation required to convert word1 which is at 2nd index to string 2 which is "" that is 3 delete operations j+1
        if(j<0) return i+1;
        if(word1.charAt(i)==word2.charAt(j))return 0+f(i-1,j-1,word1,word2);   
      //all possibilities
        int insert=1+f(i,j-1,word1,word2);
        int delete=1+f(i-1,j,word1,word2);
        int replace=1+f(i-1,j-1,word1,word2);
        return Math.min(replace,Math.min(insert,delete));
    }
    public int minDistance(String word1, String word2) {
        int n1=word1.length();
        int n2=word2.length();
        return f(n1-1,n2-1,word1,word2);
    }
}
//memoization
class Solution {
    public int f(int i,int j,String word1,String word2,int dp[][]){
        if(i<0)return j+1;
        if(j<0) return i+1;
        if(dp[i][j]!=-1)return dp[i][j];
        if(word1.charAt(i)==word2.charAt(j))return dp[i][j]=0+f(i-1,j-1,word1,word2,dp);
        int insert=1+f(i,j-1,word1,word2,dp);
        int delete=1+f(i-1,j,word1,word2,dp);
        int replace=1+f(i-1,j-1,word1,word2,dp);
        return dp[i][j]=Math.min(replace,Math.min(insert,delete));
    }
    public int minDistance(String word1, String word2) {
        int n1=word1.length();
        int n2=word2.length();
        int dp[][]=new int[n1][n2];
        for(int rows[]:dp){
            Arrays.fill(rows,-1);
        }
        return f(n1-1,n2-1,word1,word2,dp);
    }
}
//tabulated
static int editDistance(String S1, String S2) {
        int n = S1.length();
        int m = S2.length();
        // Create a 2D array to store the minimum edit distances
        int[][] dp = new int[n + 1][m + 1];
        // Initialize the first row and column with their respective indices
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= m; j++) {
            dp[0][j] = j;
        }
        // Fill the dp array using a bottom-up approach
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (S1.charAt(i - 1) == S2.charAt(j - 1)) {
                    // If the characters match, no edit is needed, so take the value from the diagonal.
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // If the characters don't match, take the minimum of three possibilities:
                    // 1. Replace the character in S1 with the character in S2 (diagonal).
                    // 2. Delete the character in S1 (left).
                    // 3. Insert the character from S2 into S1 (up).
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }
        return dp[n][m];
    }
}
//space optimization
static int editDistance(String S1, String S2) {
        int n = S1.length();
        int m = S2.length();

        // Create two arrays to store the previous and current rows of minimum edit distances
        int[] prev = new int[m + 1];
        int[] cur = new int[m + 1];

        // Initialize the first row with their respective indices
        for (int j = 0; j <= m; j++) {
            prev[j] = j;
        }

        // Fill the cur array using a bottom-up approach
        for (int i = 1; i <= n; i++) {
            cur[0] = i;
            for (int j = 1; j <= m; j++) {
                if (S1.charAt(i - 1) == S2.charAt(j - 1)) {
                    // If the characters match, no edit is needed, so take the value from the diagonal.
                    cur[j] = prev[j - 1];
                } else {
                    // If the characters don't match, take the minimum of three possibilities:
                    // 1. Replace the character in S1 with the character in S2 (diagonal).
                    // 2. Delete the character in S1 (left).
                    // 3. Insert the character from S2 into S1 (up).
                    cur[j] = 1 + Math.min(prev[j - 1], Math.min(prev[j], cur[j - 1]));
                }
            }
            // Update prev array to store the current values
            prev = cur.clone();
        }

        return cur[m];
    }
}
