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

public class C {
	static Scanner sc = new Scanner(System.in);
	static PrintWriter pw = new PrintWriter(System.out);

	static int h, w;
	static final int mod = (int) 1e9 + 7;
	static char grid[][];
	static long memo[][];

	public static void main(String[] args) throws IOException, InterruptedException {
		Scanner sc = new Scanner(System.in);
		h = sc.nextInt();
		w = sc.nextInt();
		grid = new char[h][w];
		memo = new long[h][w];
		for (int i = 0; i < h; i++) {
			grid[i] = sc.next().toCharArray();
			Arrays.fill(memo[i], -1);
		}
		pw.print(dp(0, 0));
		pw.close();
	}

	// dp(i, j) represents the number of ways to get from cell (i, j)
	// to cell (h - 1, w - 1) modulo mod.

	static long dp(int i, int j) {
		if (i == h - 1 && j == w - 1)
			return 1;
		if (memo[i][j] != -1)
			return memo[i][j];
		long res = 0;
		if (i + 1 < h && grid[i + 1][j] == '.') {
			res += dp(i + 1, j);
			res %= mod;
		}
		if (j + 1 < w && grid[i][j + 1] == '.') {
			res += dp(i, j + 1);
			res %= mod;
		}
		return memo[i][j] = res;
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