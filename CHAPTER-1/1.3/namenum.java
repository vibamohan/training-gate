/*
PROG: namenum
LANG: JAVA
*/


import java.io.*;
import java.util.*;

public class namenum {
    static String[] digitToLetters = {
        "",     // 0
        "",     // 1
        "ABC",  // 2
        "DEF",  // 3
        "GHI",  // 4
        "JKL",  // 5
        "MNO",  // 6
        "PRS",  // 7
        "TUV",  // 8
        "WXY"   // 9
    };

    static List<String> dictionary = new ArrayList<>();
    static List<String> results = new ArrayList<>();
    static String number;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("namenum.in"));
        PrintWriter out = new PrintWriter(new FileWriter("namenum.out"));
        BufferedReader dictReader = new BufferedReader(new FileReader("dict.txt"));

        number = br.readLine().trim();

        String word;
        while ((word = dictReader.readLine()) != null) {
            dictionary.add(word);
        }
        Collections.sort(dictionary);

        backtrack(0, new StringBuilder());

        if (results.isEmpty()) {
            out.println("NONE");
        } else {
            Collections.sort(results);
            for (String name : results) {
                out.println(name);
            }
        }

        br.close();
        dictReader.close();
        out.close();
    }

    static void backtrack(int idx, StringBuilder current) {
        if (idx == number.length()) {
            String candidate = current.toString();
            if (Collections.binarySearch(dictionary, candidate) >= 0) {
                results.add(candidate);
            }
            return;
        }

        int digit = number.charAt(idx) - '0';
        for (char c : digitToLetters[digit].toCharArray()) {
            current.append(c);
            backtrack(idx + 1, current);
            current.deleteCharAt(current.length() - 1);
        }
    }
}

