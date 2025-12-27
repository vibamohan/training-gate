/*
PROG: ariprog
LANG: JAVA
*/ 

import java.io.*;
import java.util.*;

public class ariprog {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("ariprog.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int maxBisquare = 2 * M * M;
        boolean[] isBisquare = new boolean[maxBisquare + 1];
        List<Integer> bisquares = new ArrayList<>();

        for (int p = 0; p <= M; p++) {
            for (int q = 0; q <= M; q++) {
                int val = p * p + q * q;
                if (!isBisquare[val]) {
                    isBisquare[val] = true;
                    bisquares.add(val);
                }
            }
        }
        
        Collections.sort(bisquares);

        List<int[]> results = new ArrayList<>();

        for (int a : bisquares) {
            int maxB = (maxBisquare - a) / (N - 1);
            for (int b = 1; b <= maxB; b++) {
                boolean valid = true;
                for (int k = 0; k < N; k++) {
                    if (!isBisquare[a + k * b]) {
                        valid = false;
                        break;
                    }
                }
                if (valid) {
                    results.add(new int[]{a, b});
                }
            }
        }

        results.sort((x, y) -> x[1] != y[1] ? x[1] - y[1] : x[0] - y[0]);

        if (results.isEmpty()) {
            out.println("NONE");
        } else {
            for (int[] r : results) {
                out.println(r[0] + " " + r[1]);
            }
        }

        br.close();
        out.close();
    }
}
