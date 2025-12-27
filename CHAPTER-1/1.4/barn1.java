/*
PROG: barn1
LANG: JAVA
*/

import java.io.*;
import java.util.*;
public class barn1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("barn1.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("barn1.out")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int maxBoards = Integer.parseInt(st.nextToken());
        int stalls = Integer.parseInt(st.nextToken());
        int cows = Integer.parseInt(st.nextToken());

        if (cows < maxBoards) { out.println(cows); out.flush(); br.close(); out.close(); return; }

        int[] occupied = new int[cows];
        for (int i = 0; i < cows; i++) {
            occupied[i] = Integer.parseInt(br.readLine());
        }
        br.close();

        Arrays.sort(occupied);

        int blocked = occupied[cows - 1] - occupied[0] + 1;

        ArrayList<Integer> gaps = new ArrayList<>();
        for (int i = 1; i < cows; i++) {
            int gap = occupied[i] - occupied[i - 1] - 1;
            if (gap > 0) gaps.add(gap);
        }

        gaps.sort(Collections.reverseOrder());

        int saved = 0;
        for (int i = 0; i < Math.min(maxBoards - 1, gaps.size()); i++) {
            saved += gaps.get(i);
        }
        out.println(blocked - saved);

        out.close();
    }
}
