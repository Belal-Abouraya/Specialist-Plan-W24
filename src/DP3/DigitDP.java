package DP3;

import java.util.Arrays;
import java.util.Scanner;

// Problem: find the number of numbers between l and r
// whose bitwise xor of digits is 1. l and r are given in binary

public class DigitDP {
	static int[] l, r;
	static int n, memo[][][][];

	static int dp(boolean mn, boolean mx, int curr_xor, int idx) {
		if (idx == n)
			return curr_xor;
		int b1 = mn ? 1 : 0, b2 = mx ? 1 : 0;
		if (memo[b1][b2][curr_xor][idx] != -1)
			return memo[b1][b2][curr_xor][idx];
		int up = r[idx], down = l[idx];
		if (mx)
			up = 1;
		if (mn)
			down = 0;
		int res = 0;
		for (int d = down; d <= up; d++) {
			boolean nxt_mn = mn || d != down, nxt_mx = mx || d != up;
			res += dp(nxt_mn, nxt_mx, curr_xor ^ d, idx + 1);
		}
		return memo[b1][b2][curr_xor][idx] = res;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String L = sc.next(), R = sc.next();
		// Make the two strings of equal length.
		while (R.length() > L.length()) {
			L = "0" + L;
		}
		n = R.length();
		l = new int[n];
		r = new int[n];
		memo = new int[2][2][2][n + 1];
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 2; j++)
				for (int k = 0; k < 2; k++)
					Arrays.fill(memo[i][j][k], -1);
		for (int i = 0; i < n; i++) {
			l[i] = L.charAt(i) - '0';
			r[i] = R.charAt(i) - '0';

		}
		System.out.println(dp(false, false, 0, 0));
	}

}
