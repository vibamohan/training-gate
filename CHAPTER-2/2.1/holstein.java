/*
PROG: holstein
LANG: JAVA
*/ 

import java.io.*;
import java.util.*;

public class holstein {
    static int V, G;
    static int[] req;
    static int[][] feeds;
    static int bestCount;
    static List<Integer> bestSet;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("holstein.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("holstein.out"));

        V = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        req = new int[V];
        for (int i = 0; i < V; i++) req[i] = Integer.parseInt(st.nextToken());

        G = Integer.parseInt(br.readLine());
        feeds = new int[G][V];
        for (int i = 0; i < G; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < V; j++) feeds[i][j] = Integer.parseInt(st.nextToken());
        }

        bestCount = G + 1;
        bestSet = new ArrayList<>();

        backtrack(0, new int[V], new ArrayList<>());

        Collections.sort(bestSet);
        pw.print(bestCount);
        for (int feed : bestSet) pw.print(" " + feed);
        pw.println();
        pw.close();
    }

    static void backtrack(int index, int[] currentTotal, List<Integer> chosenFeeds) {
        if (index == G) {
            boolean ok = true;
            for (int i = 0; i < V; i++) if (currentTotal[i] < req[i]) ok = false;
            if (ok) {
                if (chosenFeeds.size() < bestCount ||
                   (chosenFeeds.size() == bestCount && compareLists(chosenFeeds, bestSet) < 0)) {
                    bestCount = chosenFeeds.size();
                    bestSet = new ArrayList<>(chosenFeeds);
                }
            }
            return;
        }

        int[] newTotal = Arrays.copyOf(currentTotal, V);
        for (int i = 0; i < V; i++) newTotal[i] += feeds[index][i];
        chosenFeeds.add(index + 1);
        backtrack(index + 1, newTotal, chosenFeeds);
        chosenFeeds.remove(chosenFeeds.size() - 1);

        backtrack(index + 1, currentTotal, chosenFeeds);
    }

    private static int compareLists(List<Integer> a, List<Integer> b) {
        for (int i = 0; i < Math.min(a.size(), b.size()); i++) {
            if (!a.get(i).equals(b.get(i))) return a.get(i) - b.get(i);
        }
        return a.size() - b.size();
    }
}
