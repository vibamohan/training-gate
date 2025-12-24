/*
PROG: milk
LANG: JAVA
*/

import java.io.*;
import java.util.*;

public class milk {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("milk.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] farmers = new int[M][2];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            farmers[i][0] = Integer.parseInt(st.nextToken());
            farmers[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(farmers, (a, b) -> Integer.compare(a[0], b[0]));

        long totalCost = 0;
        int milkNeeded = N;

        for (int i = 0; i < M && milkNeeded > 0; i++) {
            int buy = Math.min(farmers[i][1], milkNeeded);
            totalCost += (long) buy * farmers[i][0];
            milkNeeded -= buy;
        }

        out.println(totalCost);
        out.close();
        br.close();
    }
}
