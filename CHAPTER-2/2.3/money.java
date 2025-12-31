/*
PROG: money
LANG: JAVA
*/ 

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class money {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("money.in"));
        int V = scanner.nextInt();
        int N = scanner.nextInt();

        int[] coins = new int[V];
        for (int i = 0; i < V; i++) coins[i] = scanner.nextInt();

        long[] dp = new long[N + 1];
        dp[0] = 1;

        for (int coin : coins) {
            for (int sum = coin; sum <= N; sum++) {
                dp[sum] += dp[sum - coin];
            }
        }

        PrintWriter pw = new PrintWriter(new File("money.out"));
        pw.println(dp[N]);
        pw.flush();
        pw.close();
    }
}
