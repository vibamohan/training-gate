
import java.io.*;
import java.util.*;

public class fracdec {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("fracdec.in"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("fracdec.out"));

        StringTokenizer st = new StringTokenizer(br.readLine());
        long numerator = Long.parseLong(st.nextToken());
        long denominator = Long.parseLong(st.nextToken());


        long integerPart = numerator / denominator;
        StringBuilder result = new StringBuilder();
        result.append(integerPart);

        long remainder = numerator % denominator;
        if (remainder == 0) {
            result.append(".0");
        } else {
            result.append(".");
            result.append(longDivision(remainder, denominator));
        }

        int lineLength = 76;
        for (int i = 0; i < result.length(); i += lineLength) {
            bw.write(result.substring(i, Math.min(i + lineLength, result.length())));
            bw.newLine();
        }

        br.close();
        bw.close();
    }

    private static String longDivision(long remainder, long denominator) {
        Map<Long, Integer> remainderPositions = new HashMap<>();
        StringBuilder fractionPart = new StringBuilder();
        int pos = 0;

        while (remainder != 0) {
            if (remainderPositions.containsKey(remainder)) {
                int repeatIndex = remainderPositions.get(remainder);
                fractionPart.insert(repeatIndex, "(");
                fractionPart.append(")");
                break;
            }

            remainderPositions.put(remainder, pos);
            remainder *= 10;
            long digit = remainder / denominator;
            fractionPart.append(digit);
            remainder %= denominator;
            pos++;
        }

        return fractionPart.toString();
    }
}
