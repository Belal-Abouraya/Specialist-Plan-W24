package DP2;

import java.io.IOException;
import java.util.Arrays;

// Problem:

// Given n <= 1e15 find the sum of the digits of all numbers from 1 to n.

public class DigitDP {
	static String s; // stores the number as a string
	static long n = 11, memo[][]; // n is the number itself
	static int N; // N is the length of the string

	// Finds the sum of all possible numbers in the current state.
	// idx is the index of the current digit, f is a flag that
	// indicates whether we can exceed the digit in s
	static long dp(int idx, int f) {
		if (idx == N)
			return 0;
		if (memo[idx][f] != -1)
			return memo[idx][f];
		int lim = s.charAt(idx) - '0';
		if (f == 1)
			lim = 9;
		long res = 0;
		for (int i = 0; i <= lim; i++) {
			if (f == 1) {
				res += i * Math.pow(10, N - idx - 1) + dp(idx + 1, 1);
			} else {
				if (i == lim) {
					res += i * (n % Math.pow(10, N - idx - 1) + 1) + dp(idx + 1, 0);
				} else {
					res += i * Math.pow(10, N - idx - 1) + dp(idx + 1, 1);
				}
			}
		}

		return memo[idx][f] = res;
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		s = n + "";
		N = s.length();
		memo = new long[N + 1][2];
		for (int i = 0; i <= N; i++)
			Arrays.fill(memo[i], -1);
		long res = dp(0, 0);
		// brute force
		int x = 0;
		for (int i = 0; i <= n; i++) {
			int tmp = i;
			while (tmp > 0) {
				x += tmp % 10;
				tmp /= 10;
			}
		}
	}
}
