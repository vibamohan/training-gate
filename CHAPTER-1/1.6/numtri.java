/*
PROG: numtri
LANG: JAVA
*/ 

import java.io.*;
import java.util.*;

public class numtri {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("numtri.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));

        int R = Integer.parseInt(br.readLine());
        int[][] triangle = new int[R][R];

        for (int i = 0; i < R; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; j++) {
                triangle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = R - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                triangle[i][j] += Math.max(triangle[i + 1][j], triangle[i + 1][j + 1]);
            }
        }

        pw.println(triangle[0][0]);

        pw.close();
        br.close();
    }
}
