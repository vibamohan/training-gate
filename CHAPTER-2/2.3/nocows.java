/*
PROG: nocows
LANG: JAVA
*/

import java.io.*;
import java.util.*;

public class nocows {
    static final int MOD = 9901;
    static int N, K;
    static int[][] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("nocows.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        br.close();

        memo = new int[N + 1][K + 1];
        for (int i = 0; i <= N; i++)
            Arrays.fill(memo[i], -1);

        int result = countExact(N, K);
        PrintWriter out = new PrintWriter(new FileWriter("nocows.out"));
        out.println(result);
        out.close();
    }

    static int countExact(int n, int k) {
        int result = (count(n, k) - count(n, k - 1)) % MOD;
        if (result < 0) result += MOD;
        return result;
    }

    static int count(int n, int k) {
        if (n % 2 == 0) return 0;
        if (k <= 0) return 0;
        if (n == 1) return 1;
        if (memo[n][k] != -1) return memo[n][k];

        int total = 0;
        for (int left = 1; left <= n - 2; left += 2) {
            int right = n - 1 - left;
            total += count(left, k - 1) * count(right, k - 1);
            total %= MOD;
        }

        memo[n][k] = total;
        return total;
    }
}
