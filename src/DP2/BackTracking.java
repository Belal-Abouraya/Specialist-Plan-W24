package DP2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class BackTracking {
	static int n, W;
	static long v[], w[], memo[][];

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

	// Stores the indices of the taken elements/
	static ArrayList<Integer> ans;

	// Traces the correct dp path.
	static void trace(int idx, int currWeight) {
		if (idx == n)
			return;
		// leave
		long a = dp(idx + 1, currWeight), b = 0;
		// take
		if (currWeight + w[idx] <= W)
			b = v[idx] + dp(idx + 1, (int) (currWeight + w[idx]));

		if (a == dp(idx, currWeight)) {
			trace(idx + 1, currWeight);
		} else {
			ans.add(idx);
			trace(idx + 1, (int) w[idx] + currWeight);
		}
	}

	public static void main(String[] args) throws IOException {
		memo = new long[n][W + 1];
		for (int i = 0; i < n; i++)
			Arrays.fill(memo[i], -1);
		long res = dp(0, 0);
		ans = new ArrayList<>();
		trace(0, 0);
	}
}
