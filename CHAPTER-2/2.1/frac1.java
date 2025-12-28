/*
PROG: frac1
LANG: JAVA
*/

import java.io.*;
import java.util.*;

public class frac1 {
    private static class Fraction implements Comparable<Fraction> {
        int numer, denom;

        Fraction(int numer, int denom) {
            this.numer = numer;
            this.denom = denom;
        }

        @Override
        public int compareTo(Fraction o) {
            long lhs = (long) numer * o.denom;
            long rhs = (long) o.numer * denom;
            return Long.compare(lhs, rhs);
        }

        @Override
        public String toString() {
            return numer + "/" + denom;
        }
    }

    static int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("frac1.in"));
        PrintWriter out = new PrintWriter(new FileWriter("frac1.out"));
        int n = Integer.parseInt(br.readLine());

        List<Fraction> list = new ArrayList<>();

        for (int d = 1; d <= n; d++) {
            for (int num = 0; num <= d; num++) {
                if (gcd(num, d) == 1) {
                    list.add(new Fraction(num, d));
                }
            }
        }

        Collections.sort(list);

        for (Fraction f : list) {
            out.println(f);
        }

        out.close();
    }
}
