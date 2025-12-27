/*
TASK: sprime
LANG: JAVA
*/

import java.io.*;

public class sprime {
    static int N;
    static StringBuilder out = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("sprime.in"));
        N = Integer.parseInt(br.readLine());
        br.close();

        int[] startPrimes = {2, 3, 5, 7};
        for (int p : startPrimes) {
            dfs(p, 1);
        }

        PrintWriter pw = new PrintWriter(new FileWriter("sprime.out"));
        pw.print(out.toString());
        pw.close();
    }

    static void dfs(int num, int len) {
        if (len == N) {
            out.append(num).append('\n');
            return;
        }

        for (int d = 1; d <= 9; d += 2) {
            int next = num * 10 + d;
            if (isPrime(next)) {
                dfs(next, len + 1);
            }
        }
    }

    static boolean isPrime(int x) {
        if (x < 2) return false;
        if (x % 2 == 0) return x == 2;
        for (int i = 3; i * i <= x; i += 2) {
            if (x % i == 0) return false;
        }
        return true;
    }
}
