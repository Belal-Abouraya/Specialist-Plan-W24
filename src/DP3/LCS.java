package DP3;

import java.util.Arrays;
import java.util.Scanner;

public class LCS {

	static String s, t, res;
	static int memo[][];

	static int dp(int i, int j) {
		if (i == s.length())
			return 0;
		if (j == t.length())
			return 0;
		if (memo[i][j] != -1)
			return memo[i][j];
		// Leave the current character in s.
		int leaves = dp(i + 1, j);
		// Leave the current character in t.
		int leavet = dp(i, j + 1), take = 0;
		// Take the character if it is common to both.
		if (s.charAt(i) == t.charAt(j))
			take = 1 + dp(i + 1, j + 1);
		return memo[i][j] = Math.max(leaves, Math.max(leavet, take));
	}

	static void trace(int i, int j) {
		if (i == s.length())
			return;
		if (j == t.length())
			return;
		int leaves = dp(i + 1, j), leavet = dp(i, j + 1), take = 0;
		if (s.charAt(i) == t.charAt(j))
			take = 1 + dp(i + 1, j + 1);
		if (leaves == dp(i, j))
			trace(i + 1, j);
		else if (leavet == dp(i, j))
			trace(i, j + 1);
		else {
			res += s.charAt(i);
			trace(i + 1, j + 1);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		s = sc.next();
		t = sc.next();
		res = new String();
		memo = new int[s.length() + 1][t.length() + 1];
		for (int i = 0; i < s.length(); i++)
			Arrays.fill(memo[i], -1);
		dp(0, 0);
		trace(0, 0);
		System.out.println(dp(0, 0));
		System.out.println(res);
	}
}
