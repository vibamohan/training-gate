/*
PROG: ride
LANG: JAVA
*/

import java.io.*;
import java.util.*;

public class ride {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("ride.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));

        String comet = br.readLine();
        String group = br.readLine();

        long cometProduct = 1;
        long groupProduct = 1;

        for (char c : comet.toCharArray()) {
            cometProduct *= (c - 'A' + 1);
            cometProduct %= 47;
        }

        for (char c : group.toCharArray()) {
            groupProduct *= (c - 'A' + 1);
            groupProduct %= 47;
        }

        if (cometProduct % 47 == groupProduct % 47) {
            pw.println("GO");
        } else {
            pw.println("STAY");
        }

        br.close();
        pw.close();
    }
}
