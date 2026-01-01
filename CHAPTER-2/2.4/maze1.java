/*
PROG: maze1
LANG: JAVA
*/

import java.io.*;
import java.util.*;

public class maze1 {
    static int W, H;
    static char[][] a;
    static int[][] d;

    static class Coord {
        int r, c;
        Coord(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    enum Dir {
        N(-1, 0),
        E(0, 1),
        S(1, 0),
        W(0, -1);

        int dr, dc;
        Dir(int dr, int dc) {
            this.dr = dr;
            this.dc = dc;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("maze1.in"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        a = new char[2 * H + 1][2 * W + 1];
        for (int i = 0; i < 2 * H + 1; i++) {
            a[i] = br.readLine().toCharArray();
        }

        br.close();

        d = new int[H][W];
        for (int i = 0; i < H; i++) Arrays.fill(d[i], -1);

        Queue<Coord> q = new LinkedList<>();

        for (int r = 0; r < H; r++) {
            if (a[2*r+1][0] == ' ') {
                d[r][0] = 1;
                q.add(new Coord(r, 0));
            }
            if (a[2*r+1][2*W] == ' ') {
                d[r][W-1] = 1;
                q.add(new Coord(r, W-1));
            }
        }

        for (int c = 0; c < W; c++) {
            if (a[0][2*c+1] == ' ') {
                d[0][c] = 1;
                q.add(new Coord(0, c));
            }
            if (a[2*H][2*c+1] == ' ') {
                d[H-1][c] = 1;
                q.add(new Coord(H-1, c));
            }
        }

        while (!q.isEmpty()) {
            Coord cur = q.poll();
            int r = cur.r, c = cur.c;

            int rr = 2*r + 1;
            int cc = 2*c + 1;

            for (Dir dir : Dir.values()) {
                int nr = r + dir.dr;
                int nc = c + dir.dc;
                if (nr < 0 || nr >= H || nc < 0 || nc >= W) continue;
                if (d[nr][nc] != -1) continue;

                if (dir == Dir.N && a[rr-1][cc] != ' ') continue;
                if (dir == Dir.E && a[rr][cc+1] != ' ') continue;
                if (dir == Dir.S && a[rr+1][cc] != ' ') continue;
                if (dir == Dir.W && a[rr][cc-1] != ' ') continue;

                d[nr][nc] = d[r][c] + 1;
                q.add(new Coord(nr, nc));
            }
        }

        int ans = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                ans = Math.max(ans, d[i][j]);
            }
        }

        PrintWriter out = new PrintWriter(new FileWriter("maze1.out"));
        out.println(ans);
        out.flush();
        out.close();

    }
}
