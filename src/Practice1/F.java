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

public class F {
	static Scanner sc = new Scanner(System.in);
	static PrintWriter pw = new PrintWriter(System.out);

	static int n;
	static long memo[][];
	static String s;

	public static void main(String[] args) throws IOException, InterruptedException {
		s = sc.next();
		n = s.length();
		memo = new long[n + 1][4];
		for (int i = 0; i <= n; i++)
			Arrays.fill(memo[i], -1);

		// this precomputes some states to reduce the recursion
		// depth to avoid runtime error verdict
		for (int i = n - 1; i >= 0; i--)
			for (int j = 0; j <= 3; j++)
				dp(i, j);
		pw.print(dp(0, 0));
		pw.close();
	}

	// dp(idx, pos) represents the number of subsequences from idx to the end that
	// match the string "wow" from position pos to the end.

	static long dp(int idx, int pos) {
		if (idx == n) {
			if (pos == 3)// means we found the whole string
				return 1;
			return 0;
		}
		if (memo[idx][pos] != -1)
			return memo[idx][pos];
		// leave
		long res = dp(idx + 1, pos);
		// take
		if (pos == 0 || pos == 2) {
			if (idx + 1 < n && s.charAt(idx) == 'v' && s.charAt(idx + 1) == 'v') {
				res += dp(idx + 2, pos + 1);
			}
		} else if (pos == 1) {
			if (s.charAt(idx) == 'o')
				res += dp(idx + 1, pos + 1);
		}
		return memo[idx][pos] = res;
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