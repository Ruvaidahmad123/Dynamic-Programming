class Solution {
    public List<String> all_longest_common_subsequences(String s, String t) {
        int n = s.length();
        int m = t.length();
        
        int[][] dp = new int[n+1][m+1];
        
        for(int i=0; i<=n; i++){
            dp[i][0] = 0;
        }
        for(int j=0; j<=m; j++){
            dp[0][j] =0;
        }
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                if(s.charAt(i-1) == t.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }
                else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        
        Set<String> set = new HashSet<>();
        Map<String, List<String>> map = new HashMap<>();
        backtrack(dp, s, t, n, m, "", set, map);
        List<String> lst = new ArrayList<>(set);
        Collections.sort(lst);
        return lst;
    }
    public void backtrack(int[][] dp, String s, String t, int i, int j, String lcs, Set<String> res, Map<String, List<String>> memo){
        if(i == 0 || j == 0){
            res.add(new StringBuilder(lcs).reverse().toString());
            return;
        }
        
        
        String key = i + "," + j + "," + lcs;
        
        if(memo.containsKey(key)){
            res.addAll(memo.get(key));
            return;
        }
        
        
        if(s.charAt(i-1) == t.charAt(j-1)){
            backtrack(dp, s, t,i-1, j-1, lcs + s.charAt(i-1), res, memo);
        }else{
            if(dp[i-1][j] == dp[i][j]){
                backtrack(dp, s, t, i-1, j, lcs, res, memo);
            }
            if(dp[i][j-1] == dp[i][j]){
                backtrack(dp, s, t, i, j-1, lcs, res, memo);
            }
        }
        
        List<String> lst = new ArrayList<>(res);
        memo.put(key, lst);
    }
}
/*Base Case: When i == 0 or j == 0, it means one of the strings is fully processed. The current LCS is added to the result set res after reversing it.
Memoization Key: The key for memoization is created by concatenating i, j, and lcs. If the current state has been computed before (checked via memo.containsKey(key)), the function retrieves the results from the map and adds them to res.
Character Match: If the characters s[i-1] and t[j-1] match, the function backtracks diagonally (i-1, j-1) and includes this character in lcs.
Character Mismatch: If the characters don't match, the function checks both possibilities:
Moving up in the DP table (i-1, j) if dp[i-1][j] == dp[i][j].
Moving left in the DP table (i, j-1) if dp[i][j-1] == dp[i][j].
Memoization: After processing, the current results are stored in the map memo for future reuse.
4. Sorting the Results:
After backtracking, all LCS sequences are stored in set. These sequences are then converted to a list and sorted lexicographically using Collections.sort(lst).
Key Points:
DP Table: Helps determine the length of the LCS and provides a way to backtrack to find all possible LCS sequences.
Backtracking: Explores all possible paths in the DP table to generate all LCS sequences.
Memoization: Optimizes the backtracking process by storing already computed results to avoid redundant calculations.
Lexicographical Order: After generating all sequences, they are sorted to ensure the output is in lexicographical order.
This code effectively solves the problem of finding all longest common subsequences in lexicographical order while efficiently handling duplicates and redundant computations.*/
