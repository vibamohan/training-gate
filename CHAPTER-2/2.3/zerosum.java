/*
TASK: zerosum
LANG: JAVA
*/

import java.io.*;
import java.util.*;

public class zerosum {
    static int N;
    static ArrayList<String> answers;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("zerosum.in"));
        BufferedWriter out = new BufferedWriter(new FileWriter("zerosum.out"));
        answers = new ArrayList<>();

        N = Integer.parseInt(in.readLine());
        dfs("1", 1, 1, 2);

        Collections.sort(answers);
        for (String s : answers) {
            out.write(s + "\n");
        }

        out.close();
    }

    static void dfs(String expr, int sum, int last, int current) throws IOException {
        if (current > N) {
            if (sum == 0) {
                answers.add(expr);
            }
            return;
        }

        dfs(expr + "+" + current, sum + current, current, current+1);
        dfs(expr + "-" + current, sum - current, -current, current+1);

        int concatNum = Integer.parseInt(Math.abs(last) + "" + current);
        int newSum = sum - last + (last > 0 ? concatNum : -concatNum);
        dfs(expr + " " + current, newSum, last > 0 ? concatNum : -concatNum, current+1);
    }
}
