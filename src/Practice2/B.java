package Practice2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B {
	static Scanner sc = new Scanner(System.in);
	static PrintWriter pw = new PrintWriter(System.out);
	static int n, mats[][], memo[][];

	// dp represents the minimum number of operations to multiply the segment
	// from l to r.
	static int dp(int l, int r) {
		if (l == r)
			return 0;
		if (memo[l][r] != -1)
			return memo[l][r];
		int ans = (int) 1e9;
		// we try to divide the segment at all possible places and take the minimum.
		for (int i = l; i < r; i++) {
			int tmp = mats[l][0] * mats[i][1] * mats[r][1];
			ans = Math.min(ans, tmp + dp(l, i) + dp(i + 1, r));
		}
		return memo[l][r] = ans;
	}

	static void trace(int l, int r) {
		if (l == r) {
			pw.print("A" + (l + 1));
			return;
		}
		int ans = (int) 1e9;
		int best = l;
		for (int i = l; i < r; i++) {
			int tmp = mats[l][0] * mats[i][1] * mats[r][1];
			ans = tmp + dp(l, i) + dp(i + 1, r);
			if (ans == dp(l, r)) {
				best = i;
				break;
			}
		}
		// print an opening bracket
		pw.print("(");
		// print the left segment answer
		trace(l, best);
		// print a multiplication sign
		pw.print(" x ");
		// print the right segment answer
		trace(best + 1, r);
		pw.print(")");
	}

	public static void main(String[] args) throws IOException {
		int x = 1;
		while (true) {
			n = sc.nextInt();
			if (n == 0)
				break;
			memo = new int[n][n];
			for (int i = 0; i < n; i++)
				Arrays.fill(memo[i], -1);
			mats = new int[n][2];
			for (int i = 0; i < n; i++) {
				mats[i][0] = sc.nextInt();
				mats[i][1] = sc.nextInt();
			}
			dp(0, n - 1);
			pw.print("Case " + x++ + ": ");
			trace(0, n - 1);
			pw.println();
		}
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