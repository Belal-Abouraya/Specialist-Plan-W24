package Practice1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class E {

	static Scanner sc = new Scanner(System.in);
	static PrintWriter pw = new PrintWriter(System.out);
	static int a[], n, memo[][];

	public static void main(String[] args) throws IOException, InterruptedException {
		n = sc.nextInt();
		a = new int[n];
		memo = new int[n + 1][2];
		int total = 0;
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
			total += a[i];
		}
		for (int i = 0; i <= n; i++)
			Arrays.fill(memo[i], -1);
		int res = dp(0, 1);
		pw.print((total - res) + " " + res);
		pw.close();
	}

	// dp(idx, b) represents the maximum sum that Bob can get from the subarray
	// starting at index idx and while player b is playing b = 0 means Alice
	// and b = 1 means Bob.

	static int dp(int idx, int b) {
		if (idx == n)
			return 0;
		if (memo[idx][b] != -1)
			return memo[idx][b];
		int res;
		if (b == 1) { // here Bob is playing so he wants to maximize his sum
			// take
			res = a[idx] + dp(idx + 1, 0);
			// leave
			res = Math.max(res, dp(idx + 1, 1));
		} else { // here Alice is playing so she wants to minimize Bob's sum.
			// take
			res = dp(idx + 1, 1); // take for Alice means Bob's sum doesn't increase
			// leave
			res = Math.min(res, a[idx] + dp(idx + 1, 0)); // leave for Alice means
															// Bob's sum increases
		}
		return memo[idx][b] = res;
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
