/*
PROG: concom
LANG: JAVA
*/ 

import java.io.*;
import java.util.*;

public class concom {
    static int[][] own = new int[101][101];
    static boolean[][] control = new boolean[101][101];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("concom.in"));
        PrintWriter out = new PrintWriter(new FileWriter("concom.out"));

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            own[a][b] += p;
        }

        for (int h = 1; h <= 100; h++) {
            int[] sum = new int[101];
            boolean[] controlled = new boolean[101];

            for (int j = 1; j <= 100; j++) {
                sum[j] = own[h][j];
            }

            boolean changed;
            do {
                changed = false;
                for (int j = 1; j <= 100; j++) {
                    if (!controlled[j] && sum[j] > 50) {
                        controlled[j] = true;
                        changed = true;

                        for (int k = 1; k <= 100; k++) {
                            sum[k] += own[j][k];
                        }
                    }
                }
            } while (changed);

            for (int s = 1; s <= 100; s++) {
                if (h != s && controlled[s]) {
                    control[h][s] = true;
                }
            }
        }

        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++) {
                if (control[i][j]) {
                    out.println(i + " " + j);
                }
            }
        }

        out.close();
        br.close();
    }
}
