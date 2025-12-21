/*
PROG: friday
LANG: JAVA
*/

import java.io.*;

public class friday {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("friday.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));

        int nYears = Integer.parseInt(br.readLine());
        int dayOfWeek = 0;
        int[] dayLengths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        int[] counters = new int[7];

        for (int dY = 0; dY < nYears; dY++) {
            int year = 1900 + dY;
            boolean isLeapYear = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));

            for (int month = 0; month < 12; month++) {
                counters[dayOfWeek]++;

                int days = dayLengths[month];
                if (month == 1 && isLeapYear) days++;
                dayOfWeek = (dayOfWeek + days) % 7;
            }
        }

        for (int i = 0; i < 7; i++) {
            pw.print(counters[i] + (i != 6 ? " " : "\n"));
        }

        br.close();
        pw.close();
    }
}
