/*
PROG: milk3
LANG: JAVA
*/ 

import java.io.*;
import java.util.*;

public class milk3 {

    static int[] cap = new int[3];
    static boolean[][][] visited;
    static TreeSet<Integer> result = new TreeSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("milk3.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        cap[0] = Integer.parseInt(st.nextToken());
        cap[1] = Integer.parseInt(st.nextToken());
        cap[2] = Integer.parseInt(st.nextToken());

        visited = new boolean[cap[0] + 1][cap[1] + 1][cap[2] + 1];

        int[] initial = {0, 0, cap[2]};
        dfs(initial);

        int rsSize = result.size();
        for (int i = 0; i < rsSize; i++) {
            pw.print(result.pollFirst() + ((i < rsSize - 1) ? " " : ""));
        }
        pw.print("\n");
        pw.close();
    }

    static void dfs(int[] state) {
        if (visited[state[0]][state[1]][state[2]]) return;
        visited[state[0]][state[1]][state[2]] = true;

        if (state[0] == 0) {
            result.add(state[2]);
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j || state[i] == 0 || state[j] == cap[j]) continue;

                int[] next = {state[0], state[1], state[2]};
                int pourAmt = Math.min(state[i], cap[j] - state[j]);
                next[i] -= pourAmt;
                next[j] += pourAmt;
                dfs(next);
            }
        }
    }
}
