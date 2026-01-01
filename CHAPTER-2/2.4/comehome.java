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
        BufferedWriter bw = new BufferedWriter(new FileWriter("comehome.out"));
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
        Map<Character, Integer> dist = new HashMap<>();

        for (char c : adj.keySet()) {
            dist.put(c, Integer.MAX_VALUE);
        }
        dist.put('Z', 0);
        pq.add(new Node('Z', 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (curr.distance > dist.get(curr.pasture)) {
                continue;
            }

            for (Node neighbor : adj.get(curr.pasture)) {
                int newDist = dist.get(curr.pasture) + neighbor.distance;
                if (newDist < dist.get(neighbor.pasture)) {
                    dist.put(neighbor.pasture, newDist);
                    pq.add(new Node(neighbor.pasture, newDist));
                }
            }
        }

        char fastestCow = '?';
        int minDistance = Integer.MAX_VALUE;
        for (char c = 'A'; c <= 'Y'; c++) {
            if (dist.containsKey(c) && dist.get(c) < minDistance) {
                minDistance = dist.get(c);
                fastestCow = c;
            }
        }

        bw.write(fastestCow + " " + minDistance + "\n");
        bw.close();
    }
}
