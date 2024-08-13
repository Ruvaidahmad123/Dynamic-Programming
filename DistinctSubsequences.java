//recursive
class Solution {
    public int f(int i,int j,String s,String t){
        if(j<0) return 1;
        if(i<0) return 0;
        if(s.charAt(i)==t.charAt(j)){           //matching
            return f(i-1,j-1,s,t)+f(i-1,j,s,t);   //take this matching case or look forward for more possibilities
        }
        return f(i-1,j,s,t);
    }
    public int numDistinct(String s, String t) {
        int n1=s.length();
        int n2=t.length();
        return f(n1-1,n2-1,s,t);
    }
}
//memoization
class Solution {
    public int f(int i,int j,String s,String t,int [][]dp){
        if(j<0) return 1;
        if(i<0) return 0;
        if(dp[i][j]!=-1)return dp[i][j];
        if(s.charAt(i)==t.charAt(j)){           //matching
            return dp[i][j]=f(i-1,j-1,s,t,dp)+f(i-1,j,s,t,dp);   //take this matching case or look forward for more possibilities
        }
        return dp[i][j]=f(i-1,j,s,t,dp);
    }
    public int numDistinct(String s, String t) {
        int n1=s.length();
        int n2=t.length();
        int dp[][]=new int[n1][n2];
        for(int rows[]:dp){
            Arrays.fill(rows,-1);
        }
        return f(n1-1,n2-1,s,t,dp);
    }
}
//tabulation
static int subsequenceCounting(String s1, String s2, int n, int m) {
        // Create a 2D array to store the counts of subsequences
        int dp[][] = new int[n + 1][m + 1];

        // Initialize the first column with 1 because there's one empty subsequence in any string.
        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = 1;
        }

        // Initialize the first row (except dp[0][0]) with 0 because there's no way to form s2 from an empty string.
        for (int i = 1; i < m + 1; i++) {
            dp[0][i] = 0;
        }

        // Fill the dp array using a bottom-up approach
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    // If the characters match, we can either include this character in the subsequence
                    // or exclude it. So, we add the counts from both possibilities.
                    dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]) % prime;
                } else {
                    // If the characters don't match, we can only exclude this character.
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][m];
    }
}
//space optimized
static int subsequenceCounting(String s1, String s2, int n, int m) {
    // Define a large prime number to take modulo (if not already defined)
    int prime = 1000000007;

    // Initialize two 1D arrays for the current and previous rows
    int[] prev = new int[m + 1];
    int[] curr = new int[m + 1];

    // Base case: dp[i][0] = 1 (An empty target can always be formed from any prefix of s1)
    for (int i = 0; i <= n; i++) {
        prev[0] = 1;
    }

    // Fill the dp array using a bottom-up approach
    for (int i = 1; i <= n; i++) {
        // Each new row starts with curr[0] = 1 since dp[i][0] = 1 for all i
        curr[0] = 1;

        for (int j = 1; j <= m; j++) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                // If characters match, add counts from including or excluding the character
                curr[j] = (prev[j - 1] + prev[j]) % prime;
            } else {
                // If characters don't match, carry forward the count without the current character
                curr[j] = prev[j];
            }
        }

        // Move current row to previous for the next iteration
        prev = curr.clone();
    }

    // The result is stored in prev[m] after the final iteration
    return prev[m];
}
//more space optimization done below
static int subsequenceCounting(String s1, String s2, int n, int m) {
        // Create an array to store the counts of subsequences
        int[] prev = new int[m + 1];

        // Initialize the first element to 1 because there's one empty subsequence in any string.
        prev[0] = 1;

        // Fill the prev array using a bottom-up approach
        for (int i = 1; i < n + 1; i++) {
            for (int j = m; j >= 1; j--) { // Reverse direction for updating

                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    // If the characters match, we can either include this character in the subsequence
                    // or exclude it. So, we add the counts from both possibilities.
                    prev[j] = (prev[j - 1] + prev[j]) % prime;
                } else {
                    // If the characters don't match, we can only exclude this character.
                    prev[j] = prev[j]; // This statement is not necessary, as it doesn't change the value.
                }
            }
        }

        return prev[m];
    }
}
