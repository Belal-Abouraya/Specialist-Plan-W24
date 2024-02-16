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

public class G {
	static Scanner sc = new Scanner(System.in);
	static PrintWriter pw = new PrintWriter(System.out);
	static int n;
	static long weight[][], memo[];
	// Key observation: order doesn't matter. All we need to know is
	// which items are grouped together

	// dp represents the minimum cost to visit all univisited items in the mask.
	static long dp(int mask) {
		if (mask == (1 << n) - 1)
			return 0;
		if (memo[mask] != -1)
			return memo[mask];
		long ans = (long) 1e18;
		for (int i = 0; i < n; i++) {
			if ((mask & (1 << i)) != 0)
				continue;
			for (int j = 0; j < n; j++) { // Finds the item to pair with that
											// reduces the cost. Notice that this
											// takes care of doing only one item alone
											// as i can equal j.
				if ((mask & (1 << j)) != 0)
					continue;
				long tmp = weight[0][i + 1] + weight[i + 1][j + 1] + weight[j + 1][0];
				ans = Math.min(ans, tmp + dp(mask | (1 << i) | (1 << j)));
			}
			break; // We can do this because order is not relevant
					// as the univisited items here will be visited in a latter state.
		}
		return memo[mask] = ans;
	}

	static void trace(int mask) {
		if (mask == (1 << n) - 1)
			return;
		long ans = (long) 1e18;
		int first = 0, second = 0, nxt = 0;
		for (int i = 0; i < n; i++) {
			if ((mask & (1 << i)) != 0)
				continue;
			first = i;
			for (int j = 0; j < n; j++) {
				if ((mask & (1 << j)) != 0)
					continue;
				long tmp = weight[0][i + 1] + weight[i + 1][j + 1] + weight[j + 1][0];
				ans = Math.min(ans, tmp + dp(mask | (1 << i) | (1 << j)));
				if (ans == dp(mask)) {
					second = j;
					nxt = mask | (1 << i) | (1 << j);
					break;
				}
			}
			break;
		}
		pw.print(first + 1 + " ");
		if (first != second) // Prints the second item only
								// if it is different from the first.
			pw.print(second + 1 + " ");
		pw.print(0 + " "); // Always return to zero.
		trace(nxt);
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		int x = sc.nextInt(), y = sc.nextInt();
		n = sc.nextInt();
		int[][] points = new int[n + 1][2];
		weight = new long[n + 1][n + 1];
		points[0][0] = x;
		points[0][1] = y;
		for (int i = 1; i <= n; i++) {
			points[i][0] = sc.nextInt();
			points[i][1] = sc.nextInt();
		}
		for (int i = 0; i <= n; i++)
			for (int j = 0; j <= n; j++) {
				long dx = points[i][0] - points[j][0];
				long dy = points[i][1] - points[j][1];
				weight[i][j] = dx * dx + dy * dy;
			}
		memo = new long[1 << n];
		Arrays.fill(memo, -1);
		pw.println(dp(0));
		pw.print(0 + " "); // Always start at zero.
		trace(0);
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