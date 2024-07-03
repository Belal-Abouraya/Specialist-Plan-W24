class Fenwick2D { // 0-based query and update

    private int n, m;
    private long[][] a, bit;

    Fenwick2D() {}

    Fenwick2D(int n, int m) {
        this.n = n + 1;
        this.m = m + 1;
        a = new long[this.n + 1][this.m + 1];
        bit = new long[this.n + 1][this.m + 1];
    }

    void add(int x, int y, long delta) {
        a[x][y] += delta;
        for (int i = x + 1; i <= n; i += i & -i) {
            for (int j = y + 1; j <= m; j += j & -j) {
                bit[i][j] += delta;
            }
        }
    }

    void set(int x, int y, long val) {
        add(x, y, val - a[x][y]);
    }

    long query(int x, int y) {
        long sum = 0;
        for (int i = x + 1; i >= 1; i -= i & -i) {
            for (int j = y + 1; j >= 1; j -= j & -j) {
                sum += bit[i][j];
            }
        }
        return sum;
    }

    long query(int x1, int y1, int x2, int y2) {
        return query(x2, y2) - query(x1 - 1, y2) - query(x2, y1 - 1) + query(x1 - 1, y1 - 1);
    }
}
