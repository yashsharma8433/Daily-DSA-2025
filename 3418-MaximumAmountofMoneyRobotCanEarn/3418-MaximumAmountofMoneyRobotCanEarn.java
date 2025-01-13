class Solution {
    public int maximumAmount(int[][] coins) {
        int m = coins.length;
        int n = coins[0].length;

        // DP array: dp[i][j][k] represents the maximum profit at (i, j) with k neutralizations
        int[][][] dp = new int[m][n][3];

        // Initialize DP array with minimum values
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 3; k++) {
                    dp[i][j][k] = Integer.MIN_VALUE;
                }
            }
        }

        // Base case: Starting point
        dp[0][0][0] = coins[0][0]; // Starting with no neutralization
        if (coins[0][0] < 0) {
            dp[0][0][1] = 0; // Neutralizing the first cell if it's a robber
        }

        // Fill the DP table
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 3; k++) {
                    // Skip invalid states
                    if (dp[i][j][k] == Integer.MIN_VALUE) continue;

                    // Move Right
                    if (j + 1 < n) {
                        int gain = coins[i][j + 1] >= 0 ? coins[i][j + 1] : 0;
                        int loss = coins[i][j + 1] < 0 ? -coins[i][j + 1] : 0;

                        // Without neutralization
                        dp[i][j + 1][k] = Math.max(dp[i][j + 1][k], dp[i][j][k] + gain - loss);

                        // With neutralization
                        if (k < 2 && coins[i][j + 1] < 0) {
                            dp[i][j + 1][k + 1] = Math.max(dp[i][j + 1][k + 1], dp[i][j][k] + gain);
                        }
                    }

                    // Move Down
                    if (i + 1 < m) {
                        int gain = coins[i + 1][j] >= 0 ? coins[i + 1][j] : 0;
                        int loss = coins[i + 1][j] < 0 ? -coins[i + 1][j] : 0;

                        // Without neutralization
                        dp[i + 1][j][k] = Math.max(dp[i + 1][j][k], dp[i][j][k] + gain - loss);

                        // With neutralization
                        if (k < 2 && coins[i + 1][j] < 0) {
                            dp[i + 1][j][k + 1] = Math.max(dp[i + 1][j][k + 1], dp[i][j][k] + gain);
                        }
                    }
                }
            }
        }

        // The result is the maximum value at the bottom-right corner with up to 2 neutralizations
        return Math.max(dp[m - 1][n - 1][0], Math.max(dp[m - 1][n - 1][1], dp[m - 1][n - 1][2]));
    }
}
