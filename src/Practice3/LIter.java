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

public class LIter {
	static Scanner sc = new Scanner(System.in);
	static PrintWriter pw = new PrintWriter(System.out);
	static int n, k, memo[][], memoSum[][], a[], mod = (int) 1e9 + 7;

	public static void main(String[] args) throws IOException {
		n = sc.nextInt();
		k = sc.nextInt();
		a = new int[n];
		memo = new int[n + 1][k + 1];
		memoSum = new int[n + 1][k + 1];
		for (int i = 0; i < n; i++)
			a[i] = sc.nextInt();
		Arrays.fill(memo[n], 0);
		// Base case is one in only zero remainder.
		memo[n][0] = 1;
		// Base case for sum is one for all because it is the sum of memo.
		Arrays.fill(memoSum[n], 1);
		// We move from high index to low to move from base case to required
		// result.
		for (int i = n - 1; i >= 0; i--) {
			// We move from low remainder to high because the higher remainders
			// depend on the lower ones.
			for (int j = 0; j <= k; j++) {
				long res = memoSum[i + 1][j];
				if (j - a[i] - 1 >= 0) {
					res -= memoSum[i + 1][j - a[i] - 1];
					res += mod;
					res %= mod;
				}
				memo[i][j] = (int) res;
				memoSum[i][j] = memo[i][j];
				if (j > 0)
					memoSum[i][j] = (int) ((0l + memo[i][j] + memoSum[i][j - 1]) % mod);
			}
		}

		pw.println(memo[0][k]);
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