/*
PROG: milk2
LANG: JAVA
*/

import java.io.*;
import java.util.*;

public class milk2 {
    static class Interval {
        int start, end;
        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("milk2.in"));
        int N = Integer.parseInt(br.readLine());

        Interval[] intervals = new Interval[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            intervals[i] = new Interval(s, e);
        }

        Arrays.sort(intervals, Comparator.comparingInt(a -> a.start));

        int maxMilk = 0;
        int maxIdle = 0;

        int currStart = intervals[0].start;
        int currEnd = intervals[0].end;

        for (int i = 1; i < N; i++) {
            if (intervals[i].start <= currEnd) {
                currEnd = Math.max(currEnd, intervals[i].end);
            } else {
                maxMilk = Math.max(maxMilk, currEnd - currStart);
                maxIdle = Math.max(maxIdle, intervals[i].start - currEnd);

                currStart = intervals[i].start;
                currEnd = intervals[i].end;
            }
        }

        maxMilk = Math.max(maxMilk, currEnd - currStart);

        PrintWriter out = new PrintWriter(new FileWriter("milk2.out"));
        out.println(maxMilk + " " + maxIdle);
        out.close();
    }
}
