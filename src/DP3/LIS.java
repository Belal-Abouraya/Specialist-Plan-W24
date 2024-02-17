package DP3;

import java.util.ArrayList;
import java.util.Scanner;

public class LIS {

	// Finds the index of the first element greater than or equal x.
	static int lower_bound(ArrayList<Integer> a, int x) {
		int res = 0, l = 0, r = a.size();
		while (l <= r) {
			int mid = (l + r) / 2;
			if (mid == a.size())
				return a.size();
			if (a.get(mid) < x)
				l = mid + 1;
			else {
				res = mid;
				r = mid - 1;
			}
		}
		return res;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] a = new int[n], p = new int[n];
		for (int i = 0; i < n; i++)
			a[i] = sc.nextInt();
		int idx = 0;
		// Greedy
		// O(nlog(n))
		ArrayList<Integer> l = new ArrayList<>(), id = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			int pos = lower_bound(l, a[i]);
			if (pos == l.size()) {
				l.add(a[i]);
				id.add(i);
				idx = i;
			} else {
				l.set(pos, a[i]);
				id.set(pos, i);
			}
			p[i] = pos > 0 ? id.get(pos - 1) : -1;
		}

		System.out.println(l.size());
		// Prints in reverse order.
		while (idx != -1) {
			System.out.print(idx + " ");
			idx = p[idx];
		}
		System.out.println();
		// DP
		// O(n^2)
		int[] d = new int[n];
		int res = 0;
		for (int i = 0; i < n; i++) {
			d[i] = 1;
			for (int j = 0; j < i; j++)
				if (a[j] < a[i])
					d[i] = Math.max(d[i], 1 + d[j]);

			res = Math.max(res, d[i]);
		}

		System.out.println(res);
	}
}
