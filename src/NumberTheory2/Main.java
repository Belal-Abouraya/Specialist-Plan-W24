import java.util.*;

public class Main {
    static long mod = (long) 1e9 + 7;
    static long[] extendedGCD(long a, long b){
        if(b == 0){
            return new long[]{1, 0};
        }
        long[] next = extendedGCD(b, a%b);
        long x1 = next[0], y1 = next[1];
        long q = a/b;
        long x = y1;
        long y = x1 - q * y1;
        return new long[]{x, y};
    }

    public static long[] shiftSol(long x, long y, long a, long b, long k) {
        long g = gcd(a, b);
        return new long[] { x + k * b / g , y - k * a / g};
    }

    static long gcd(long a, long b){ //log2(a) + lob2(b)
        if(b == 0) return a;
        return gcd(b, a%b);
    }
    static long[] diophantine(long a, long b, long c){
        long g = gcd(a,b);
        if (c % g != 0){
            return new long[]{};
        }
        long k = c / g;
        long[] sol = extendedGCD(a, b);
        long x0 = sol[0], y0 = sol[1];
        return new long[]{k * x0, k * y0};
    }

    static long fastPower(long a, long b, long mod){
        if(b == 0) return 1;
        long x = fastPower(a, b/2, mod);
        return (x*x%mod) * (b % 2 == 0 ? 1 : a) % mod;
    }

    static long binPower(long a, long b, long mod){
        long res = 1;
        while (b > 0){
            if ((b & 1) != 0) res = res * a % mod;
            a = a * a % mod;
            b >>= 1;
        }
        return res;
    }

    static long[][] matrixMul(long[][]a, long[][]b){
        long[][] c = new long[a.length][b[0].length];
        for (int i = 0; i < a.length; ++i){
            for (int j = 0; j < b[0].length; ++j){
                for (int k = 0; k < a[0].length; ++k){
                    c[i][j] += a[i][k] * b[k][j] % mod;
                    c[i][j] %= mod;
                }
            }
        }
        return c;
    }

    static long[][] matrixPow(long[][] a, long b){
        long[][] res = new long[a.length][a.length];
        for (int i = 0; i<a.length; ++i) res[i][i] = 1;
        while (b > 0){
            if ((b&1) != 0) res = matrixMul(res, a);
            a = matrixMul(a, a);
            b >>= 1;
        }
        return res;
    }

    public static void main(String[] args) {

    }
}