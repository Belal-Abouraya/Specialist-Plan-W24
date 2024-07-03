template <class T> struct fenwick {
    int n;
    vector<T> a, bit;

    fenwick() {}

    fenwick(int _n) : n(_n + 1), a(n + 1), bit(n + 1) {} 

    void add(int i, T x) {
        a[i] += x;
        for (++i; i <= n; i += i & -i) bit[i] += x;
    }

    void set(int i, T val) {
        add(i, val - a[i]);
    }

    T query(int i) {
        T sum = 0;
        for (++i; i >= 1; i -= i & -i) sum += bit[i];
        return sum;
    }

    T query(int l, int r) {
        return query(r) - query(l - 1);
    }
};
