template <class T> struct fenwick2d {
    int n, m;
    vector<vector<T>> a, bit;

    fenwick2d() {}

    fenwick2d(int _n, int _m) {
        n = _n + 1, m = _m + 1;
        a = bit = vector(n + 1, vector(m + 1, T(0)));
    }

    void add(int x, int y, T delta) {
        a[x][y] += delta;
        for (int i = x + 1; i <= n; i += i & -i) {
            for (int j = y + 1; j <= m; j += j & -j) {
                bit[i][j] += delta;
            }
        }
    }

    void set(int x, int y, int val) {
        add(x, y, val - a[x][y]);
    }

    T query(int x, int y) {
        T sum = 0;
        for (int i = x + 1; i >= 1; i -= i & -i) {
            for (int j = y + 1; j >= 1; j -= j & -j) {
                sum += bit[i][j];
            }
        }
        return sum;
    }

    T query(int x1, int y1, int x2, int y2) {
        return query(x2, y2) - query(x1 - 1, y2) - query(x2, y1 - 1) + query(x1 - 1, y1 - 1);
    }
};
