/*
PROG: transform
LANG: JAVA
*/

import java.io.*;
import java.util.*;

public class transform {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("transform.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));
        
        int N = Integer.parseInt(br.readLine());
        char[][] before = new char[N][N];
        char[][] after = new char[N][N];
        
        for (int i = 0; i < N; i++) {
            before[i] = br.readLine().toCharArray();
        }
        for (int i = 0; i < N; i++) {
            after[i] = br.readLine().toCharArray();
        }
        
        int result = transformation(before, after, N);
        out.println(result);
        out.close();
        br.close();
    }

    static int transformation(char[][] before, char[][] after, int N) {
        if (matches(rotate(before, N, 90), after, N)) return 1;
        if (matches(rotate(before, N, 180), after, N)) return 2;
        if (matches(rotate(before, N, 270), after, N)) return 3;
        
        char[][] reflected = reflect(before, N);
        if (matches(reflected, after, N)) return 4;
        if (matches(rotate(reflected, N, 90), after, N) ||
            matches(rotate(reflected, N, 180), after, N) ||
            matches(rotate(reflected, N, 270), after, N)) return 5;
        if (matches(before, after, N)) return 6;
        return 7;
    }

    static char[][] rotate(char[][] square, int N, int degrees) {
        char[][] result = new char[N][N];
        if (degrees == 90) {
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    result[j][N-1-i] = square[i][j];
        } else if (degrees == 180) {
            return rotate(rotate(square, N, 90), N, 90);
        } else if (degrees == 270) {
            return rotate(rotate(square, N, 180), N, 90);
        }
        return result;
    }

    static char[][] reflect(char[][] square, int N) {
        char[][] result = new char[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                result[i][j] = square[i][N-1-j];
        return result;
    }

    static boolean matches(char[][] a, char[][] b, int N) {
        for (int i = 0; i < N; i++)
            if (!Arrays.equals(a[i], b[i]))
                return false;
        return true;
    }
}
