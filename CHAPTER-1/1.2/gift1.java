/*
PROG: gift1
LANG: JAVA
*/

import java.io.*;
import java.util.*;

public class gift1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("gift1.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));

        int NP = Integer.parseInt(br.readLine());
        String[] names = new String[NP];
        Map<String, Integer> indexMap = new HashMap<>();
        int[] balances = new int[NP];

        for (int i = 0; i < NP; i++) {
            names[i] = br.readLine();
            indexMap.put(names[i], i);
        }

        String line;
        while ((line = br.readLine()) != null) {
            String giver = line;
            String[] moneyInfo = br.readLine().split(" ");
            int money = Integer.parseInt(moneyInfo[0]);
            int NGi = Integer.parseInt(moneyInfo[1]);

            if (NGi > 0) {
                int giftAmount = money / NGi;
                balances[indexMap.get(giver)] -= giftAmount * NGi;

                for (int i = 0; i < NGi; i++) {
                    String recipient = br.readLine();
                    balances[indexMap.get(recipient)] += giftAmount;
                }
            }
        }

        for (int i = 0; i < NP; i++) {
            pw.println(names[i] + " " + balances[i]);
        }

        br.close();
        pw.close();
    }
}
