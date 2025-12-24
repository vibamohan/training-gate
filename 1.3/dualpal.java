/*
PROG: dualpal
LANG: JAVA
*/

import java.io.*;
import java.util.*;

public class dualpal {

    public static boolean isPalindrome(String s) {
        int len = s.length();
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(i) != s.charAt(len - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static String toBase(int n, int base) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.insert(0, n % base);
            n /= base;
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("dualpal.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        br.close();

        int countFound = 0;
        int current = S + 1;

        while (countFound < N) {
            int palBaseCount = 0;

            for (int base = 2; base <= 10; base++) {
                if (isPalindrome(toBase(current, base))) {
                    palBaseCount++;
                }
                if (palBaseCount >= 2) {
                    out.println(current);
                    countFound++;
                    break;
                }
            }

            current++;
        }

        out.close();
    }
}
