//tabulation optimized
class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int hash[]=new int[nums.length];
        int dp[]=new int[nums.length];
        Arrays.fill(dp,1);
        int maxi=1;
        int lastIndex=0;
        for(int i=0;i<nums.length;i++){
            hash[i]=i;
            for(int prev=0;prev<i;prev++){
                if (nums[i]>nums[prev] && (nums[i]%nums[prev]==0 || nums[prev]%nums[i]==0) && 1 + dp[prev] > dp[i]) {
                    dp[i] = 1 + dp[prev];
                    hash[i] = prev;
                }
            }
            if (dp[i] > maxi) {
                maxi = dp[i];
                lastIndex = i;
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
