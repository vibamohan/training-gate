/*
PROG: pprime
LANG: JAVA
*/ 

import java.io.*;
import java.util.*;

public class pprime {
    static boolean isPrime(int n) {
        if (n < 2) return false;
        if (n % 2 == 0) return n == 2;
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    static int makePalindrome(int x) {
        int res = x;
        x /= 10;
        while (x > 0) {
            res = res * 10 + (x % 10);
            x /= 10;
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("pprime.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        PrintWriter out = new PrintWriter(new FileWriter("pprime.out"));

        List<Integer> possAns = new ArrayList<>();
        if (a <= 11 && 11 <= b) {
            possAns.add(11);
        }

        for (int i = 1; i <= 10000; i++) {
            int p = makePalindrome(i);
            if (p > b) break;
            if (p >= a && isPrime(p)) {
                possAns.add(p);
            }
        }

        Collections.sort(possAns);
        for (int i : possAns) {
            out.println(i);
        }

        out.close();
    }
}
