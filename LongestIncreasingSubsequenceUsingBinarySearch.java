class Solution {
    // Function to find length of longest increasing subsequence.
    static int longestSubsequence(int n, int arr[]) {
        List<Integer> temp = new ArrayList<>();
        temp.add(arr[0]);

        int len = 1;

        for (int i = 1; i < n; i++) {
            if (arr[i] > temp.get(temp.size() - 1)) {
                temp.add(arr[i]);
                len++;
            } else {
                int ind = Collections.binarySearch(temp, arr[i]);
                if (ind < 0) {
                    ind = -ind - 1;
                }
                temp.set(ind, arr[i]);
            }
        }

        return len;
    }
}
