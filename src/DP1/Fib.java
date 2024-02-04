package DP1;

import java.util.Arrays;

public class Fib {

	static int N = 100;
	static int[] memo = new int[N];

	// top-down
	// O(n)
	public static int fib(int n) {
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;
		if (memo[n] != -1)
			return memo[n];
		return memo[n] = fib(n - 1) + fib(n - 2);
	}

	public static void main(String[] args) {
		memo = new int[N + 1];
		Arrays.fill(memo, -1);
		// bottom-up
		// O(n)
		int[] fib = new int[N + 1];
		fib[0] = 0;
		fib[1] = 1;
		for (int i = 2; i <= N; i++)
			fib[i] = fib[i - 2] + fib[i - 1];
	}
}
