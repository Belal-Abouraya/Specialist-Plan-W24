class Fenwick { // 0-based query and update
    private int n;
    private long[] a, bit;

    Fenwick() {}

    Fenwick(int n) {
        this.n = n + 1;
        a = new long[this.n + 1];
        bit = new long[this.n + 1];
    }

    void add(int i, long x) {
        a[i] += x;
        for (++i; i < n; i += i & -i) {
            bit[i] += x;
        }
    }

    void set(int i, long val) {
        add(i, val - a[i]);
    }

    long query(int i) {
        long sum = 0;
        for (++i; i > 0; i -= i & -i) {
            sum += bit[i];
        }
        return sum;
    }

    long query(int l, int r) {
        return query(r) - query(l - 1);
    }
}
