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

public class D {
	static Scanner sc = new Scanner(System.in);
	static PrintWriter pw = new PrintWriter(System.out);
	static int N, W;
	static long w[], memo[][];
	static int v[];

	public static void main(String[] args) throws IOException, InterruptedException {
		N = sc.nextInt();
		W = sc.nextInt();
		int total = 0;
		v = new int[N];
		w = new long[N];
		for (int i = 0; i < N; i++) {
			w[i] = sc.nextLong();
			v[i] = sc.nextInt();
			total += v[i];
		}
		memo = new long[N + 1][total + 1];
		for (int i = 0; i <= N; i++)
			Arrays.fill(memo[i], -1);
		for (int i = total; i >= 0; i--)
			if (dp(0, i) <= W) {
				pw.print(i);
				break;
			}
		pw.close();
	}

	// dp(idx, val) represents the minimum weight required to
	// get the value val from the subarray starting at index idx to the end

	static long dp(int idx, int val) {
		if (idx == N) {
			if (val == 0)
				return 0;
			return (long) 1e9;
		}
		if (memo[idx][val] != -1)
			return memo[idx][val];
		// leave
		long res = dp(idx + 1, val);
		// take
		if (val >= v[idx])
			res = Math.min(res, w[idx] + dp(idx + 1, val - v[idx]));
		return memo[idx][val] = res;
	}

	static class Pair implements Comparable<Pair> {
		int first, second, idx;

		public Pair(int first, int second, int idx) {
			this.first = first;
			this.second = second;
			this.idx = idx;
		}

		public Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}

		@Override
		public int compareTo(Pair o) {
			if (o.first > first)
				return -1;
			if (o.first < first)
				return 1;
			if (o.second > second)
				return -1;
			if (o.second < second)
				return 1;
			return 0;
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