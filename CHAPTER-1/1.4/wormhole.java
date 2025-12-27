/*
 PROG: wormhole
 LANG: JAVA
 */

import java.io.*;
import java.util.*;

public class wormhole {
    static int N;
    static int[] x, y;
    static int[] pair;
    static int[] nextOnRight;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("wormhole.in"));
        PrintWriter out = new PrintWriter(new FileWriter("wormhole.out"));
        
        N = Integer.parseInt(br.readLine());
        x = new int[N+1];
        y = new int[N+1];
        pair = new int[N+1];
        nextOnRight = new int[N+1];
        
        for (int i = 1; i <= N; i++) {
            String[] parts = br.readLine().split(" ");
            x[i] = Integer.parseInt(parts[0]);
            y[i] = Integer.parseInt(parts[1]);
        }
        
        // Precompute nextOnRight
        for (int i = 1; i <= N; i++) {
            int next = 0;
            for (int j = 1; j <= N; j++) {
                if (y[i] == y[j] && x[j] > x[i]) {
                    if (next == 0 || x[j] - x[i] < x[next] - x[i]) next = j;
                }
            }
            nextOnRight[i] = next;
        }
        
        int result = solve();
        out.println(result);
        out.close();
    }
    
    static int solve() {
        int i;
        for (i = 1; i <= N; i++) if (pair[i] == 0) break;
        
        if (i > N) {
            if (hasCycle()) return 1;
            else return 0;
        }
        
        int count = 0;
        for (int j = i+1; j <= N; j++) {
            if (pair[j] == 0) {
                pair[i] = j;
                pair[j] = i;
                count += solve();
                pair[i] = pair[j] = 0;
            }
        }
        return count;
    }
    
    static boolean hasCycle() {
        for (int start = 1; start <= N; start++) {
            int pos = start;
            for (int k = 0; k < N; k++) {
                pos = nextOnRight[pair[pos]];
                if (pos == 0) break;
            }
            if (pos != 0) return true;
        }
        return false;
    }
}
