/*
PROG: palsquare
LANG: JAVA
*/

import java.io.*;

public class palsquare {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("palsquare.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("palsquare.out")));

        int B = Integer.parseInt(br.readLine().trim());

        for (int N = 1; N <= 300; N++) {
            int square = N * N;

            if (isPalindrome(square, B)) {
                pw.println(toBase(N, B) + " " + toBase(square, B));
            }
        }

        br.close();
        pw.close();
    }

    static String toBase(int num, int base) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            int digit = num % base;
            if (digit < 10) sb.append(digit);
            else sb.append((char) ('A' + digit - 10));
            num /= base;
        }
        return sb.reverse().toString();
    }

    static boolean isPalindrome(int num, int base) {
        int reversed = 0;
        int original = num;

        while (num > 0) {
            int digit = num % base;
            reversed = reversed * base + digit;
            num /= base;
        }

        return original == reversed;
    }
}
