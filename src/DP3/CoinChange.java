package DP3;

import java.util.Arrays;
import java.util.Scanner;

public class CoinChange {
	static int n, c, memo[], v[];

	static int dp(int rem) {
		if (rem == 0)
			return 0;
		if (rem < 0)
			return (int) 1e9;
		if (memo[rem] != -1)
			return memo[rem];
		int res = (int) 1e9;
		for (int i = 0; i < n; i++)
			res = Math.min(res, 1 + dp(rem - v[i]));
		return memo[rem] = res;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		c = sc.nextInt();
		n = sc.nextInt();
		v = new int[n];
		for (int i = 0; i < n; i++)
			v[i] = sc.nextInt();
		memo = new int[c + 1];
		Arrays.fill(memo, -1);
		System.out.println(dp(c));
	}
}
