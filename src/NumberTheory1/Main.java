package NumberTheory1;

import java.io.*;
import java.util.*;

public class Main {

    static PrintWriter pw = new PrintWriter(System.out);
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        pw.close();
    }

    public static ArrayList<Long> divisors(long x){
        ArrayList<Long> divs = new ArrayList<>();
        for (int i = 1; 1L * i * i <= x; i++){
            if (x % i == 0){
                divs.add((long)i);
                if (1L * i * i != x) divs.add(x/i);
            }
        }
        return divs;
    }

    public static ArrayList<Pair> primeFact(long x){
        ArrayList<Pair> fact = new ArrayList<>();
        for (long i = 2; i*i <= x; i++){
            if (x%i == 0){
                int power = 0;
                while (x%i == 0){
                    power++;
                    x/=i;
                }
                fact.add(new Pair(i, power));
            }
        }
        if (x > 1) fact.add(new Pair(x, 1));
        return fact;
    }

    static int N = (int) 1e7 + 1;

    static ArrayList<Integer> primes;
    static int[] prime = new int[N + 1];
    public static void sieve() { // Complexity O(Nlog(log(N));
        primes = new ArrayList<>();
        Arrays.fill(prime, 1);
        prime[0] = prime[1] = 0;
        for (int i = 4; i<N; i+=2) prime[i] = 0;
        for (int i = 3; 1l * i * i <= N; i+=2) {
            if (prime[i] == 0) continue;
            for (int j = i * i; j <= N; j += i) prime[j] = 0;
        }
        for (int i = 2; i<N; i++){
            if (prime[i] == 1) primes.add(i);
        }
    }
    static int[] pf = new int[N + 1];
    public static void sieve2() {
        for (int i = 1; i < N; i++) pf[i] = i;
        for (int i = 2; i < N; i++){
            if (pf[i] != i) continue;
            for (int j = 2 * i; j < N; j += i) pf[j] = i;
        }
    }
    public static ArrayList<Pair> primeFactors(int N) { // Complexity O(sqrt(N) / ln (sqrt(N)));
        // precalculation : prime numbers
        ArrayList<Pair> factors = new ArrayList<>();
        int PF_idx = 0;
        int PF = primes.get(PF_idx); // primes has been populated by sieve
        while (PF * PF <= N) { // stop at sqrt(N); N can get smaller
            if (N % PF == 0) {
                int pow = 0;
                while (N % PF == 0) {
                    N /= PF;
                    pow++;
                } // remove PF
                factors.add(new Pair(PF, pow));
            }
            PF = primes.get(++PF_idx); // only consider primes!
        }
        if (N > 1) {
            factors.add(new Pair(N,1));
        } // special case if N is a prime
        return factors; // if N does not fit in 32-bit integer and is a prime
    }

    public static ArrayList<Pair> primeFactors2(int N) { // Complexity O(log2(N));
        // precalculation : highest prime factor
        ArrayList<Pair> factors = new ArrayList<>();
        while (N > 1){
            int pow = 0;
            int p = pf[N];
            while (N % p == 0){
                pow++;
                N /= p;
            }
            factors.add(new Pair(p, pow));
        }
        return factors;
    }


    static long gcd(long a, long b){
        if (b == 0) return a;
        return gcd(b, a%b);
    }

    static long gcd_iter(long a, long b){
        while (b > 0){
            long a1 = b, b1 = a%b;
            a = a1; b = b1;
        }
        return a;
    }

    static long lcm(long a, long b){
        return a / gcd(a,b) * b;
    }

    static class Pair implements Comparable<Pair> {
        long first;
        long second;

        public Pair(long first, long second) {
            this.first = first;
            this.second = second;
        }

        public int compareTo(Pair p) {
            if (first != p.first)
                return Long.compare(first, p.first);
            else if (second != p.second)
                return Long.compare(second, p.second);
            else
                return 0;

        }

        public String toString() {
            return "(" + first + ", " + second + ")";
        }

    }

}
