/*
PROG: beads
LANG: JAVA
*/

import java.io.*;

public class beads {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("beads.in"));
        int N = Integer.parseInt(br.readLine().trim());
        String s = br.readLine().trim();
        br.close();

        String beads = s + s;
        int ans = 0;

        for (int i = 0; i < N; i++) {
            int right = countRight(beads, i, N);
            int left = countLeft(beads, i + N - 1, N);
            ans = Math.max(ans, Math.min(N, right + left));
        }

        PrintWriter out = new PrintWriter(new FileWriter("beads.out"));
        out.println(ans);
        out.close();
    }

    private static int countRight(String s, int start, int N) {
        char color = 'w';
        int cnt = 0;
        for (int i = start; i < start + N; i++) {
            char c = s.charAt(i);
            if (c == 'w') cnt++;
            else if (color == 'w') {
                color = c;
                cnt++;
            } else if (c == color) cnt++;
            else break;
        }
        return cnt;
    }

    private static int countLeft(String s, int start, int N) {
        char color = 'w';
        int cnt = 0;
        for (int i = start; i > start - N; i--) {
            char c = s.charAt(i);
            if (c == 'w') cnt++;
            else if (color == 'w') {
                color = c;
                cnt++;
            } else if (c == color) cnt++;
            else break;
        }
        return cnt;
    }
}
