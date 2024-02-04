package DP1;

import java.util.Arrays;

public class Knapsack {
	static int n, W;
	static long v[], w[], memo[][];

	// top-down
	// O(n*W)
	static long dp(int idx, int currWeight) {
		if (idx == n)
			return 0;
		if (memo[idx][currWeight] != -1)
			return memo[idx][currWeight];
		// leave
		long res = dp(idx + 1, currWeight);
		// take
		if (currWeight + w[idx] <= W)
			res = Math.max(res, v[idx] + dp(idx + 1, (int) (currWeight + w[idx])));
		return memo[idx][currWeight] = res;
	}

	public static void main(String[] args) {
		memo = new long[n][W + 1];
		for (int i = 0; i < n; i++)
			Arrays.fill(memo[i], -1);
		// bottom-up
		long[][] dp = new long[n + 1][W + 1];
		Arrays.fill(dp[n], 0);
		for (int idx = n - 1; idx >= 0; idx--) {
			for (int currWeight = 0; currWeight <= W; currWeight++) {
				// leave
				long res = dp[idx + 1][currWeight];
				// take
				if (currWeight + w[idx] <= W)
					res = Math.max(res, v[idx] + dp[idx + 1][currWeight + (int) w[idx]]);
				dp[idx][currWeight] = res;
			}
		}
	}
}
