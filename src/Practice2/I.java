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

public class I {
	static Scanner sc = new Scanner(System.in);
	static PrintWriter pw = new PrintWriter(System.out);

	static int n, m, pre[][][], memo[][][];
	// pre[i][j][k] gives us the cost to have k pancakes in index i
	// after we have distributed the first j pancakes from the left.

	// idx: current index in the array.
	// mx: the maximum number that the pile cannot exceed
	// done: number of pancakes already used.
	// We always use pancakes from the left first as
	// it is the optimal since we move from left to right.
	static int dp(int idx, int mx, int done) {
		if (done == m) // All pancakes done.
			return 0;
		if (idx == n) // Reached the end but still has pancakes to use.
			return (int) 1e9;
		if (memo[idx][mx][done] != -1)
			return memo[idx][mx][done];
		int ans = (int) 1e9;
		// We try to put all possible numbers less than or equal the maximum
		// and take the one with the minimum cost.
		for (int i = 1; i <= mx && i + done <= m; i++) {
			ans = Math.min(ans, dp(idx + 1, i, done + i) + pre[idx][done][i - 1]);
			// i - 1 because the pancakes are zero indexed.
		}
		return memo[idx][mx][done] = ans;
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		n = sc.nextInt();
		m = sc.nextInt();
		int[] pos = new int[m + 1], a = new int[n];
		// pos[i] gives the position of the ith pancake from the left.
		for (int i = 0; i < n; i++)
			a[i] = sc.nextInt();
		int idx = 0;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < a[i]; j++)
				pos[idx++] = i;

		pre = new int[n][m + 1][m + 1];
		memo = new int[n][m + 1][m + 1];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= m; j++) {
				Arrays.fill(memo[i][j], -1);
				pre[i][j][0] = Math.abs(i - pos[j]); // Put the first pancake (0-idxed).
				for (int k = 1; j + k <= m; k++) {
					pre[i][j][k] = pre[i][j][k - 1] + Math.abs(i - pos[j + k]);
					// total cost is the sum of costs of moving each pancake to index
					// i and the cost of moving previous pancakes.
				}
			}
		}
		pw.print(dp(0, m, 0)); // At the beginning we can have as many pancakes as we want/
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