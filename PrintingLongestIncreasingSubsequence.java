class Solution {
    public ArrayList<Integer> longestIncreasingSubsequence(int n, int nums[]) {
        int dp[] = new int[n];
        Arrays.fill(dp, 1);
        int maxi = 1;
        int lastIndex = 0;
        int hash[] = new int[n];
        for (int idx = 0; idx < n; idx++) {
            hash[idx] = idx;
            for (int prev = 0; prev < idx; prev++) {
                if (nums[idx] > nums[prev] && 1 + dp[prev] > dp[idx]) {
                    dp[idx] = 1 + dp[prev];
                    hash[idx] = prev;
                }
            }
            if (dp[idx] > maxi) {
                maxi = dp[idx];
                lastIndex = idx;
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();
        while (hash[lastIndex] != lastIndex) {
            ans.add(nums[lastIndex]);
            lastIndex = hash[lastIndex];
        }
        ans.add(nums[lastIndex]); // Add the last element of the subsequence
        // Since the elements are added in reverse order, reverse the list before returning
        Collections.reverse(ans);
        return ans;
    }
}
