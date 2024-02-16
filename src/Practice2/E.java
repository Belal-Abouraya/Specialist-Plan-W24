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

public class E {
	static Scanner sc = new Scanner(System.in);
	static PrintWriter pw = new PrintWriter(System.out);

	static int mod = (int) 1e9 + 7, n, d, m;
	static String s;
	static int memo[][][];

	static int dp(int mx, int idx, int sum) {
		if (idx == n) {
			if (sum == 0)
				return 1;
			return 0;
		}
		if (memo[mx][idx][sum] != -1)
			return memo[mx][idx][sum];
		int lim = s.charAt(idx) - '0', nsum = 10 * sum % m;
		if (mx == 1)
			lim = 9;
		long ans = 0;
		for (int i = 0; i <= lim; i++) {
			if (idx % 2 == 1 && i != d)// Even position. Must put d.
				continue;
			if (idx % 2 == 0 && i == d)// Odd position. Can't put d.
				continue;
			if (mx == 1 || i < lim)
				ans += dp(1, idx + 1, (nsum + i) % m);
			else
				ans += dp(0, idx + 1, (nsum + i) % m);
			ans %= mod;
		}
		return memo[mx][idx][sum] = (int) ans;
	}

	public static void main(String[] args) throws IOException {
		m = sc.nextInt();
		d = sc.nextInt();
		s = sc.next();
		BigInteger S = new BigInteger(s);
		n = s.length();
		s = S.subtract(BigInteger.ONE).toString(); // Subtract 1 from a.
		if (s.length() < n)
			s = "0" + s; // Keeps the same length.

		memo = new int[2][n + 1][m];
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < n; j++)
				Arrays.fill(memo[i][j], -1);
		long lower = dp(0, 0, 0);
		s = sc.next();
		n = s.length();
		memo = new int[2][n + 1][m];

		for (int i = 0; i < 2; i++)
			for (int j = 0; j < n; j++)
				Arrays.fill(memo[i][j], -1);
		long upper = dp(0, 0, 0);
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