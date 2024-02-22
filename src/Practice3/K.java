package Practice3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class K {
	static Scanner sc = new Scanner(System.in);
	static PrintWriter pw = new PrintWriter(System.out);

	static int n, mod = (int) 1e9 + 7, memo[][], memoSum[][];
	static String s;

	// This function computes the prefix sum of the dp array.
	static int dpSum(int len, int prev) {
		if (prev == 0)
			return 0;
		if (memoSum[len][prev] != -1)
			return memoSum[len][prev];
		long res = (0l + dp(len, prev) + dpSum(len, prev - 1)) % mod;
		return memoSum[len][prev] = (int) res;
	}

	// len: the length of the remaining premutaion.
	// prev: the last element placed.
	static int dp(int len, int prev) {
		if (len == 0)
			return 1;
		if (memo[len][prev] != -1)
			return memo[len][prev];
		long res = 0;
		if (s.charAt(len - 1) == '<') {
//			for (int i = 1; i < prev; i++) {
//				res += dp(len - 1, i);
//				res %= mod;
//			}

			// This part replaces the earlier loop.

			// we do only until before the prev because we need it to be less than
			// the previous.
			res = (0l + dpSum(len - 1, prev - 1) - dpSum(len - 1, 0) + mod) % mod;
			// Add mod to avoid negative values.
		} else {
//			for (int i = prev; i <= len; i++) {
//				res += dp(len - 1, i);
//				res %= mod;
//			}

			// This part replaces the earlier loop.

			// we start with the prev because the the permutation gets smaller
			// everytime so the element at position prev is actually greater
			// than prev.
			res = (0l + dpSum(len - 1, len) - dpSum(len - 1, prev - 1) + mod) % mod;
			// Add mod to avoid negative values.
		}
		return memo[len][prev] = (int) res;
	}

	public static void main(String[] args) throws IOException {
		n = sc.nextInt();
		s = sc.next();
		memo = new int[n + 1][n + 1];
		memoSum = new int[n + 1][n + 1];
		for (int i = 0; i <= n; i++) {
			Arrays.fill(memo[i], -1);
			Arrays.fill(memoSum[i], -1);
		}
		// The answer is sum of all prev because we try all possible endings.
		int res = dpSum(n - 1, n);
		pw.println(res);
		pw.close();
	}

	static class Scanner {
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s) {
			br = new BufferedReader(new InputStreamReader(s));
		}

		public Scanner(String file) throws FileNotFoundException {
			br = new BufferedReader(new FileReader(file));
		}

		public String next() throws IOException {
			while (st == null || !st.hasMoreTokens())
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {
			return Integer.parseInt(next());
		}

		public long nextLong() throws IOException {
			return Long.parseLong(next());
		}

		public String nextLine() throws IOException {
			return br.readLine();
		}

		public double nextDouble() throws IOException {
			return Double.parseDouble(next());
		}

		public boolean ready() throws IOException {
			return br.ready();
		}
	}
}