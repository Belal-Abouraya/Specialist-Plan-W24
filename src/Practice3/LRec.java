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

public class LRec {
	static Scanner sc = new Scanner(System.in);
	static PrintWriter pw = new PrintWriter(System.out);
	static int n, k, memo[][], memoSum[][], a[], mod = (int) 1e9 + 7;

	// This function computes the prefix sum of the dp array.
	static int dpSum(int idx, int rem) {
		if (memoSum[idx][rem] != -1)
			return memoSum[idx][rem];
		if (rem == 0)
			return dp(idx, 0);
		long res = (0l + dp(idx, rem) + dpSum(idx, rem - 1)) % mod;
		return memoSum[idx][rem] = (int) res;
	}

	// idx: the index of the current element.
	// rem: the remaining candies to be distributed.
	static int dp(int idx, int rem) {
		if (idx == n) {
			if (rem == 0)
				return 1;
			return 0;
		}
		if (memo[idx][rem] != -1)
			return memo[idx][rem];

//		for(int i = 0; i <= Math.min(rem, a[i]); i++) {
//			res += dp(idx, rem - i);
//			res %= mod;
//		}

		// This part replaces the earlier loop.
		long res = dpSum(idx + 1, rem);

		if (rem - a[idx] - 1 >= 0) {
			res -= dpSum(idx + 1, rem - a[idx] - 1);
			res += mod; // Add mod to avoid negative values.
			res %= mod;
		}

		return memo[idx][rem] = (int) res;
	}

	public static void main(String[] args) throws IOException {
		n = sc.nextInt();
		k = sc.nextInt();
		a = new int[n];
		memo = new int[n + 1][k + 1];
		memoSum = new int[n + 1][k + 1];

		for (int i = 0; i <= n; i++) {
			Arrays.fill(memo[i], -1);
			Arrays.fill(memoSum[i], -1);
		}
		for (int i = 0; i < n; i++)
			a[i] = sc.nextInt();

		for (int i = n; i >= 0; i--)
			for (int j = 0; j <= k; j++)
				dp(i, j);
		pw.println(dp(0, k));
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
