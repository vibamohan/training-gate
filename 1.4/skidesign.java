/*
PROG: skidesign
LANG: JAVA
*/

import java.io.*;
import java.util.*;

public class skidesign {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("skidesign.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));

        int N = Integer.parseInt(br.readLine());
        int[] hills = new int[N];
        for (int i = 0; i < N; i++) {
            hills[i] = Integer.parseInt(br.readLine());
        }

        long minCost = Long.MAX_VALUE;

        for (int low = 0; low <= 100 - 17; low++) {
            int high = low + 17;
            long cost = 0;
            for (int h : hills) {
                if (h < low) cost += (long) (low - h) * (low - h);
                else if (h > high) cost += (long) (h - high) * (h - high);
            }
            minCost = Math.min(minCost, cost);
        }

        out.println(minCost);
        out.close();
    }
}
