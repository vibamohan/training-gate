/*
PROG: prefix
LANG: JAVA
*/ 

import java.io.*;
import java.util.*;

public class prefix {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("prefix.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("prefix.out")));

        List<String> primitives = new ArrayList<>();
        while (true) {
            String line = br.readLine();
            if (line.equals(".")) break;
            StringTokenizer st = new StringTokenizer(line);
            while (st.hasMoreTokens()) {
                primitives.add(st.nextToken());
            }
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        String S = sb.toString();
        int n = S.length();

        boolean[] visited = new boolean[n + 1];
        ArrayDeque<Integer> q = new ArrayDeque<>();

        q.add(0);
        visited[0] = true;

        int best = 0;

        while (!q.isEmpty()) {
            int i = q.poll();
            best = Math.max(best, i);

            for (String p : primitives) {
                int len = p.length();
                int next = i + len;
                if (next <= n && !visited[next]) {
                    if (S.startsWith(p, i)) {
                        visited[next] = true;
                        q.add(next);
                    }
                }
            }
        }

        out.println(best);
        out.close();
    }
}
