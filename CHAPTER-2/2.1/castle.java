/*
PROG: castle
LANG: JAVA
*/

import java.io.*;
import java.util.*;

public class castle {
    static int M, N;
    static int[][] grid;
    static int[][] roomId;
    static int[] roomSize;
    static int roomCount = 0;

    static int[] dr = {0, -1, 0, 1};
    static int[] dc = {-1, 0, 1, 0};
    static int[] wall = {1, 2, 4, 8};

    static void dfs(int r, int c, int id) {
        Stack<int[]> st = new Stack<>();
        st.push(new int[]{r, c});
        roomId[r][c] = id;
        roomSize[id]++;

        while (!st.isEmpty()) {
            int[] cur = st.pop();
            int cr = cur[0], cc = cur[1];


            for (int d = 0; d < 4; d++) {
                if ((grid[cr][cc] & wall[d]) != 0) continue;
                int nr = cr + dr[d];
                int nc = cc + dc[d];
                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if (roomId[nr][nc] == -1) {
                    roomId[nr][nc] = id;
                    roomSize[id]++;
                    st.push(new int[]{nr, nc});
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("castle.in"));
        PrintWriter out = new PrintWriter(new FileWriter("castle.out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        grid = new int[N][M];
        roomId = new int[N][M];
        for (int[] row : roomId) Arrays.fill(row, -1);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        roomSize = new int[N * M];

        // Find rooms
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (roomId[r][c] == -1) {
                    dfs(r, c, roomCount++);
                }
            }
        }

        int largestRoom = 0;
        for (int i = 0; i < roomCount; i++)
            largestRoom = Math.max(largestRoom, roomSize[i]);

        int bestMerge = 0;
        int bestR = 0, bestC = 0;
        char bestDir = 'N';

        // Try removing walls
        for (int c = 0; c < M; c++) {
            for (int r = N - 1; r >= 0; r--) {
                int id = roomId[r][c];

                if ((grid[r][c] & 2) != 0 && r > 0) {
                    int nid = roomId[r - 1][c];
                    if (id != nid) {
                        int size = roomSize[id] + roomSize[nid];
                        if (size > bestMerge) {
                            bestMerge = size;
                            bestR = r;
                            bestC = c;
                            bestDir = 'N';
                        }
                    }
                }

                if ((grid[r][c] & 4) != 0 && c < M - 1) {
                    int nid = roomId[r][c + 1];
                    if (id != nid) {
                        int size = roomSize[id] + roomSize[nid];
                        if (size > bestMerge) {
                            bestMerge = size;
                            bestR = r;
                            bestC = c;
                            bestDir = 'E';
                        }
                    }
                }
            }
        }

        out.println(roomCount);
        out.println(largestRoom);
        out.println(bestMerge);
        out.println((bestR + 1) + " " + (bestC + 1) + " " + bestDir);
        out.close();
        br.close();
    }
}
