/*
PROG: ttwo
LANG: JAVA
*/

import java.io.*;

public class ttwo {
    static final int N = 10;
    static char[][] grid = new char[N][N];

    enum Direction {
        NORTH(-1, 0),
        EAST(0, 1),
        SOUTH(1, 0),
        WEST(0, -1);

        final int dr, dc;

        Direction(int dr, int dc) {
            this.dr = dr;
            this.dc = dc;
        }

        Direction rotateCW() {
            return values()[(ordinal() + 1) % 4];
        }
    }

    static class Obj {
        int r, c;
        Direction dir;

        Obj(int r, int c) {
            this.r = r;
            this.c = c;
            this.dir = Direction.NORTH;
        }

        void move() {
            int nr = r + dir.dr;
            int nc = c + dir.dc;

            if (nr < 0 || nr >= N || nc < 0 || nc >= N || grid[nr][nc] == '*') {
                dir = dir.rotateCW(); // only rotate when movement is blocked 
            } else {
                r = nr;
                c = nc;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("ttwo.in"));
        PrintWriter pw = new PrintWriter("ttwo.out");

        Obj farmer = null;
        Obj cows = null;

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                grid[i][j] = line.charAt(j);
                if (grid[i][j] == 'F') farmer = new Obj(i, j);
                if (grid[i][j] == 'C') cows = new Obj(i, j);
            }
        }

        int maxSteps = (4 * 10 * 10) * (4 * 10 * 10);
        for (int t = 1; t <= maxSteps; t++) {
            farmer.move();
            cows.move();

            if (farmer.r == cows.r && farmer.c == cows.c) {
                pw.println(t);
                pw.flush();
                pw.close();
                return;
            }
        }

        pw.println(0);
        pw.flush();
        pw.close();
    }
}
