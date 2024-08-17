class Solution {
    public static int LongestBitonicSequence(int n, int[] nums) {
        // code here
        if (n == 0) return 0; // Handle the edge case where the array is empty

        int dp1[] = new int[n];
        int dp2[] = new int[n];
        Arrays.fill(dp1, 1);
        Arrays.fill(dp2, 1);

        // Calculate the LIS for each element (dp1)
        for (int i = 0; i < n; i++) {
            for (int prev = 0; prev < i; prev++) {
                if (nums[i] > nums[prev] && dp1[prev] + 1 > dp1[i]) {
                    dp1[i] = dp1[prev] + 1;
                }
            }
        }

        // Calculate the LDS for each element (dp2)
        for (int i = n - 1; i >= 0; i--) {
            for (int prev = n - 1; prev > i; prev--) {
                if (nums[i] > nums[prev] && dp2[prev] + 1 > dp2[i]) {
                    dp2[i] = dp2[prev] + 1;
                }
            }
        }

        // Find the maximum length of the bitonic subsequence
        int max = 0;  // Initialize max to 0
        for (int i = 0; i < n; i++) {
            // Only consider valid bitonic subsequences
            if (dp1[i] > 1 && dp2[i] > 1) {
                max = Math.max(max, dp1[i] + dp2[i] - 1);
            }
        }

        return max;
    }
}
