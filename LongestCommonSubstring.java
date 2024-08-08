//tabulation
class Solution {
    public int longestCommonSubstr(String text1, String text2) {
        int n1=text1.length();
        int n2=text2.length();
        int dp[][]=new int[n1+1][n2+1];
        for(int rows[]:dp){
            Arrays.fill(rows,-1);
        }
        int max=0;
        for(int idx1=0;idx1<=n1;idx1++){
            for(int idx2=0;idx2<=n2;idx2++){
                if(idx1==0 || idx2==0){
                    dp[idx1][idx2]=0; 
                }
                else if(text1.charAt(idx1-1)==text2.charAt(idx2-1)){
                    dp[idx1][idx2]=1+dp[idx1-1][idx2-1];
                    max=Math.max(max,dp[idx1][idx2]);
                }
                else
                dp[idx1][idx2]=0;        //same as subsequence but if they are not matching we put them 0 because substrings are consecutive 
            }
        }
        return max;
    }
}
//space optimization
class TUF {
    // Function to find the length of the Longest Common Substring (LCS)
    static int lcs(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        // Create arrays to store LCS lengths
        int prev[] = new int[m + 1];
        int cur[] = new int[m + 1];

        int ans = 0; // Initialize a variable to store the maximum LCS length

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // If the characters at the current indices are the same, extend the LCS
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    int val = 1 + prev[j - 1];
                    cur[j] = val;
                    ans = Math.max(ans, val); // Update the maximum LCS length
                } else {
                    cur[j] = 0; // Reset LCS length if characters don't match
                }
            }
            // Update the 'prev' array to the values of 'cur' for the next iteration
            prev = cur.clone();
        }
        return ans; // Return the length of the Longest Common Substring (LCS)
    }
}
