package algorithm.divideAndConquer;

import java.util.*;
import java.io.*;

public class b1992_jisu {
    static int n;
    static int[][] map;
    static String answer = "";
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        divideBy(0, 0, n);

        System.out.println(answer);
    }

    static boolean allSame(int y, int x, int r) {
        int color = map[y][x];
        for (int i = y; i < y + r; i++) {
            for (int j = x; j < x + r; j++) {
                if (map[i][j] != color) return false;
            }
        }
        return true;
    }

    static void divideBy(int y, int x, int r) {
        if (allSame(y, x, r)) answer += map[y][x];
        else {
            int nextR = r/2;
            answer += "(";
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    divideBy(y + nextR * i, x + nextR * j, nextR);
                }
            }
            answer += ")";
        }
    }
}
