/*
PROG: combo
LANG: JAVA
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class combo {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("combo.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("combo.out")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()),
                c = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int d = Integer.parseInt(st.nextToken()), e = Integer.parseInt(st.nextToken()),
                f = Integer.parseInt(st.nextToken());
        br.close();

        int aSolutions = 1;
        int bSolutions = 1;
        int overlap = 1;

        for (int[] correspondingNum : new int[][]{{a, d}, {b, e}, {c, f}}) {
            int i = correspondingNum[0], j = correspondingNum[1];
            Set<Integer> aS = new HashSet<>();
            aS.add(wrap(i - 2, N));
            aS.add(wrap(i - 1, N));
            aS.add(wrap(i, N));
            aS.add(wrap(i + 1, N));
            aS.add(wrap(i + 2, N));
            
            Set<Integer> bS = new HashSet<>();
            bS.add(wrap(j - 2, N));
            bS.add(wrap(j - 1, N));
            bS.add(wrap(j, N));
            bS.add(wrap(j + 1, N));
            bS.add(wrap(j + 2, N));

            aSolutions *= aS.size();
            bSolutions *= bS.size();
            aS.retainAll(bS);
            overlap *= aS.size();
        }

        int solutions = aSolutions + bSolutions - overlap;
        out.println(solutions);
        out.close();
    }

    private static int wrap(int i, int n) {
        return ((i - 1 + n) % n) + 1;
    }
}
