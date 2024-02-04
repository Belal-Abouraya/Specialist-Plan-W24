package DP1;

import java.util.Arrays;
import java.util.Scanner;

public class TravellingSalesman {
	static int n, g[][], memo[][];

	// O(2 ^ (n - 1) * n ^ 2)
	static int dp(int u, int mask) {
		if (mask == (1 << (n - 1)) - 1)
			return g[u][0];
		if (memo[u][mask] != -1)
			return memo[u][mask];
		int res = (int) 1e9;
		for (int i = 0; i < n - 1; i++) {
			if ((mask & (1 << i)) > 0)
				continue;
			res = Math.min(res, g[u][i + 1] + dp(i + 1, mask | (1 << i)));
		}
		return memo[u][mask] = res;
	}

	/**
	 * sample
	 * 
	 * 4
	 * 
	 * 0 1 2 3
	 * 
	 * 1 0 1 2
	 * 
	 * 2 1 0 3
	 * 
	 * 3 2 3 0
	 */

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		g = new int[n][n];
		memo = new int[n][1 << (n - 1)];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				g[i][j] = sc.nextInt();

		for (int i = 0; i < n; i++)
			Arrays.fill(memo[i], -1);
		System.out.println(dp(0, 0));
	}
}
