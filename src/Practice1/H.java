package Practice1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class H {
	static Scanner sc = new Scanner(System.in);
	static PrintWriter pw = new PrintWriter(System.out);

	static int n, m, memo[][];
	static char s[], t[];
	static ArrayList<String> ans;

	public static void main(String[] args) throws IOException, InterruptedException {
		s = sc.next().toCharArray();
		t = sc.next().toCharArray();
		n = s.length;
		m = t.length;
		memo = new int[n + 1][m + 1];
		for (int i = 0; i <= n; i++)
			Arrays.fill(memo[i], -1);
		pw.println(dp(0, 0));
		ans = new ArrayList<>();
		trace(0, 0);
		for (String x : ans)
			pw.println(x);
		pw.close();
	}

	// dp(i, j) represents the minimum number of operations required to match
	// s to t from starting at position i in s and j in t till the end
	// of both strings.E

	static int dp(int i, int j) {
		if (i == n && j == m)
			return 0;
		if (memo[i][j] != -1)
			return memo[i][j];
		if (i < n && j < m && s[i] == t[j])
			return dp(i + 1, j + 1);
		int ins = (int) 1e6, del = (int) 1e6, rep = (int) 1e6;
		if (j < m)
			ins = 1 + dp(i, j + 1);
		if (i < n)
			del = 1 + dp(i + 1, j);
		if (i < n && j < m)
			rep = 1 + dp(i + 1, j + 1);
		return memo[i][j] = Math.min(rep, Math.min(del, ins));
	}

	// This function backtracks the dp by simulating it and following
	// the correct solution every time.
	static void trace(int i, int j) {
		if (i == n && j == m)
			return;
		if (i < n && j < m && s[i] == t[j]) {
			trace(i + 1, j + 1);
			return;
		}
		int ins = (int) 1e6, del = (int) 1e6, rep = (int) 1e6;
		if (j < m)
			ins = 1 + dp(i, j + 1);
		if (i < n)
			del = 1 + dp(i + 1, j);
		if (i < n && j < m)
			rep = 1 + dp(i + 1, j + 1);
		if (ins == memo[i][j]) {
			ans.add("INSERT " + (j + 1) + " " + t[j]);
			trace(i, j + 1);
		} else if (del == memo[i][j]) {
			ans.add("DELETE " + (j + 1));
			trace(i + 1, j);
		} else {
			ans.add("REPLACE " + (j + 1) + " " + t[j]);
			trace(i + 1, j + 1);
		}
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