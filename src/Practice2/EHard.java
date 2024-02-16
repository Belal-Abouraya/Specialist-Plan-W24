package Practice2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class EHard {
	static Scanner sc = new Scanner(System.in);
	static PrintWriter pw = new PrintWriter(System.out);

	static int mod = (int) 1e9 + 7, n, d, m;
	static String s;
	static int memo[][][][];

	// It actually turns out that this solution is pretty much overkill because a
	// and b always
	// have the same number of digits so leading zeros will never happen in any test
	// case.
	// None the less this solution is valid for the general problem where a and b
	// can be anything.
	// It is also a good exercise to try and understand this code. For the given
	// problem the zi state is redundant so you can just drop it and use the parity
	// of
	// the current index directly.

	// dp represents the number of d-magic numbers that are divisible by m
	// in the current state.
	// mx : whether we can exceed the max digit
	// or not.
	// zi : the parity of the index of first non zero digit (2 means
	// no none zero digit yet).
	// idx : represents the current idx in the string.
	// sum : the current number modulo m.
	static long dp(int mx, int zi, int idx, int sum) {
		if (idx == n) {
			if (sum == 0)
				return 1;
			return 0;
		}
		if (memo[mx][zi][idx][sum] != -1)
			return memo[mx][zi][idx][sum];
		long ans = 0;
		int lim = s.charAt(idx) - '0';
		if (mx == 1)
			lim = 9;
		// No none zero digit yet.
		if (zi == 2) {
			// Add a leading zero.
			ans += dp(1, 2, idx + 1, sum);
			ans %= mod;
			for (int i = 1; i <= lim; i++) {
				if (i == d) // Can't put d in position 1.
					continue;
				if (i == lim && mx == 0) // happens only in the first time we put a non zero digit
											// otherwise mx is 1 here.
					ans += dp(0, idx % 2, idx + 1, i % m);
				else
					ans += dp(1, idx % 2, idx + 1, i % m);
				ans %= mod;
			}
		} else {
			int nxts = sum * 10 % m; // Multiply the current sum by 10 and take modulo m.
			if ((idx - zi) % 2 == 1) { // Even position so we put d.
				if (mx == 0 && d > lim) // Can't put d.
					ans = 0;
				else if (mx == 0 && d < lim)
					ans = dp(1, zi, idx + 1, (nxts + d) % m);
				else // d is the maximum possible digit.
					ans = dp(mx, zi, idx + 1, (nxts + d) % m);
			} else { // Odd position.
				for (int i = 0; i <= lim; i++) {
					if (i == d) // Can't put d here.
						continue;
					if (mx == 1) {
						ans += dp(1, zi, idx + 1, (nxts + i) % m);
					} else {
						if (i == lim)
							ans += dp(0, zi, idx + 1, (nxts + i) % m);
						else
							ans += dp(1, zi, idx + 1, (nxts + i) % m);
					}
					ans %= mod;
				}
			}
		}
		return memo[mx][zi][idx][sum] = (int) ans;
	}

	public static void main(String[] args) throws IOException {
		m = sc.nextInt();
		d = sc.nextInt();
		s = sc.next();
		BigInteger S = new BigInteger(s);
		s = S.subtract(BigInteger.ONE).toString(); // Subtract 1 from a.
		n = s.length();
		memo = new int[2][3][n + 1][m];
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 3; j++)
				for (int k = 0; k < n; k++)
					Arrays.fill(memo[i][j][k], -1);
		long lower = dp(0, 2, 0, 0);
		s = sc.next();
		n = s.length();
		memo = new int[2][3][n + 1][m];

		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 3; j++)
				for (int k = 0; k < n; k++)
					Arrays.fill(memo[i][j][k], -1);
		long upper = dp(0, 2, 0, 0);
		long ans = (upper - lower + mod) % mod; // avoid negative results
		pw.println(ans);
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