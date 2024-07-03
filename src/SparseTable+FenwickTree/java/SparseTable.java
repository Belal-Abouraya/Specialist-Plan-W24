class SparseTable {
    private int n, LOG;
    private int[][] st;

    SparseTable() {}

    SparseTable(int[] a) {
        this.n = a.length;
        this.LOG = (int)(Math.log(n) / Math.log(2)) + 1;
        this.st = new int[n][LOG];
        for (int i = 0; i < n; i++) {
            st[i][0] = a[i];
        }
        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i + (1 << j) - 1 < n; i++) {
                st[i][j] = Math.min(st[i][j - 1], st[i + (1 << (j - 1))][j - 1]);
            }
        }
    }

    int query(int l, int r) {
        int k = (int)(Math.log(r - l + 1) / Math.log(2));
        return Math.min(st[l][k], st[r - (1 << k) + 1][k]);
    }
}
