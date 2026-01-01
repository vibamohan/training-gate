/*
PROG: comehome
LANG: JAVA
*/

import java.io.*;
import java.util.*;

public class comehome {
    static class Node implements Comparable<Node> {
        char pasture;
        int distance;

        Node(char pasture, int distance) {
            this.pasture = pasture;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }

        @Override
        public String toString() {
            return "(" + pasture + "," + distance + ")";
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("comehome.in"));
        int P = Integer.parseInt(br.readLine().trim());

        Map<Character, List<Node>> adj = new HashMap<>();

        for (int i = 0; i < P; i++) {
            String[] parts = br.readLine().split(" ");
            char from = parts[0].charAt(0);
            char to = parts[1].charAt(0);
            int distance = Integer.parseInt(parts[2]);

            adj.putIfAbsent(from, new ArrayList<>());
            adj.putIfAbsent(to, new ArrayList<>());

            adj.get(from).add(new Node(to, distance));
            adj.get(to).add(new Node(from, distance));
        }

        br.close();

        PriorityQueue<Node> pq = new PriorityQueue<>();


    }
}
