package Practice3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class M {
	static Scanner sc = new Scanner(System.in);
	static PrintWriter pw = new PrintWriter(System.out);
	static int n;
	static BigInteger x, lim;
	// We use a map instead of an array because the range is high but the number
	// of states is low so we don't get MLE.
	static TreeMap<BigInteger, Integer> memo;

	static int dp(BigInteger y) {
		if (y.compareTo(lim) >= 0) {
			return 0;
		}
		if (memo.containsKey(y))
			return memo.get(y);
		String tmp = y.toString();
		int res = (int) 1e9;
		for (int i = 0; i < tmp.length(); i++) {
			int d = tmp.charAt(i) - '0';
			if (d == 0 || d == 1) // Skip the bad digits.
				continue;
			res = Math.min(res, 1 + dp(y.multiply(new BigInteger("" + d))));
		}
		memo.put(y, res);
		return res;
	}

	public static void main(String[] args) throws IOException {
		n = sc.nextInt();
		x = new BigInteger(sc.next());
		lim = BigInteger.ONE;
		memo = new TreeMap<>();
		for (int i = 0; i < n - 1; i++)
			lim = lim.multiply(BigInteger.TEN);
		int res = dp(x);
		if (res >= (int) 1e9)
			res = -1;
		pw.println(res);
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