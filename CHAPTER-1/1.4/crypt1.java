/*
PROG: crypt1
LANG: JAVA
*/

import java.io.*;
import java.util.*;

public class crypt1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("crypt1.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        boolean[] allowed = new boolean[10];
        List<Integer> digits = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int d = Integer.parseInt(st.nextToken());
            allowed[d] = true;
            digits.add(d);
        }

        List<Integer> threeDigitNumbers = new ArrayList<>();
        for (int a : digits) {
            if (a == 0) continue;
            for (int b : digits) {
                for (int c : digits) {
                    threeDigitNumbers.add(a*100 + b*10 + c);
                }
            }
        }

        List<Integer> twoDigitNumbers = new ArrayList<>();
        for (int d : digits) {
            if (d == 0) continue;
            for (int e : digits) {
                twoDigitNumbers.add(d*10 + e);
            }
        }

        int count = 0;

        for (int abc : threeDigitNumbers) {
            for (int de : twoDigitNumbers) {
                int d = de / 10;
                int e = de % 10;

                int p1 = abc * e;
                int p2 = abc * d;

                if (!isThreeDigit(p1) || !isThreeDigit(p2)) continue;
                if (!digitsInSet(p1, allowed) || !digitsInSet(p2, allowed)) continue;

                int total = abc * de;
                if (!isFourDigit(total) || !digitsInSet(total, allowed)) continue;

                count++;
            }
        }

        out.println(count);
        out.close();
        br.close();
    }

    private static boolean isThreeDigit(int n) {
        return n >= 100 && n <= 999;
    }

    private static boolean isFourDigit(int n) {
        return n >= 1000 && n <= 9999;
    }

    private static boolean digitsInSet(int n, boolean[] allowed) {
        while (n > 0) {
            if (!allowed[n % 10]) return false;
            n /= 10;
        }
        return true;
    }
}
